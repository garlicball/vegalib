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

/* Creates on 2023/4/30. */

import static com.thesmartkbd.vagalib.Objects.sprintf;

/**
 * @author thesmartkbd
 */
public class FrameworkRuntimeException extends RuntimeException {

    public FrameworkRuntimeException() {
        super();
    }

    public FrameworkRuntimeException(String message) {
        super(message);
    }

    public FrameworkRuntimeException(String message, Object... args) {
        super(sprintf(message, args));
    }

    public FrameworkRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkRuntimeException(String message, Throwable cause, Object... args) {
        super(sprintf(message, args), cause);
    }

    public FrameworkRuntimeException(Throwable cause) {
        super(cause);
    }

}
