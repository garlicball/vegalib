package org.venorze.vegalib.security;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 venorze                                                    *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2022/8/29. */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.venorze.vegalib.Assert;
import org.venorze.vegalib.collection.Collections;
import org.venorze.vegalib.time.DateFormatter;
import org.venorze.vegalib.time.VegaTimeUnit;

import java.util.Date;
import java.util.Map;

/**
 * Jwt token 生成器。
 *
 * @author venorze
 */
public class JwtProvider {

    /**
     * token 过期时间，以秒为单位
     */
    private final int expire;
    /**
     * 加密算法
     */
    private final Algorithm algorithm;
    /**
     * 写入荷载数据前对荷载数据进行处理
     */
    private WritePayload writePayload = claim -> claim;
    /**
     * 读取荷载数据前对荷载数据进行处理
     */
    private ReadPayload readPayload = claim -> claim;

    /**
     * Token 签发者
     */
    private static final String ISSUER = "UN_NAMED_ISSUE";
    /**
     * 过期时间
     */
    private static final String EXPIRE = "exp";
    /**
     * 荷载命名
     */
    private static final String PAYLOAD = "payload";

    /** 设置 Payload 之前对 Map 进行处理 */
    public interface WritePayload {
        Map<String, Object> apply(Map<String, Object> claim);
    }

    /** 获取 Payload 之前对 Payload 进行处理 */
    public interface ReadPayload {
        Map<String, Object> apply(Map<String, Object> claim);
    }

    /**
     * 创建 token 生成器，默认使用 HS256 加密算法加密 token 内容。
     * 暂时还不支持其他加密算法。
     *
     * @param secret
     *        HS256 加密算法中的密钥值
     *
     * @param expire
     *        Token 过期时间，这个时间以秒为单位
     *
     */
    public JwtProvider(String secret, int expire) {
        this.expire     = expire;
        this.algorithm  = Algorithm.HMAC256(secret);
    }

    public <V> String sign(String k0, V v0) {
        return sign(Collections.mapOf(k0, v0));
    }

    public <V> String sign(String k0, V v0, String k1, V v1) {
        return sign(Collections.mapOf(k0, v0, k1, v1));
    }

    public <V> String sign(String k0, V v0, String k1, V v1, String k2, V v2) {
        return sign(Collections.mapOf(k0, v0, k1, v1, k2, v2));
    }

    public void setWritePayload(WritePayload writePayload) {
        this.writePayload = writePayload;
    }

    public void setReadPayload(ReadPayload readPayload) {
        this.readPayload = readPayload;
    }

    /**
     * 签发 token，也可以理解为生成一个 token。这个函数封装了来自 {@link JWT} 这个工具包下
     * 的大部分算法，以及 token 加密解密过程。
     *
     * @param payload
     *        Token荷载
     */
    public String sign(Map<String, Object> payload) {
        Date expireTime = VegaTimeUnit.SECONDS.plus(expire);
        JWTCreator.Builder jwtCreator = JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(expireTime);
        if (payload == null)
            payload = Collections.mapOf();
        payload.put(EXPIRE, DateFormatter.fmt(expireTime));
        jwtCreator.withClaim(PAYLOAD, writePayload.apply(payload));
        return jwtCreator.sign(this.algorithm);
    }

    /**
     * @return 获取 token 数据
     */
    public Payload payload(String token) {
        Assert.throwIfFalse(verifier(token));
        Map<String, Object> m = JWT.decode(token)
                .getClaim(PAYLOAD)
                .asMap();
        return new Payload(readPayload.apply(m));
    }

    /**
     * @return 校验当前 token 是否正确或是否已过期，如果 token 校验通过
     *         则返回 {@code true} 反之 {@code false}。
     */
    public boolean verifier(String token) {
        try {
            JWTVerifier verifierBuilder = JWT.require(this.algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifierBuilder.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * @return 返回 token 的过期时间，如果 token 已过期、或 token 不正确
     *         则会抛出校验异常
     */
    public Date expire(String token) {
        return DateFormatter.parse(payload(token).getAttribute(EXPIRE));
    }

}
