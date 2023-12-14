package com.bitfashion.libraries.fashiontools.security;

/* ************************************************************************
 *
 * Copyright (C) 2020 bit-bitfashion All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not useEnv this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2022/8/29. */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bitfashion.libraries.fashiontools.Assert;
import com.bitfashion.libraries.fashiontools.collection.Collections;
import com.bitfashion.libraries.fashiontools.time.TimeUnits;
import com.bitfashion.libraries.fashiontools.time.DateFormatter;

import java.util.Date;
import java.util.Map;

import static com.bitfashion.libraries.fashiontools.Assert.throwIfFalse;
import static com.bitfashion.libraries.fashiontools.collection.Collections.mapOf;

/**
 * Jwt token 生成器。
 *
 * @author bit-bitfashion
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
        Date expireTime = TimeUnits.SECONDS.plus(expire);
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
        var m = JWT.decode(token)
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
