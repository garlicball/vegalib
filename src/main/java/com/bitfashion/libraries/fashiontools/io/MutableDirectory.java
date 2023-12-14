package com.bitfashion.libraries.fashiontools.io;

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

/* Creates on 2023/5/11. */

import com.bitfashion.libraries.fashiontools.Assert;
import com.bitfashion.libraries.fashiontools.exception.ValidationException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static com.bitfashion.libraries.fashiontools.Objects.streq;
import static com.bitfashion.libraries.fashiontools.collection.Collections.listMap;
import static com.bitfashion.libraries.fashiontools.collection.Collections.listOf;

/**
 * 目录对象，提供用于操作目录相关的函数。比如获取目录大小，获取目录文件、
 * 查询等功能。
 *
 * @author bit-bitfashion
 */
public class MutableDirectory implements Iterable<MutableFile> {

    private final MutableFile vf;

    /**
     * 通过 {@code pathname} 创建一个新的 {@link #MutableDirectory} 实例对象。将
     * 给定的 {@code pathname} 参数转换成一个相对路径，并且这个相对路径它必须是一个
     * 文件夹目录，如果不是目录的话则会抛出断言异常。
     *
     * @param pathname
     *        路径名称字符串
     *
     * @throws ValidationException 如果 {@code pathname} 不是目录
     */
    public MutableDirectory(String pathname) {
        this(new MutableFile(pathname));
    }

    /**
     * 通过 {@link MutableFile vf} 创建一个新的 {@link #MutableDirectory} 实例对象。将
     * 给定的 {@link MutableFile vf} 参数转换成一个相对路径，并且这个相对路径它必须是一个
     * 文件夹目录，如果不是目录的话则会抛出断言异常。
     *
     * @param vf
     *        {@link MutableFile} 目录对象实例
     *
     * @throws ValidationException 如果 {@link MutableFile vf} 不是目录
     */
    public MutableDirectory(MutableFile vf) {
        Assert.throwIfFalse(vf.isDirectory(), "VortexFile object instance not a valid directory.");
        this.vf = vf;
    }

    /**
     * 查找当前文件夹下的文件，通过名称匹配，名称需要携带后缀一起。需要注意的是
     * 这个函数不支持无后缀匹配。如果找到的文件则直接返回文件的 {@link MutableFile}
     * 对象实例。如果没有找到则返回 {@code null}。
     *
     * @param findname
     *        查找文件名称
     *
     * @return 对应名称的 {@link MutableFile} 对象实例或是 {@code null}
     */
    public MutableFile find(String findname) {
        for (MutableFile ivf : this)
            if (streq(ivf.name(), findname))
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
    public List<MutableFile> match(String regexp) {
        List<MutableFile> retval = listOf();
        Pattern pattern = Pattern.compile(regexp);
        for (MutableFile ivf : this)
            if (pattern.matcher(ivf.name()).find())
                retval.add(ivf);
        return retval;
    }

    @Override
    public Iterator<MutableFile> iterator() {
        return openDirectory().iterator();
    }

    /**
     * 这是个内部函数，每次遍历文件夹下的文件时都需要调用它来获取目录下所有文件列表。
     * 并且这个函数不做缓存处理，避免有新的文件增加时刷新不及时导致获取不到文件的问题。
     */
    private List<MutableFile> openDirectory() {
        var lf = vf.listFiles();
        if (lf == null)
            return Collections.emptyList();
        return listMap(lf, MutableFile::new);
    }

}
