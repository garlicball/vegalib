package com.thesmartkbd.vagalib.logging.standard;

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

/* Creates on 2019/11/05. */

import com.thesmartkbd.vagalib.io.IOUtils;
import com.thesmartkbd.vagalib.logging.Logger;
import com.thesmartkbd.vagalib.time.DateFormatter;

import static com.thesmartkbd.vagalib.Objects.sprintf;

/**
 * @author thesmartkbd
 */
public class StandardLogger implements Logger {

    private final String classpath;

    public StandardLogger(String name) {
        this.classpath = name;
    }

    public StandardLogger(Class<?> aClass) {
        this.classpath = aClass.getName();
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void info(String message, Object... args) {
        logprint(classpath, "INFO", message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        logprint(classpath, "WARN", message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        logprint(classpath, "DEBUG", message, args);
    }

    @Override
    public void error(String message, Object... args) {
        logprint(classpath, "ERROR", message, args);
    }

    private static void logprint(String classpath, String level, String message, Object... args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        IOUtils.stdout.println("%s %s [%s] %s --- %s",
                DateFormatter.fmt(),
                level,
                stackTraceElement.getMethodName(),
                classpath,
                sprintf(message, args));
    }

}

