package com.bitfashion.libraries.fashiontools.logging.slf4j;

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

/* Creates on 2019/11/05. */

import com.bitfashion.libraries.fashiontools.logging.Logger;

import static com.bitfashion.libraries.fashiontools.Objects.sprintf;

/**
 * @author bit-bitfashion
 */
public class Slf4jLogger implements Logger {

    private final org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void info(String message, Object... args) {
        logger.info(sprintf(message, args));
    }

    @Override
    public void warn(String message, Object... args) {
        logger.warn(sprintf(message, args));
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(sprintf(message, args));
    }

    @Override
    public void error(String message, Object... args) {
        logger.error(sprintf(message, args));
    }

}

