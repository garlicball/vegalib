package com.bitfashion.libraries.fashiontools.refection;

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

/* Creates on 2019/5/16. */

/**
 * 自定义类加载器，用于加载内存中的 Class 字节码数据。该类不支持从文件
 * 中加载类。
 *
 * @author luotiansheng
 */
public class BytecodeClassLoader extends ClassLoader {

    /**
     * 从内存中加载类
     *
     * @param bytecode
     *        字节码数组
     *
     * @param off
     *        起始偏移量
     *
     * @param len
     *        字节码长度
     *
     * @return 从字节码数组加载类到 JVM 内存。
     */
    public Class<?> forMemory(byte[] bytecode, int off, int len) {
        return null;
    }

}
