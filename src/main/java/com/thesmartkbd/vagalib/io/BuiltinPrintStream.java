package com.thesmartkbd.vagalib.io;

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

/* Creates on 2023/5/4. */

import java.io.OutputStream;
import java.io.PrintStream;

import static com.thesmartkbd.vagalib.Objects.sprintf;

/**
 * 可格式化的 {@link PrintStream} 封装类
 *
 * @author thesmartkbd
 */
public class BuiltinPrintStream extends PrintStream {

    enum Color {
        // TODO 添加彩色打印
    }

    BuiltinPrintStream(OutputStream out) {
        super(out);
    }

    /**
     * 打印字符串到输出流中，并显示在控制台上。该函数是针对 {@link PrintStream#println} 函数
     * 的增强实现。实现了对字符串的格式化打印输出、色彩打印（该功能暂时搁置）等操作。方便快速打印
     * 调试信息、日志信息到控制台上。
     *
     * @see System#out
     * @see #println(Object, Object...)
     */
    public void print(Object input, Object... args) {
        super.print(sprintf(input, args));
    }

    /**
     * 打印字符串到输出流中，并显示在控制台上。该函数是针对 {@link PrintStream#println} 函数
     * 的增强实现。实现了对字符串的格式化打印输出、色彩打印（该功能暂时搁置）等操作。方便快速打印
     * 调试信息、日志信息到控制台上。
     *
     * <p>
     * 这个函数相对于 {@link #print(Object, Object...)} 来说唯一的区别就在于它会将输出的内容打印并换行。而
     * {@link #print(Object, Object...)} 是只打印内容，但是不自动换行。
     *
     * @see System#out
     */
    public void println(Object input, Object... args) {
        super.println(sprintf(input, args));
    }

}
