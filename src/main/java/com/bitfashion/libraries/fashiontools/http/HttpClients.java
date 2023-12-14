package com.bitfashion.libraries.fashiontools.http;

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

/* Creates on 2022/8/8. */

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.bitfashion.libraries.fashiontools.Assert;
import com.bitfashion.libraries.fashiontools.exception.HttpRequestException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http 请求工具类
 *
 * @author bit-bitfashion
 */
public class HttpClients {

    private static final int READ_TIMEOUT = 45; // seconds
    private static final int CONNECTION_TIMEOUT = 45; // seconds

    private static final RequestQueryParams EMPTY_REQUEST_ARGUMENTS = new RequestQueryParams();

    enum ResponseType {
        BYTE,
        STRING,
        BYTE_STRING,
        BYTE_STREAM,
        CHAR_STREAM,
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。
     *
     * @param url
     *        请求 url
     *
     * @return 请求结果：字符串
     */
    public static String get(String url) {
        return get(url, null, (RequestQueryParams) null);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。将返回结果序列化为指定
     * 的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param seri
     *        请求结果序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Class<T> seri) {
        return get(url, null, null, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求头即可，无需传入请求参数。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, Map<String, String> headers) {
        return get(url, headers, (RequestQueryParams) null);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求头即可，无需传入请求参数。并将返回结果序列化为指定的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Map<String, String> headers, Class<T> seri) {
        return get(url, headers, null, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求参数即可，无需传入请求头。并将返回结果序列化为指定的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, RequestQueryParams arguments) {
        return get(url, null, arguments);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求参数即可，无需传入请求头。
     *
     * @param url
     *        请求 url
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @param seri
     *        指定序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, RequestQueryParams arguments, Class<T> seri) {
        return get(url, null, arguments, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数需要
     * 指定所有信息。包含：请求头、请求参数。并将返回结果序列化为指定的对象
     * 实例。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @param seri
     *        指定序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Map<String, String> headers, RequestQueryParams arguments, Class<T> seri) {
        return JSONObject.parseObject(get(url, headers, arguments), seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数需要
     * 指定所有信息。包含：请求头、请求参数。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, Map<String, String> headers, RequestQueryParams arguments) {
        if (arguments == null)
            arguments = EMPTY_REQUEST_ARGUMENTS;
        return (String) get0(url, headers, arguments, ResponseType.STRING);
    }

    /**
     * 指定URL参数发起一次GET请求，参数不可携带到RequestBody中，
     * 参数只能在url填写。
     *
     * @param url
     *        请求url
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    @SuppressWarnings("SameParameterValue")
    private static Object get0(String url, Map<String, String> headers, @NotNull RequestQueryParams arguments,
                               ResponseType responseType) {
        Object retval;

        /* 构建参数 */
        url = arguments.build(url);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .get();

        /* 添加 Header */
        if (headers != null && !headers.isEmpty())
            headers.forEach(requestBuilder::addHeader);

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            /* 根据枚举类型获取响应数据 */
            retval = parseResponseObject(response, responseType);
            /* 断言请求是否成功 */
            Assert.throwIfFalse(response.isSuccessful(), "HTTP请求出错, CODE: {}, URL: {}, MESSAGE: {}",
                    response.code(), url, retval);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }

        return retval;
    }


    /**
     * 指定URL发起一次简单POST请求，将返回结果序列化成指定Class对象
     *
     * @param url
     *        请求URL
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Class<T> seri) {
        return post(url, (Object) null, null, seri);
    }

    /**
     * 指定URL发起一次简单POST请求，将返回结果序列化成指定Class对象
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Object param, Class<T> seri) {
        return post(url, param, null, seri);
    }

    /**
     * 指定URL发起一次复杂POST请求，所有的请求数据都可作为参数传递：请求体、请求头。
     * 请求成功后将返回结果序列化成指定Class对象。
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Object param, Map<String, String> headers, Class<T> seri) {
        return JSONObject.parseObject(post(url, param, headers), seri);
    }

    /**
     * 指定URL发起一次POST请求，返回请求结果
     *
     * @param url 请求URL
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url) {
        return post(url, (Object) null, (Map<String, String>) null);
    }

    /**
     * 指定URL发起一次POST请求，返回请求结果
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url, Object param) {
        return post(url, param, (Map<String, String>) null);
    }

    /**
     * 指定URL参数发起一次post请求，请求可携带body参数以及请求头。
     *
     * @param url
     *        请求url
     *
     * @param param
     *        请求的body参数，该参数可以是String字符串，也可以是一个对象
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url, Object param, Map<String, String> headers) {
        return (String) post0(url, param, headers, ResponseType.STRING);
    }

    /**
     * 指定URL参数发起一次post请求，请求可携带body参数以及请求头。
     *
     * @param url
     *        请求url
     *
     * @param param
     *        请求的body参数，该参数可以是String字符串，也可以是一个对象
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    @SuppressWarnings("SameParameterValue")
    private static Object post0(String url, Object param, Map<String, String> headers,
                                ResponseType responseType) {
        Object retval;

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                param instanceof String ? (String) param : JSON.toJSONString(param));

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);

        /* 添加 Header */
        if (headers != null && !headers.isEmpty())
            headers.forEach(requestBuilder::addHeader);

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            /* 根据枚举类型获取响应数据 */
            retval = parseResponseObject(response, responseType);
            /* 断言请求是否成功 */
            Assert.throwIfFalse(response.isSuccessful(), "HTTP请求出错, CODE: {}, URL: {}, REQUEST BODY: {}, MESSAGE: {}",
                    response.code(), url, JSON.toJSONString(param), retval);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }

        return retval;
    }

    /**
     * 获取响应数据，通过枚举类型判断请求需要获取什么样的数据从而返回出去。
     *
     * @param response
     *        响应对象
     *
     * @param responseType
     *        响应数据类型
     */
    private static Object parseResponseObject(Response response, ResponseType responseType)
            throws IOException {
        ResponseBody body = response.body();
        if (body != null) {
            return switch (responseType) {
                case BYTE -> body.bytes();
                case STRING -> body.string();
                case BYTE_STRING -> body.byteString();
                case BYTE_STREAM -> body.byteStream();
                case CHAR_STREAM -> body.charStream();
            };
        }
        return null;
    }

}
