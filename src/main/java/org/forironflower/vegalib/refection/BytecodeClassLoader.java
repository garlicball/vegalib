package org.forironflower.vegalib.refection;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 forironflower                                              *|
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
