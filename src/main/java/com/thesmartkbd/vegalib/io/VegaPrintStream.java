package com.thesmartkbd.vegalib.io;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 thesmartkbd                                                *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/5/4. */

import java.io.OutputStream;
import java.io.PrintStream;

import static com.thesmartkbd.vegalib.Assert.throwIfNull;
import static com.thesmartkbd.vegalib.Objects.strfmt;

/**
 * 可格式化的 {@link PrintStream} 封装类
 *
 * @author thesmartkbd
 */
public class VegaPrintStream extends PrintStream {

    enum Color {
        // TODO 添加彩色打印
    }

    public VegaPrintStream(OutputStream out) {
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
        super.print(strfmt(input, args));
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
        super.println(strfmt(input, args));
    }

}
