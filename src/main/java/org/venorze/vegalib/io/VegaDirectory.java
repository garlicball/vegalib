package org.venorze.vegalib.io;

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

/* Creates on 2023/5/11. */

import org.venorze.vegalib.Assert;
import org.venorze.vegalib.annotations.Favorite;
import org.venorze.vegalib.exception.ValidationException;
import org.venorze.vegalib.Objects;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static org.venorze.vegalib.collection.Collections.listMap;
import static org.venorze.vegalib.collection.Collections.listOf;

/**
 * 目录对象，提供用于操作目录相关的函数。比如获取目录大小，获取目录文件、
 * 查询等功能。
 *
 * @author venorze
 */
@Favorite
public class VegaDirectory implements Iterable<VegaFile> {

    private final VegaFile vf;

    /**
     * 通过 {@code pathname} 创建一个新的 {@link #VegaDirectory} 实例对象。将
     * 给定的 {@code pathname} 参数转换成一个相对路径，并且这个相对路径它必须是一个
     * 文件夹目录，如果不是目录的话则会抛出断言异常。
     *
     * @param pathname
     *        路径名称字符串
     *
     * @throws ValidationException 如果 {@code pathname} 不是目录
     */
    public VegaDirectory(String pathname) {
        this(new VegaFile(pathname));
    }

    /**
     * 通过 {@link VegaFile vf} 创建一个新的 {@link #VegaDirectory} 实例对象。将
     * 给定的 {@link VegaFile vf} 参数转换成一个相对路径，并且这个相对路径它必须是一个
     * 文件夹目录，如果不是目录的话则会抛出断言异常。
     *
     * @param vf
     *        {@link VegaFile} 目录对象实例
     *
     * @throws ValidationException 如果 {@link VegaFile vf} 不是目录
     */
    public VegaDirectory(VegaFile vf) {
        Assert.throwIfFalse(vf.isDirectory(), "VortexFile object instance not a valid directory.");
        this.vf = vf;
    }

    /**
     * 查找当前文件夹下的文件，通过名称匹配，名称需要携带后缀一起。需要注意的是
     * 这个函数不支持无后缀匹配。如果找到的文件则直接返回文件的 {@link VegaFile}
     * 对象实例。如果没有找到则返回 {@code null}。
     *
     * @param findname
     *        查找文件名称
     *
     * @return 对应名称的 {@link VegaFile} 对象实例或是 {@code null}
     */
    public VegaFile find(String findname) {
        for (VegaFile ivf : this)
            if (Objects.streq(ivf.name(), findname))
                return ivf;
        return null;
    }

    /**
     * 通过正则表达式匹配文件名称，匹配出所有满足表达式条件的文件。这个函数会返回多个值，
     * 因为使用正则匹配结果可能有多个，所以它的返回类型是一个 {@code List<VortexFile>} 对象。
     *
     * <p> 假设我们需要匹配一个文件夹下所有文件名以 'a' 开头的文件或文件夹，我们只需要像下面的伪代码
     * 一样操作即可：
     * <pre>
     *     // 匹配所有名称以 'j' 开头的文件
     *     new VortexDirectory("/home/").match("^a.*")
     * </pre>
     *
     * @param regexp
     *        正则表达式
     *
     * @return 匹配到的结果列表。
     */
    public List<VegaFile> match(String regexp) {
        List<VegaFile> retval = org.venorze.vegalib.collection.Collections.listOf();
        Pattern pattern = Pattern.compile(regexp);
        for (VegaFile ivf : this)
            if (pattern.matcher(ivf.name()).find())
                retval.add(ivf);
        return retval;
    }

    @NotNull
    @Override
    public Iterator<VegaFile> iterator() {
        return openDirectory().iterator();
    }

    /**
     * 这是个内部函数，每次遍历文件夹下的文件时都需要调用它来获取目录下所有文件列表。
     * 并且这个函数不做缓存处理，避免有新的文件增加时刷新不及时导致获取不到文件的问题。
     */
    private List<VegaFile> openDirectory() {
        File[] lf = vf.listFiles();
        if (lf == null)
            return Collections.emptyList();
        return org.venorze.vegalib.collection.Collections.listMap(lf, VegaFile::new);
    }

}
