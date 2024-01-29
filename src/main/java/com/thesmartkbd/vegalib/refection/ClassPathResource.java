package com.thesmartkbd.vegalib.refection;

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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           ClassPathResource.java                                           *|
|* Create Time:    2024/1/29 19:35                                                  *|
|* Author:         thesmartkbd                                                      *|
|* EMail:          thesmartkbd@hotmail.com                                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.thesmartkbd.vegalib.io.IOUtils;

import java.io.InputStream;

/**
 * ClassPath 目录下的资源文件
 *
 * @author thesmartkbd
 */
public class ClassPathResource {

    /**
     * 输入流
     */
    private final InputStream stream;

    public ClassPathResource(String path) {
        this.stream = ClassUtils.getResourceStream(path);
    }

    /**
     * #brief: 使用字符串流读取文件中的所有数据作为字符串返回
     */
    public byte[] read() {
        return IOUtils.read(stream);
    }

    /**
     * #brief: 使用字符串流读取文件中的所有数据作为字符串返回
     */
    public String strread() {
        return IOUtils.strread(stream);
    }

    /**
     * #brief: 获取输入流
     */
    public InputStream getInputStream() {
        return stream;
    }

}
