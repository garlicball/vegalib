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

/* Creates on 2023/4/29. */

import org.venorze.vegalib.Objects;
import org.venorze.vegalib.exception.ReadException;
import org.venorze.vegalib.exception.VegaIOException;
import org.venorze.vegalib.exception.WriteException;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.venorze.vegalib.Assert.throwIfError;
import static org.venorze.vegalib.Objects.atos;

/**
 * IO操作工具包，整合大部分IO操作，使得在Java中更多的IO操作
 * 变得简单、简洁、易用。
 *
 * @author venorze
 */
public class IOUtils {

    /**
     * 文件、流、缓冲区结尾符号
     */
    public static final int EOF = -1;
    /**
     * 数据大小单位定义，1KB
     */
    public static final int  KB = Byte.BYTES * 1024;
    /**
     * 数据大小单位定义，1MB
     */
    public static final int MB = KB * 1024;
    /**
     * 数据大小单位定义，1GB
     */
    public static final int GB = MB * 1024;
    /**
     * 推荐缓冲区默认大小
     */
    public static final int DEFAULT_BYTE_BUFFER_SIZE = KB * 4;
    /**
     * 基于当前操作系统下的换行符字符串
     */
    public static final String OS_LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * 标准输入流
     */
    public static final InputStream stdin = System.in;
    /**
     * 标准错误流
     */
    public static final VegaPrintStream stderr
            = new VegaPrintStream(System.err);
    /**
     * 标准输出流
     */
    public static final VegaPrintStream stdout
            = new VegaPrintStream(System.out);

    /**
     * 关闭所有实现了 {@link Closeable} 的对象实例，并且不需要捕获
     * {@link IOException} 异常。
     * <p>
     * 非常安静的关闭文件、流等对象。
     *
     * @param closeable 实现了 {@link Closeable} 的对象实例。
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null)
            throwIfError(closeable::close);
    }

    /**
     * 这个方法会读取整个 {@code stream} 中的数据到新创建的字节数组中，数据读完以后这个函数会自动
     * 关闭输入流，外部无需手动关闭流。
     * <p>
     * 如果当某次 {@code read()} 操作时出现异常，就会直接停止读取内容并且抛出异常。但是字节流内部的指针数据位置
     * 并不会重置。所以当出现一个异常到时候，这个输入流 {@link InputStream} 就应该停止使用。
     * <p>
     * 当数据读取完毕后，返回值 {@code int} 类型是读取的字节总数，如果已经读取到末尾了，那么则会返回
     * 代表 EOF 的值 {@link #EOF}。
     *
     * @param stream
     *        输入流 {@link InputStream} 对象实例
     *
     * @return 读取写入到 {@code b} 字节缓冲区的总字节数。如果读到末尾则返回 {@link #EOF}
     */
    public static byte[] read(InputStream stream) {
        try {
            ByteBuf buffer = ByteBuf.allocate();
            byte[] tmp = new byte[DEFAULT_BYTE_BUFFER_SIZE];
            int len;
            while ((len = read(tmp, stream)) != EOF)
                buffer.write(tmp, 0, len);
            return buffer.toByteArray();
        } catch (Exception e) {
            throw new ReadException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * 这个方法会从 {@code stream} 中读数据存入到 {@code b} 字节数组缓冲区中。读取的数据长度
     * 是 {@code b.length} 缓冲区大小。缓冲区多大，那么就读取多少数据。预估好缓冲区的大小能够
     * 很方便的读取配置文件、XML、等一切以文本作为标记的文件。
     * <p>
     * 如果当某次 {@code read()} 操作时出现异常，就会直接停止读取内容并且抛出异常。但是字节流内部的指针数据位置
     * 并不会重置。所以当出现一个异常到时候，这个输入流 {@link InputStream} 就应该停止使用。
     * <p>
     * 当数据读取完毕后，返回值 {@code int} 类型是读取的字节总数，如果已经读取到末尾了，那么则会返回
     * 代表 EOF 的值 {@link #EOF}。<p>
     *
     * 请注意：该方法不会自动关闭流，需要调用者手动关闭 {@code stream}。
     *
     * @param b
     *        字节缓冲区
     *
     * @param stream
     *        输入流 {@link InputStream} 对象实例
     *
     * @return 读取写入到 {@code b} 字节缓冲区的总字节数。如果读到末尾则返回 {@link #EOF}
     */
    public static int read(byte[] b, InputStream stream) {
        return read(b, 0, b.length, stream);
    }

    /**
     * 这个方法会从 {@code stream} 中读数据存入到 {@code b} 字节数组缓冲区中。读取到的第一个字节
     * 从 {@code b[off]} 开始存放，一直读到字节流的末尾。如果字节流中数据长度大于 {@code len} 的话，
     * 那么数据最多从 {@code b[off]} 读取到 {@code b[len]}。
     * <p>
     * 如果当某次 {@code read()} 操作时出现异常，就会直接停止读取内容并且抛出异常。但是字节流内部的指针数据位置
     * 并不会重置。所以当出现一个异常到时候，这个输入流 {@link InputStream} 就应该停止使用。
     * <p>
     * 当数据读取完毕后，返回值 {@code int} 类型是读取的字节总数，如果已经读取到末尾了，那么则会返回
     * 代表 EOF 的值 {@link #EOF}。<p>
     *
     * 请注意：该方法不会自动关闭流，需要调用者手动关闭 {@code stream}。
     *
     * @param b
     *        字节缓冲区
     *
     * @param off
     *        缓冲区开始位置的偏移量
     *
     * @param len
     *        读取内容总长度，最大不能超过 {@code b.length}
     *
     * @param stream
     *        输入流 {@link InputStream} 对象实例
     *
     * @return 读取写入到 {@code b} 字节缓冲区的总字节数。如果读到末尾则返回 {@link #EOF}
     */
    public static int read(byte[] b, int off, int len,
                           InputStream stream) {
        try {
            return stream.read(b, off, len);
        } catch (Throwable e) {
            throw new ReadException(e);
        }
    }

    /**
     * 将输入流中的数据作为字符串文本读取，该函数会读取整个输入流中的数据并将它转为字符串
     * 类型返回。该函数会自动关闭输入流，外部无需手动关闭。
     *
     * @param stream
     *        输入流
     *
     * @return 从输入流中读取到的字符串文本
     */
    public static String strread(InputStream stream) {
        return Objects.atos(read(stream));
    }

    /**
     * 读取整个 {@code input} 输入流的数据，然后写入到指定的文件对象输出流中。
     * 如果文件不存在，则会创建。并且每次写入完成后都会关闭输出流。同时这个函数也
     * 支持大于2GB的数据拷贝，可用于两个数据量较大的对象相互拷贝使用。<p>
     *
     * 如果需要频繁写入不同的数据，建议在外部维护 {@code file} 的输出流
     * 对象。<p>
     *
     * 当正在执行对象拷贝的时候，一次性最大会读取 16kb 的数据到 JVM 内存
     * 缓冲区中。<p>
     *
     * 这个函数会自动关闭 {@code input} 输入流，无需调用者手动关闭输入流。
     *
     * @param input
     *        输入流
     *
     * @param file
     *        {@link VegaFile} 文件对象实例（如果文件不存在，则会创建）
     */
    public static void write(InputStream input, VegaFile file) {
        VegaFileWriter writer = null;
        try {
            writer = file.openWriter();
            write(input, writer);
        } catch (Exception e) {
            throw new VegaIOException(e);
        } finally {
            closeQuietly(writer);
        }
    }

    /**
     * 写入字符串 {@code input} 到指定的文件输出流中，字符串以字节流的形式写入。
     * 如果比较关注字符串编码建议使用 {@link #write(byte[], OutputStream)}
     * 函数来代替当前函数做写入操作。<p>
     *
     * 数据写入完成后会自动关闭文件的输出流。
     *
     * @param input
     *        字符串
     *
     * @param file
     *        {@link VegaFile} 文件对象实例（如果文件不存在，则会创建）
     */
    public static void write(String input, VegaFile file) {
        write(input.getBytes(), file);
    }

    /**
     * 将整个 {@code b} 字节数组缓冲区的内容写入到指定的文件输出流 {@code file}。
     * 这个函数不需要捕获任何异常，它能 “安静” 的写入数据。<p>
     *
     * 数据写入完成后会自动关闭文件的输出流。
     *
     * @param b
     *        字节数组缓冲区
     *
     * @param file
     *        指定输出流
     */
    public static void write(byte[] b, VegaFile file) {
        VegaFileWriter writer = null;
        try {
            writer = file.openWriter();
            write(b, writer);
        } catch (Throwable e) {
            throw new VegaIOException(e);
        } finally {
            closeQuietly(writer);
        }
    }

    /**
     * 读取整个 {@code input} 输入流的数据，然后写入到指定的 {@code stream}
     * 输出流中。同时这个函数也支持大于2GB的数据拷贝，可用于两个数据量较大的
     * 对象相互拷贝使用。
     * <p>
     * 当正在执行对象拷贝的时候，一次性最大会读取 16kb 的数据到 JVM 内存
     * 缓冲区中。
     * <p>
     * 这个函数会自动关闭 {@code input} 输入流，无需调用者手动关闭输入流。
     *
     * @param input
     *        输入流
     *
     * @param stream
     *        指定输出流
     */
    public static void write(InputStream input, OutputStream stream) {
        try {
            byte[] buf = new byte[DEFAULT_BYTE_BUFFER_SIZE];
            int len;
            while ((len = read(buf, input)) != EOF)
                write(buf, 0, len, stream);
        } catch (Throwable e) {
            throw new VegaIOException(e);
        } finally {
            /* 如果出现异常关闭输入流，因为输入流中的数据已经被读取，所以
             * 这个函数可以替开发者将输入流关闭。 */
            closeQuietly(input);
        }
    }

    /**
     * 写入字符串 {@code input} 到指定的输出流中，字符串以字节流的形式写入。如果比较
     * 关注字符串编码建议使用 {@link #write(byte[], OutputStream)} 函数来代替当前
     * 函数做写入操作。
     *
     * @param input
     *        字符串
     *
     * @param stream
     *        指定输出流
     */
    public static void write(String input, OutputStream stream) {
        byte[] bytes = input.getBytes();
        write(bytes, 0, bytes.length, stream);
    }

    /**
     * 将整个 {@code b} 字节数组缓冲区的内容写入到指定的输出流 {@code stream}。
     * 这个函数不需要捕获任何异常，它能 “安静” 的写入数据。
     *
     * @param b
     *        字节数组缓冲区
     *
     * @param stream
     *        指定输出流
     */
    public static void write(byte[] b, OutputStream stream) {
        write(b, 0, b.length, stream);
    }

    /**
     * #brief: 写入一个字节数组到输出输出流中<p>
     *
     * 写入一个字节数组到输出输出流中。内部只是调用了 {@link OutputStream#write(byte[], int, int)}
     * 方法执行写入操作。它的作用是能安静的调用任何 {@code write} 函数做写入操作，不需要强制
     * 去捕获异常信息。
     * <p>
     * 该函数可以写入任何继承了 {@link OutputStream} 的子类。
     *
     * @param b
     *        要写入的字节数组
     *
     * @param off
     *        字节数组起始位置偏移量，写入的时候从 {@code b[off]} 开始读取
     *        数据。
     *
     * @param len
     *        写入数据的总长度，这个长度不能大于 {@code b.length} 同时它也
     *        不能小于 {@code off}
     *
     * @param stream
     *        继承了 {@link OutputStream} 的所有子类
     */
    public static void write(byte[] b, int off, int len,
                             OutputStream stream) {
        try {
            stream.write(b, off, len);
        } catch (IOException e) {
            throw new WriteException(e);
        }
    }

}
