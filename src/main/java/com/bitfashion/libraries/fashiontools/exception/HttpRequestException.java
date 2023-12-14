package com.bitfashion.libraries.fashiontools.exception;

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

/* Creates on 2023/5/8. */

/**
 * @author bit-bitfashion
 */
public class HttpRequestException extends FrameworkRuntimeException {

    public HttpRequestException() {
        super();
    }

    public HttpRequestException(String message) {
        super(message);
    }

    public HttpRequestException(String message, Object... args) {
        super(message, args);
    }

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestException(String message, Throwable cause, Object... args) {
        super(message, args, cause);
    }

    public HttpRequestException(Throwable cause) {
        super(cause);
    }

}
