package org.venorze.vegalib.samples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 venorze                                                    *|
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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           StringSamples.java                                               *|
|* Create Time:    2024/1/29 17:59                                                  *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.junit.Test;

import static org.venorze.vegalib.Objects.*;

/**
 * 字符串样例
 *
 * @author venorze
 */
@SuppressWarnings("ALL")
public class StringSamples {

    /**
     * 使用 atos 函数将任意对象转换为成 String 对象。
     */
    @Test
    public void api_sample_atos() {
        /* byte array */
        byte[] hello_word_bytes = { 72, 101, 108, 108, 111, 87, 111, 114, 108, 100 };
        fprintlnf("atos(byte[]):  %s", atos(hello_word_bytes));

        /* object */
        Object byte_array_object = hello_word_bytes;
        fprintlnf("atos(Object):  %s", atos(byte_array_object));

        /* long */
        Long long_value = Long.MAX_VALUE;
        fprintlnf("atos(Long):    %s", atos(long_value));

        /* int */
        Integer integer_value = Integer.MAX_VALUE;
        fprintlnf("atos(Integer): %s", atos(long_value));

        /* double */
        Double double_valaue = Double.MAX_VALUE;
        fprintlnf("atos(Double):  %s", atos(double_valaue));

        /* number */
        Number number = (Number) integer_value;
        fprintlnf("atos(Number):  %s", atos(long_value));
    }

    /**
     * 使用 strxfmt 函数对字符串进行格式化操作
     */
    @Test
    public void api_sample_strfmt() {
        fprintlnf(strxfmt("Hello %s", "Word"));
    }

    /**
     * 使用 strcut 函数并配合截取规则 edx:* 截取文件后缀名
     */
    @Test
    public void api_sample_strcut() {
        fprintlnf("the file extension name: %s", strcut("Hello World.txt", "edx:.", 0));
    }

    /**
     * 使用 streq | strneq 函数判断两个字符串是否相等，使用 strieq | strineq 忽略大小写判断两个字符串
     * 是否相等。
     */
    @Test
    public void api_sample_streq_strieq() {
        Object x = "x", y = "y", z = "L", w = "l";

        fprintlnf("Variable define:");
        fprintlnf("  X = \"%s\"\n  Y = \"%s\"\n  Z = \"%s\"\n  W = \"%s\"", x, y, z, w);

        /* streq | strneq */
        fprintlnf("\nstreq | strneq");
        fprintlnf("  X equals Y:                 %b", streq(x, y));
        fprintlnf("  X not equals Y:             %b", strneq(x, y));

        /* streq | strneq */
        fprintlnf("\nstrieq | strineq");
        fprintlnf("  Z ignore case equals W:     %b", strieq(z, w));
        fprintlnf("  Z not ignore case equals W: %b", strineq(z, w));
    }

}
