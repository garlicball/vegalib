package com.thesmartkbd.vagalib.exception;

/* ************************************************************************
 *
 * Copyright (C) 2020 thesmartkbd All rights reserved.
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
 * @author thesmartkbd
 */
public class WriteException extends FrameworkRuntimeException {

    public WriteException() {
        super();
    }

    public WriteException(String message) {
        super(message);
    }

    public WriteException(String message, Object... args) {
        super(message, args);
    }

    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteException(String message, Throwable cause, Object... args) {
        super(message, args, cause);
    }

    public WriteException(Throwable cause) {
        super(cause);
    }

}
