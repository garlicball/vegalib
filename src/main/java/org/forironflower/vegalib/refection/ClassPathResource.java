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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           ClassPathResource.java                                           *|
|* Create Time:    2024/1/29 19:35                                                  *|
|* Author:         forironflower                                                    *|
|* EMail:          forironflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.forironflower.vegalib.io.IOUtils;
import org.forironflower.vegalib.io.VegaFile;

import java.io.InputStream;

/**
 * ClassPath 目录下的资源文件
 *
 * @author forironflower
 */
public class ClassPathResource {

    private final ClassLoader classLoader;

    private final VegaFile fd;

    public ClassPathResource(String name) {
        this.fd = new VegaFile(name);
        this.classLoader = this.getClass().getClassLoader();
    }

    /**
     * #brief: 获取 classpath 下文件的输入数据流<p>
     *
     * 获取 classpath 下输入数据流，可以自定义数据读取方式。方便支持更多的功能。
     * 数据读取可以使用 {@link IOUtils} 下的函数进行处理。
     *
     */
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(fd.name());
    }

    /**
     * #brief: 读取文件 classpath 下文件所有字节数据<p>
     *
     * 读取 classpath 下指定文件所有数据，以字节形式返回。resource 下的文件通常来说
     * 打包在 jar 文件内部。所以一般不会太大。可以一次性读取数据
     *
     */
    public byte[] read() {
        return IOUtils.read(getInputStream());
    }

    /**
     * #brief: 读取文件 classpath 下文件所有字符串数据<p>
     *
     * 读取 classpath 下指定文件所有数据，以字符串形式返回。resource 下的文件通常来说
     * 打包在 jar 文件内部。所以一般不会太大。可以一次性读取数据
     *
     */
    public String strread() {
        return IOUtils.strread(getInputStream());
    }

}
