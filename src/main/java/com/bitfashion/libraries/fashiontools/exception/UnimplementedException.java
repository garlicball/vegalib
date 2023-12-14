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
 * 未实现异常
 *
 * @author bit-bitfashion
 */
public class UnimplementedException extends FrameworkRuntimeException {

    public UnimplementedException() {
        super();
    }

    public UnimplementedException(String message) {
        super(message);
    }

    public UnimplementedException(String message, Object... args) {
        super(message, args);
    }

    public UnimplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnimplementedException(String message, Throwable cause, Object... args) {
        super(message, args, cause);
    }

    public UnimplementedException(Throwable cause) {
        super(cause);
    }

}
