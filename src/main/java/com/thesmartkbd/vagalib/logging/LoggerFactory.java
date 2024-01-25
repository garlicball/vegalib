package com.thesmartkbd.vagalib.logging;

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

import com.thesmartkbd.vagalib.logging.slf4j.Slf4jLoggerAdapter;
import com.thesmartkbd.vagalib.refection.JvmRefs;

import static com.thesmartkbd.vagalib.Objects.sprintf;

/**
 * @author thesmartkbd
 */
public class LoggerFactory {

    /** 日志适配器 */
    private static LoggerAdapter loggerAdapterInstance = null;

    static {
        tryFindBestChoiceLoggerAdapter(LoggerFactory::slf4j);
        tryFindBestChoiceLoggerAdapter(LoggerFactory::standard);
    }

    /**
     * 根据传入的 {@code name} 来指定日志别名，如果是根据类型传入那么打印名称
     * 则会是类的全路径。
     *
     * @param name
     *        日志别名
     *
     * @return 日志记录器
     */
    public static Logger getLogger(String name) {
        return loggerAdapterInstance.getLogger(name);
    }

    /**
     * 根据传入的 {@code _class} 来指定日志对象路径，打印内容为 {@code _class} 类
     * 对象所在包的全路径名称。
     *
     * @param aClass
     *        日志对象
     *
     * @return 日志记录器
     */
    public static Logger getLogger(Class<?> aClass) {
        return loggerAdapterInstance.getLogger(aClass);
    }

    /**
     * 尝试查找最优解的日志适配器
     */
    private static void tryFindBestChoiceLoggerAdapter(Runnable runnable) {
        try {
            if (loggerAdapterInstance == null)
                runnable.run();
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    private static void slf4j() {
        try {
            Slf4jLoggerAdapter adapter = JvmRefs.newInstance(Slf4jLoggerAdapter.class);
            adapter.getLogger(LoggerFactory.class).info(formatBestChoice("slf4j"));
            loggerAdapterInstance = adapter;
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    private static void standard() {
        try {
            Slf4jLoggerAdapter adapter = JvmRefs.newInstance(Slf4jLoggerAdapter.class);
            adapter.getLogger(LoggerFactory.class).info(formatBestChoice("vortextools standard"));
            loggerAdapterInstance = adapter;
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    /** 打印当前日志框架初始化使用的适配器 */
    private static String formatBestChoice(String adapter) {
        return sprintf("Try find best choice logger adapter, current use %s adapter.", adapter);
    }

}

