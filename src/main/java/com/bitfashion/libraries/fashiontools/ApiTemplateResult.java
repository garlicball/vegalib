package com.bitfashion.libraries.fashiontools;

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

/* Creates on 2023/5/13. */

import com.bitfashion.libraries.fashiontools.time.DateFormatter;
import lombok.Data;

import java.io.Serializable;

import static com.bitfashion.libraries.fashiontools.Assert.throwIfTrue;
import static com.bitfashion.libraries.fashiontools.Objects.sprintf;
import static com.bitfashion.libraries.fashiontools.Objects.streq;

/**
 * @author bit-bitfashion
 */
@Data
public class ApiTemplateResult<T> implements Serializable {

    /**
     * API 接口状态码
     */
    private String code;
    /**
     * 携带的数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 时间戳
     */
    private Long timestamp = DateFormatter.currentTimestamp();

    public ApiTemplateResult() {
    }

    public ApiTemplateResult(String code, T data, String message, Object... args) {
        this.code = code;
        this.data = data;
        this.message = sprintf(message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// success
    /////////////////////////////////////////////////////////////////////////////////

    public static <T> ApiTemplateResult<T> ok() {
        return ok((T) null);
    }

    public static <T> ApiTemplateResult<T> ok(T data) {
        return ok("200", data, "请求成功");
    }

    public static <T> ApiTemplateResult<T> ok(String code, T data, String message, Object... args) {
        return new ApiTemplateResult<>(code, data, message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// failed
    /////////////////////////////////////////////////////////////////////////////////

    public static <T> ApiTemplateResult<T> failed() {
        return failed("请求失败");
    }

    public static <T> ApiTemplateResult<T> failed(String message) {
        return failed("500", null, message);
    }

    public static <T> ApiTemplateResult<T> failed(String code, T data, String message, Object... args) {
        return new ApiTemplateResult<>(code, data, message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// not static
    /////////////////////////////////////////////////////////////////////////////////

    public boolean isError() {
        return !streq("200", code);
    }

    public void throwIfError() {
        throwIfTrue(isError(), "%x[code: %s] %s", code, message);
    }

    public T as() {
        return data;
    }

}
