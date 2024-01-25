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

/* Creates on 2023/4/29. */

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * 在 Java 中有 AIO、BIO、NIO 三种通用 IO 模型，同时也提供了很多的工具类：例如支持随机
 * 读写的 RandomAccessFile 类、也有支持 BIO 通用模型的 InputStream/OutputStream 类、
 * 还有支持 NIO 的 Selector/Buffer/Channel 类。以及支持字符串读取 Reader/Writer。<p>
 *
 * 但是你们真的会用以上的工具类吗？在 Netty 中会使用 NIO，因为 Netty 是异步网络编程，需要支持
 * 高并发。但是对于文件来说写入数据通常来说是 InputStream/OutputStream。在 vortextools 工具
 * 包中想去掉一个概念，那就是 InputStream/OutputStream。一切的 IO 操作皆为 Reader/Writer<p>
 *
 * 所有的数据读写操作应该都交给 Reader/Writer 统一起来，而 Reader/Writer 不应该是只限于读取字符
 * 数据，它应该是读取字节、写入字节。<p>
 *
 * 如果你比较喜欢这里 Reader/Writer 那里 Buffer/Channel 顺便在用一手 InputStream/OutputStream 的
 * 那么你不应该在这里。你应该在某个精神病院里面呆着<p>
 *
 * 在 vortextools 中，所有的 IO 操作。最小单位都是基于 byte，不存在 int/char。
 * 但是那个 OutputStream 源码里面的 write(int b) 就有点属实超纲了，管不了一点。<p>
 *
 * 如果你喜欢用 Reader/Writer 来读取字符，你会问那我要读取字符串怎么办？您是否在找：
 *<pre>
 *     - {@link MutableFile#strread()}
 *     - {@link IOUtils#strread}
 *     - {@link IOUtils#read(InputStream)}
 *     - {@link IOUtils#read(byte[], InputStream)}
 *     - {@link IOUtils#read(byte[], int, int, InputStream)}
 *</pre>
 *
 * 如果需要写入，您是否在找：
 * <pre>
 *     - {@link IOUtils#write(byte[], MutableFile)}
 *     - {@link IOUtils#write(String, MutableFile)}
 *     - {@link IOUtils#write(InputStream, MutableFile)}
 *     - {@link IOUtils#write(byte[], OutputStream)}
 *     - {@link IOUtils#write(String, OutputStream)}
 *     - {@link IOUtils#write(InputStream, OutputStream)}
 *     - {@link IOUtils#write(byte[], int, int, OutputStream)}
 * </pre>
 *
 * @author thesmartkbd
 */
public class MutableFileWriter extends FileOutputStream {

    public MutableFileWriter(@NotNull String name) throws FileNotFoundException {
        super(name);
    }

    public MutableFileWriter(@NotNull String name, boolean append) throws FileNotFoundException {
        super(name, append);
    }

    public MutableFileWriter(@NotNull File file) throws FileNotFoundException {
        super(file);
    }

    public MutableFileWriter(@NotNull File file, boolean append) throws FileNotFoundException {
        super(file, append);
    }

    public MutableFileWriter(@NotNull FileDescriptor fdObj) {
        super(fdObj);
    }

}
