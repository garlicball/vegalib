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

import org.jetbrains.annotations.NotNull;
import org.venorze.vegalib.annotations.Favorite;
import org.venorze.vegalib.exception.OpenException;
import org.venorze.vegalib.exception.VegaIOException;
import org.venorze.vegalib.exception.VegaRuntimeException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.venorze.vegalib.Assert.throwIfTrue;
import static org.venorze.vegalib.Objects.*;
import static org.venorze.vegalib.Optional.optionalIfNull;

/**
 * Java标准库文件增强版封装
 *
 * @author venorze
 */
@Favorite
public class VegaFile extends File {

    private static final File[] EMPTY_DIRECTORY = new File[0];

    private static boolean isLinux;

    static {
        isLinux = strihas(System.getProperty("os.name"), "linux");
    }

    /**
     * 通过 {@link File} 对象创建一个新的 {@link #VegaFile} 实例对象。将
     * 给定的 file 转换成 {@link #VegaFile} 对象实例。<p>
     *
     * 如果给定 file 为空，则会抛出空指针异常，
     *
     * @param file
     *        file对象实例
     *
     * @throws NullPointerException 如果 {@code file} 为 null
     */
    public VegaFile(File file) {
        super(file.getPath());
    }

    /**
     * 通过 pathname 创建一个新的 {@link #VegaFile} 实例对象。将
     * 给定的 pathname 转换成一个相对路径。如果给定 pathname 为空字符串，
     * 那么结果就是空的相对路径名。
     *
     * @param pathname
     *        路径名称字符串
     *
     * @throws NullPointerException 如果 {@code pathname} 为 null
     */
    public VegaFile(String pathname) {
        super(asLinuxPath(pathname));
    }

    /**
     * 通过 pathname 创建一个新的 {@link #VegaFile} 实例对象。将
     * 给定的 pathname 转换成一个相对路径。如果给定 pathname 为空字符串，
     * 那么结果就是空的相对路径名。<p>
     *
     * 这个构造函数可以格式化 {@code pathname} 字符串。
     *
     * @param pathname
     *        路径名称字符串
     *
     * @param args
     *        格式化参数
     *
     * @throws NullPointerException 如果 {@code pathname} 为 null
     */
    public VegaFile(String pathname, Object... args) {
        this(org.venorze.vegalib.Objects.strxfmt(asLinuxPath(pathname), args));
    }

    /**
     * Creates a new {@code File} instance by converting the given
     * {@code file:} URI into an abstract pathname.
     *
     * <p> The exact form of a {@code file:} URI is system-dependent, hence
     * the transformation performed by this constructor is also
     * system-dependent.
     *
     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
     *
     * <blockquote>
     * new File(</code><i>&nbsp;f</i>.{@link #toURI()
     * toURI}()).equals(</code><i>&nbsp;f</i>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </code></blockquote>
     *
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  This relationship typically does not hold,
     * however, when a {@code file:} URI that is created in a virtual machine
     * on one operating system is converted into an abstract pathname in a
     * virtual machine on a different operating system.
     *
     * @param  uri
     *         An absolute, hierarchical URI with a scheme equal to
     *         {@code "file"}, a non-empty path component, and undefined
     *         authority, query, and fragment components
     *
     * @throws  NullPointerException
     *          If {@code uri} is {@code null}
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on the parameter do not hold
     *
     * @see #toURI()
     * @see URI
     * @since 1.4
     */
    public VegaFile(URI uri) {
        super(uri);
    }

    /**
     * 传入一个 {@link URL} 对象实例，这个构造函数会通过 {@link URL} 对象中内置的
     * {@link URL#getFile()} 方法转换成 {@link VegaFile} 中的抽象路径。然后调用
     * {@link VegaFile#VegaFile(String)} 函数创建出一个实例对象。
     *
     * @param url
     *        {@link URL} 对象实例
     *
     * @throws NullPointerException
     *         {@code url} 参数为 {@code null}
     */
    public VegaFile(URL url) {
        super(url.getFile());
    }

    /**
     * Returns the absolute pathname StringUtils of this abstract pathname.
     *
     * <p> If this abstract pathname is already absolute, then the pathname
     * StringUtils is simply returned as if by the {@link #getPath}
     * method.  If this abstract pathname is the empty abstract pathname then
     * the pathname StringUtils of the current users directory, which is named by the
     * system property {@code users.dir}, is returned.  Otherwise this
     * pathname is resolved in a system-dependent way.  On UNIX systems, a
     * relative pathname is made absolute by resolving it against the current
     * users directory.  On Microsoft Windows systems, a relative pathname is made absolute
     * by resolving it against the current directory of the drive named by the
     * pathname, if any; if not, it is resolved against the current users
     * directory.
     *
     * @return  The absolute pathname StringUtils denoting the same file or
     *          directory as this abstract pathname
     *
     * @throws  SecurityException
     *          If a required system property value cannot be accessed.
     *
     * @see     File#isAbsolute()
     */
    @NotNull
    @Override
    public String getAbsolutePath() {
        return asLinuxPath(super.getAbsolutePath());
    }

    /**
     * Converts this abstract pathname into a pathname StringUtils.  The resulting
     * StringUtils uses the {@link #separator default name-separator character} to
     * separate the names in the name sequence.
     *
     * @return  The StringUtils form of this abstract pathname
     */
    @NotNull
    @Override
    public String getPath() {
        return asLinuxPath(super.getPath());
    }

    @NotNull
    @Override
    public File[] listFiles() {
        return optionalIfNull(super.listFiles(), EMPTY_DIRECTORY);
    }

    /**ev
     * @return 返回一个不带后缀的文件名称
     */
    public String getCleanName() {
        String name = strcut(getName(), 0, "edx+1:.");
        return !name.endsWith(".") ? name : strcut(name, 0, -1);
    }

    /**
     * @return 返回文件的后缀名称
     */
    public String getExtension() {
        return strcut(getName(), "edx:.", 0);
    }

    /**
     * 匹配当前文件名后缀是否和参数 {@code extension} 一致。如果类型一致，返回 {@code true}
     * 否则返回 {@code false}
     *
     * @param extension
     *        文件后缀类型名称
     *
     * @return {@code true} 表示后缀匹配成功，反之匹配不成功。
     */
    public boolean typeEquals(String extension) {
        return getName().endsWith(extension);
    }

    /**
     * Returns the pathname StringUtils of this abstract pathname's parent, or
     * {@code null} if this pathname does not name a parent directory.
     *
     * <p> The <em>parent</em> of an abstract pathname consists of the
     * pathname's prefix, if any, and each name in the pathname's name
     * sequence except for the last.  If the name sequence is empty then
     * the pathname does not name a parent directory.
     *
     * @return  The pathname StringUtils of the parent directory named by this
     *          abstract pathname, or {@code null} if this pathname
     *          does not name a parent
     */
    @Override
    public String getParent() {
        return asLinuxPath(super.getParent());
    }

    /**
     * Returns the abstract pathname of this abstract pathname's parent,
     * or {@code null} if this pathname does not name a parent
     * directory.
     *
     * <p> The <em>parent</em> of an abstract pathname consists of the
     * pathname's prefix, if any, and each name in the pathname's name
     * sequence except for the last.  If the name sequence is empty then
     * the pathname does not name a parent directory.
     *
     * @return  The abstract pathname of the parent directory named by this
     *          abstract pathname, or {@code null} if this pathname
     *          does not name a parent
     *
     * @since 1.2
     */
    public VegaFile parentVegaFile() {
        File parentFile = getParentFile();
        return parentFile == null ? null : new VegaFile(parentFile);
    }

    /**
     * 删除由当前文件或目录。如果你删除的是目录，必须确保目录不能为空，否则是
     * 无法删除的。
     *
     * @return {@code true} 表示删除成功，{@code false} 反之。
     */
    private boolean builtin_force_delete() {
        boolean retval;

        if (!exists())
            return true;

        if (!(retval = delete())) {
            System.gc();
            retval = delete();
        }

        return retval;
    }

    /**
     * 强制删除目录，需要确保当前文件对象的路径是表示的一个目录。否则会直接删除文件。
     * 如果目录下有内容，会递归遍历并进行删除。
     *
     * @return {@code true} 表示删除成功，{@code false} 反之。
     */
    private boolean builtin_force_delete_directory() {
        if (!exists())
            return true;

        if (isFile())
            return builtin_force_delete();

        for (VegaFile child : Objects.requireNonNull(openDirectory())) {
            if (child.isDirectory())
                child.builtin_force_delete_directory();
            child.builtin_force_delete();
        }

        return delete();
    }

    /**
     * 删除当前文件或目录，根据 {@link #isDirectory()} 函数返回结果调用内部删除方法。
     * 如果当前对象是一个目录，那么则会删除整个目录包括目录下的所有文件。
     *
     * @return {@code true} 表示删除成功，{@code false} 反之。
     */
    @Favorite(keyword = {
            "api_sample_file_force_delete"
    })
    public boolean forceDelete() {
        return isDirectory() ? builtin_force_delete_directory() : builtin_force_delete();
    }

    /**
     * 如果当前 {@link VegaFile} 对象不是一个目录，那么调用该函数则会返回 {@code null}，反之返回目录下的
     * 所有文件列表信息。如何确定一个 {@link VegaFile} 对象是不是目录可以调用内置的 {@link #isDirectory()}
     * 函数来判断这个对象它是否是一个目录。
     * <p>
     * 这个函数可能会返回 {@code null} 值，基于一种情况下会返回 {@code null} 值。当前 {@link VegaFile}
     * 对象 {@link #isDirectory()} 返回 {@code false} 时，它不是目录但是调用了该函数会返回 {@code null}。
     *
     * @return 当前目录下在所有子文件列表信息，如果当前对象不是一个目录或者目录下说时
     *         会返回 {@code null}。
     */
    public VegaDirectory openDirectory() {
        return new VegaDirectory(this);
    }

    private static void checkMutableFile(VegaFile mutableFile) {
        throwIfTrue(mutableFile.isDirectory(), "VortexFile 对象类型必须是文件，不能是目录");
    }

    /**
     * 打开文件输入流并且不强制抛出异常，如果这个函数的异常信息需要处理。可以使用 try/catch
     * 捕获异常信息。如果不需要特殊处理，那么则无需使用 try/catch。
     * <p>
     * 安静的打开输入流对象，避免代码过于沉余。
     *
     * @return 文件的输入流对象实例
     */
    public VegaFileReader openReader() {
        try {
            checkMutableFile(this);
            return new VegaFileReader(this);
        } catch (Throwable e) {
            throw new OpenException(e);
        }
    }

    /**
     * 打开文件输出流并且不强制抛出异常，如果这个函数的异常信息需要处理。可以使用 try/catch
     * 捕获异常信息。如果不需要特殊处理，那么则无需使用 try/catch。默认以覆盖方式写入数据。当
     * 写入新数据后已有的数据会被删除。
     * <p>
     * 安静的打开输出流对象，避免代码过于沉余。
     *
     * @return 文件的输出流对象实例
     */
    public VegaFileWriter openWriter() {
        return openWriter(false);
    }

    /**
     * 打开文件输出流并且不强制抛出异常，如果这个函数的异常信息需要处理。可以使用 try/catch
     * 捕获异常信息。如果不需要特殊处理，那么则无需使用 try/catch。
     * <p>
     * 安静的打开输出流对象，避免代码过于沉余。
     *
     * @return 文件的输出流对象实例
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public VegaFileWriter openWriter(boolean append) {
        try {
            if (!exists()) {
                parentVegaFile().mkdirs();
                createNewFile();
            }
            checkMutableFile(this);
            return new VegaFileWriter(this, append);
        } catch (Throwable e) {
            throw new OpenException(e);
        }
    }

    /**
     * 拷贝文件到指定目录
     */
    @Favorite(keyword = {
            "api_sample_file_copy"
    })
    public void copy(String path) {
        IOUtils.write(openReader(), new VegaFile(path));
    }

    /**
     * 移动文件到指定目录
     */
    @Favorite(keyword = {
            "api_sample_file_move"
    })
    public VegaFile move(String path) {
        try {
            Files.move(toPath(), Paths.get(asLinuxPath(path)));
            return new VegaFile(path);
        } catch (IOException e) {
            throw new VegaRuntimeException(e);
        }
    }

    /**
     * 将文件内容以字节流的形式读取，并返回字节数组。如果文件内容是保存的二进制数据，并且
     * 文件过大可能会造成内存溢出等情况。所以在使用该函数时，请确保文件内容是以字符串保
     * 存的。并且文件大小不超过 JVM 堆的最大值。
     *
     * @return 以字节数组的形式返回文件内容
     */
    public byte[] read() {
        return IOUtils.read(openReader());
    }

    /**
     * 将文件内容以字符串的形式读取，并返回字符串。如果文件内容是保存的二进制数据，并且
     * 文件过大可能会造成内存溢出等情况。所以在使用该函数时，请确保文件内容是以字符串保
     * 存的。并且文件大小不超过 JVM 堆的最大值。
     *
     * @return 以字符串的形式返回文件内容
     */
    public String strread() {
        return IOUtils.strread(openReader());
    }

    /**
     * #brief：将字节数据写入到文件中<p>
     *
     * 将字节数据写入到文件中，默认以追加方式写入数据不会覆盖写入，且如果文件不存在则会自动创建。
     * 如果希望不追加数据写入，可以使用 {@link #write} 函数。
     *
     * @param buf
     *        写入字节数组数据
     */
    public void awrite(byte[] buf) {
        awrite(buf, 0, buf.length);
    }

    public void awrite(byte[] buf, int off, int len) {
        write0(buf, off, len, openWriter(true));
    }

    /**
     * #brief：将字节数据写入到文件中<p>
     *
     * 将字节数据写入到文件中，默认以覆盖方式写入数据不会追加写入，且如果文件不存在则会自动创建。
     * 如果希望不覆盖数据写入，可以使用 {@link #awrite} 函数。
     *
     * @param buf
     *        写入字节数组数据
     */
    public void write(byte[] buf) {
        write(buf, 0, buf.length);
    }

    public void write(byte[] buf, int off, int len) {
        write0(buf, off, len, openWriter());
    }

    private void write0(byte[] buf, int off, int len, VegaFileWriter writer) {
        try {
            IOUtils.write(buf, off, len, writer);
        } catch (Exception e) {
            throw new VegaIOException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * @return 返回文件内容大小，单位以字节表示。
     */
    public long size() {
        return exists() ? length() : 0L;
    }

    /**
     * 如果当前操作是 Linux，那么会将路径转换成 Linux 下的路径风格。如果
     * 操作系统非 Linux，则只会将 Windows 下的 '\' 替换为 '/'
     */
    private static String asLinuxPath(String path) {
        path = path.replaceAll("\\\\", "/");
        String[] names = strtok(path, "/");
        StringBuilder pathBuilder = new StringBuilder();
        for (String name : names) {
            if (strhas(name, " "))
                pathBuilder.append(strxfmt("'%s'/", name));
            else
                pathBuilder.append(strxfmt("%s/", name));
        }
        return strcut(pathBuilder, 0, -1);
    }

}
