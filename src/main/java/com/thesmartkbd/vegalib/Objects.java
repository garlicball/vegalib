package com.thesmartkbd.vegalib;

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

/* Creates on 2023/4/30. */

import com.thesmartkbd.vegalib.collection.Collections;
import com.thesmartkbd.vegalib.exception.InvalidArgumentException;
import com.thesmartkbd.vegalib.exception.VegaRuntimeException;
import com.thesmartkbd.vegalib.io.ByteBuf;
import com.thesmartkbd.vegalib.io.VegaPrintStream;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Pattern;

import static com.thesmartkbd.vegalib.Arrays.array_copy_of;
import static com.thesmartkbd.vegalib.Assert.throwIfTrue;
import static com.thesmartkbd.vegalib.Bits.bithas;
import static com.thesmartkbd.vegalib.Optional.optionalIfError;
import static com.thesmartkbd.vegalib.collection.Collections.collectionEnd;
import static com.thesmartkbd.vegalib.io.ByteBuf.SEEK_SET;
import static com.thesmartkbd.vegalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class Objects {

    /* anycmp flag 小于符号标志位 */
    public static final Integer ACMP_LT = 0x00000001;
    /* anycmp flag 大于符号标志位 */
    public static final Integer ACMP_EQ = 0x00000002;
    /* anycmp flag 等于符号标志位 */
    public static final Integer ACMP_GT = 0x00000004;

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// Objects
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void check_index_size(int off, int len, int size) {
        throwIfTrue((off + len ) > size, "Array offset and size out of index: %s", size);
    }

    /**
     * `any`函数通常用于 Lambda 接口，将任何对象作为`Object`类型
     * 返回出去。
     */
    public static <T> T any(T type) {
        return type;
    }

    /**
     * #brief：对两个对象做 equals 操作比较<p>
     *
     * 如果两个对象相同则返回 {@code true}, 如果两个对象不相同则
     * 返回 {@code false}。
     *
     * @see Object#equals(Object)
     */
    public static boolean anyeq(Object x, Object y) {
        return (x == y) || (x != null && x.equals(y));
    }

    /**
     * #brief：对两个对象做 not equals 操作比较<p>
     *
     * 如果两个对象相同则返回 {@code false}, 如果两个对象不相同则
     * 返回 {@code true}。
     *
     * @see #anyeq(Object, Object)
     */
    public static boolean anyne(Object x, Object y) {
        return !anyeq(x, y);
    }

    /**
     * #brief：对比较两个实现了`Comparable`接口的对象实例进行比较<p>
     *
     * 对比较两个实现了`Comparable`接口的对象实例进行比较。通常用于数字、日期等可以
     * 做大于、小于、等于比较的对象。<p>
     *
     * 假设有两个`BigDecimal`对象，他们分别是`a`和`b`。这两个值需要做比较，以前我们需要这样做：
     * <pre>
     *     // 判断 a 是否大于等于 b
     *     var ret = a.compare(b)
     *     if (ret > 0 && ret == 0)
     *        ...
     * </pre>
     *
     * 如果使用`anycmp`函数只需要这样做：
     * <pre>
     *     // 判断 a 是否大于等于 b
     *     if (anycmp(a, b, ACMP_GT, ACMP_EQ))
     *         ...
     * </pre>
     *
     * @param a
     *        第一个实现了`Compare`接口的实例对象
     *
     * @param b
     *        第二个实现了`Compare`接口的实例对象
     *
     * @param flags
     *        标志位，表示是否比较大于、小于、等于，可以用bit位运算来做小于等于、
     *        大于等于的比较操作。
     *
     * @return {@code true} 表示满足 {@code flags} 要求，{@code false} 反之。
     */
    public static <T extends Comparable<T>> boolean anycmp(T a, T b, int flags) {
        throwIfTrue(bithas(flags, ACMP_LT, ACMP_GT), "大于小于符号不能同时在一个 flags 中做比较！");
        /* compare */
        int ret = a.compareTo(b);
        if (ret < 0)
            return bithas(flags, ACMP_LT);
        if (ret == 0)
            return bithas(flags, ACMP_EQ);
        /* ret > 0 */
        return bithas(flags, ACMP_GT);
    }

    /**
     * #brief: 向控制台格式化打印输出<p>
     *
     * @param fmt
     *        格式化字符串
     *
     * @param args
     *        参数
     */
    public static void fprintlnf(Object fmt, Object... args) {
        fprintlnf(stdout, fmt, args);
    }

    /**
     * #brief: 向指定的输出流格式化打印输出<p>
     *
     * @param stream
     *        输出流
     *
     * @param fmt
     *        格式化字符串
     *
     * @param args
     *        参数
     */
    public static void fprintlnf(OutputStream stream, Object fmt, Object... args) {
        new VegaPrintStream(stream).println(snprintf(fmt, args));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// int
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * #brief：自动识别 {@code obj} 类型，并转换成 Integer<p>
     *
     * 自动识别 {@code obj} 类型，并转换成 Integer，如果 {@code obj} 是 String
     * 类型，则会通过 {@code Integer.valueOf()} 方法转换。如果是 byte 则通过字节
     * 缓冲区进行转换。
     *
     * @param obj
     *        要被转换的对象
     *
     * @return 转换后的 int 类型数据。
     */
    public static int atoi(Object obj) {
        if (obj instanceof Integer)
            return (Integer) obj;
        if (obj instanceof byte[])
            return atoi((byte[]) obj);
        return Integer.parseInt(atos(obj));
    }

    /**
     * #brief：从 {@code b[0]} 往后取 4 个字节转为 int 类型<p>
     *
     * 从 {@code b[off]} 往后取 4 个字节转为 int 类型。数组长度必须保证不能
     * 小于 4 个字节。
     *
     * @param b
     *        字节数组，数组长度最低不能小于 4 个字节
     *
     * @return 转换后的 int 类型数据。
     */
    public static int atoi(byte[] b) {
        return atoi(b, 0);
    }

    /**
     * #brief：从 {@code b[off]} 往后取 4 个字节转为 int 类型<p>
     *
     * 从 {@code b[off]} 往后取 4 个字节转为 int 类型。数组长度必须保证不能
     * 小于 4 个字节。如果使用了 {@code off} 字段。那么必须保证 {@code b.length - off >= 4}。
     * 如果不满足这些条件则抛出数组越界异常。
     *
     * @param b
     *        字节数组，数组长度最低不能小于 4 个字节
     *
     * @param off
     *        偏移量，从 {@code b[off]} 往后取 4 个字节。
     *
     * @return 转换后的 int 类型数据。
     */
    public static int atoi(byte[] b, int off) {
        return ByteBuf.wrap(b, off, Integer.BYTES)
                .seek(SEEK_SET, 0).readInt();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// long
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * #brief：自动识别 {@code obj} 类型，并转换成 Long<p>
     *
     * 自动识别 {@code obj} 类型，并转换成 Long，如果 {@code obj} 是 String
     * 类型，则会通过 {@code Long.valueOf()} 方法转换。如果是 byte 则通过字节
     * 缓冲区进行转换。
     *
     * @param obj
     *        要被转换的对象
     *
     * @return 转换后的 long 类型数据。
     */
    public static long atol(Object obj) {
        if (obj instanceof Long)
            return (Long) obj;
        if (obj instanceof Number)
            return ((Number) obj).longValue();
        if (obj instanceof byte[])
            return atol((byte[]) obj);
        return Long.parseLong(atos(obj));
    }

    /**
     * #brief：从 {@code b[0]} 往后取 8 个字节转为 long 类型<p>
     *
     * 从 {@code b[off]} 往后取 8 个字节转为 long 类型。数组长度必须保证不能
     * 小于 8 个字节。
     *
     * @param b
     *        字节数组，数组长度最低不能小于 8 个字节
     *
     * @return 转换后的 long 类型数据。
     */
    public static long atol(byte[] b) {
        return atol(b, 0);
    }

    /**
     * #brief：从 {@code b[off]} 往后取 8 个字节转为 long 类型<p>
     *
     * 从 {@code b[off]} 往后取 8 个字节转为 long 类型。数组长度必须保证不能
     * 小于 8 个字节。如果使用了 {@code off} 字段。那么必须保证 {@code b.length - off >= 8}。
     * 如果不满足这些条件则抛出数组越界异常。
     *
     * @param b
     *        字节数组，数组长度最低不能小于 8 个字节
     *
     * @param off
     *        偏移量，从 {@code b[off]} 往后取 8 个字节。
     *
     * @return 转换后的 long 类型数据。
     */
    public static long atol(byte[] b, int off) {
        return ByteBuf.wrap(b, off, Long.BYTES)
                .seek(SEEK_SET, 0).readLong();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// boolean
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 将 {@link Object} 对象实例转换成`Boolean`类型的对象实例。修改原来的 {@link Object} 对象不会对
     * 新的创建的`Boolean`对象实例有任何影响，并且这个函数也不会抛出异常。如果对象是 {@code null} 的话那
     * 么则返回 {@code false}
     *
     * @param obj
     *        {@code Object} 对象实例
     *
     * @return 根据传入的其他对象实例转换成`Boolean`对象实例。
     *
     * @see String#valueOf(Object)
     */
    public static boolean atobool(Object obj) {
        if (obj instanceof Boolean)
            return (Boolean) obj;
        if (obj instanceof Number)
            return ((Number) obj).intValue() > 0;
        String bool = atos(obj, Objects::strlower);
        return strxmatch(bool, "true|on|y|yes");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// 字符串工具类，该工具类中所有的字符串参数对象应该使用 {@link Object} 来
    /// 代替，而不是使用 {@link String}。
    ///
    /// <p>字符串工具类使用方式不应该是 {@code Objects.streq()} 而是应该
    /// 直接使用 {@code streq()} 函数名调用。不应该添加 Objects 前缀。
    ///
    /// <p>该工具类中带有 “x” 的函数名都是有自己的特殊含义的，比如 streq 和 strxeq 带有
    /// “x” 的功能都要强一点。
    ///
    /// @author thesmartkbd
    ///
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 将 {@link Object} 对象实例转换成 {@link String} 类型的对象实例。修改原来的 {@link Object} 对象不会对
     * 新的创建的 {@link String} 对象实例有任何影响。该函数也不会抛出异常，如果传入的对象为 {@code null} 的话
     * 那么新创建出来的字符串就会从常量池中返回一个 "null" 字符串出去。<p>
     *
     * 这个函数提供数据处理 Lambda 函数，可通过 Lambda 处理返回数据。示例：
     * <pre>
     *     // 将字符串转为小写
     *     var str = atos("0xABCDEF", Objects::strlower);
     * </pre>
     *
     * @param obj
     *        {@code Object} 对象实例
     *
     * @param mapper
     *        数据映射函数
     *
     * @return 根据传入的其他对象实例转换成 {@code String} 对象实例。
     *
     * @see String#valueOf(Object)
     */
    public static String atos(Object obj, VegaObjectMapper<String, String> mapper) {
        return mapper.apply(atos(obj));
    }

    /**
     * 将 {@link Object} 对象实例转换成 {@link String} 类型的对象实例。修改原来的 {@link Object} 对象不会对
     * 新的创建的 {@link String} 对象实例有任何影响。该函数也不会抛出异常，如果传入的对象为 {@code null} 的话
     * 那么新创建出来的字符串就会从常量池中返回一个 "null" 字符串出去。
     *
     * @param obj
     *        {@code Object} 对象实例
     *
     * @return 根据传入的其他对象实例转换成 {@code String} 对象实例。
     *
     * @see String#valueOf(Object)
     */
    public static String atos(Object obj) {
        if (obj == null)
            return "";
        if (obj instanceof String)
            return (String) obj;
        /* 字节数组转字符串 */
        if (obj instanceof byte[])
            return atos((byte[]) obj, 0, ((byte[]) obj).length);
        /* 字符数组转字符串 */
        if (obj instanceof char[])
            return atos((char[]) obj, 0, ((char[]) obj).length);
        return String.valueOf(obj);
    }

    /**
     * 通过 {@code obj} 子字符串、{@code off} 数组偏移量和 {@code len} 长度分配
     * 一个新的字符串对象。{@code off} 是字符数组中第一个字符的索引位置。{@code len} 参数
     * 是整个字符串的长度。
     *
     * @param obj
     *        子字符串对象实例
     *
     * @param off
     *        子字符串中第一个字符开始的索引位置
     *
     * @param len
     *        新的字符串长度，该长度不能超过 {@code sub} 被截取的子字符串长度
     *
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     *
     * @throws NullPointerException
     *          {@code sub} 子字符串参数不能为空
     *
     * @throws ArrayIndexOutOfBoundsException
     *          如果参数 {@code len} 超出整个子字符串的大小后会抛出数组越界异常。
     *
     * @see #atos(String, int, int)
     */
    public static String atos(Object obj, int off, int len) {
        return atos(atos(obj), off, len);
    }

    /**
     * 通过 {@code sub} 子字符串、{@code off} 数组偏移量和 {@code len} 长度分配
     * 一个新的字符串对象。{@code off} 是字符数组中第一个字符的索引位置。{@code len} 参数
     * 是整个字符串的长度。
     *
     * @param sub
     *          子字符串对象实例
     *
     * @param off
     *          子字符串中第一个字符开始的索引位置
     *
     * @param len
     *          新的字符串长度，该长度不能超过 {@code sub} 被截取的子字符串长度。如果长度
     *          是 {@code 0} 那么表示到最后，如果是负数，表示截取到 len - n
     *
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     *
     * @throws NullPointerException
     *          {@code sub} 子字符串参数不能为空
     *
     * @throws ArrayIndexOutOfBoundsException
     *          如果参数 {@code len} 超出整个子字符串的大小后会抛出数组越界异常。
     *
     * @see #atos(char[], int, int)
     */
    public static String atos(String sub, int off, int len) {
        return atos(sub.toCharArray(), off, len);
    }

    /**
     * 通过 {@code b} 字节数组分配一个新的字符串对象。
     * <p>
     * 分配新的字符串后，修改字节数组不会对新分配的字符串造成影响。
     *
     * @param b
     *        字节数组
     *
     * @return 新的字符串由传入的 {@code b} 字节数组转的字符串文本
     *
     * @see String#String(byte[])
     */
    public static String atos(byte[] b) {
        return atos(b, 0, b.length);
    }

    /**
     * 通过 {@code b} 字节数组、{@code off} 数组偏移量和 {@code len} 长度分配
     * 一个新的字符串对象。{@code off} 是字符数组中第一个字符的索引位置。{@code len} 参数
     * 是整个字符串的长度。
     * <p>
     * 分配新的字符串后，修改字节数组不会对新分配的字符串造成影响。
     *
     * @param b
     *        字节数组
     *
     * @param off
     *        字节数组中第一个字符开始的索引位置
     *
     * @param len
     *        新的字符串长度，该长度不能超过 {@code b} 字节数组长度
     *
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     *
     * @throws ArrayIndexOutOfBoundsException
     *          如果参数 {@code len} 超出整个字符数组的大小后会抛出数组越界异常。
     *
     * @see String#String(byte[], int, int)
     */
    public static String atos(byte[] b, int off, int len) {
        return new String(array_copy_of(b, off, len));
    }

    /**
     * 通过 {@code a} 字符数组、{@code off} 数组偏移量和 {@code len} 长度分配
     * 一个新的字符串对象。{@code off} 是字符数组中第一个字符的索引位置。{@code len}
     * 参数是整个字符串的长度。
     *
     * <p>分配新的字符串后，修改字符数组不会对新分配的字符串造成影响。
     *
     * @param a
     *        被截取的字符数组
     *
     * @param off
     *        字符数组中第一个字符开始的索引位置
     *
     * @param len
     *        新的字符串长度，该长度不能超过 {@code a} 被截取的字符数组长度
     *
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     *
     * @throws ArrayIndexOutOfBoundsException
     *          如果参数 {@code len} 超出整个字符数组的大小后会抛出数组越界异常。
     *
     * @see String#String(char[], int, int)
     */
    public static String atos(char[] a, int off, int len) {
        return new String(array_copy_of(a, off, len));
    }

    /**
     * 正则表达式对象缓存
     */
    private static final Map<String, Pattern> compiled =
            new WeakHashMap<>();

    /**
     * #brief: 获取字符串长度<p>
     *
     * 获取字符串长度信息，如果对象为 {@code null} 或空，那么则返回 {@code 0}，反之
     * 返回字符串真实长度。
     *
     * @param source
     *        可以转为 String 的对象
     *
     * @return 字符串长度
     */
    public static int strlen(Object source) {
        return atos(source).length();
    }

    /**
     * 校验一个字符串是为空，如果字符串为 {@code null} 或字符串内部是
     * 空字符则返回 {@code true} 表示当前字符串是一个空字符串。反之返回
     * {@code false}.
     *
     * @param obj
     *        校验的字符串参数
     *
     * @return {@code true} 表示字符串为空，反之 {@code false}
     */
    public static boolean strempty(Object obj) {
        return obj == null || "".equals(atos(obj).trim());
    }

    /**
     * 校验一个字符串是否不为空，如果字符串为 {@code null} 或字符串内部是
     * 空字符则返回 {@code false} 表示当前字符串是一个空字符串。反之返回
     * {@code false}.
     *
     * @param obj
     *        校验的字符串参数
     *
     * @return {@code true} 表示字符串不为空，反之 {@code false}
     */
    public static boolean strnempty(Object obj) {
        return !strempty(obj);
    }

    /**
     * 判断 {@code input} 字符串中是否包含 {@code has} 字符对象。如果包含则返回
     * {@code true} 否则返回 {@code false}.
     *
     * @param obj
     *        输入的字符串对象
     *
     * @param has
     *       包含某个字符
     *
     * @return {@code true} 表示 {@code input} 字符串种包含了 {@code has} 字符。
     */
    public static boolean strhas(Object obj, Object has) {
        return atos(obj).contains(atos(has));
    }

    /**
     * #brief：将字符串转为小写字符串<p>
     *
     * @param obj
     *        输入的字符串对象
     *
     * @return 转换后的全小写字符串
     */
    public static String strlower(Object obj) {
        return atos(obj).toLowerCase();
    }

    /**
     * #brief：将字符串转为大写字符串<p>
     *
     * @param obj
     *        输入的字符串对象
     *
     * @return 转换后的全大写字符串
     */
    public static String strupper(Object obj) {
        return atos(obj).toUpperCase();
    }

    /**
     * #brief：忽略大小写比较两个字符串是否相等<p>
     *
     * @param a 一个对象
     * @param b 与另一个对象做比较
     * @return 如果两个对象的内容相等，那么返回 {@code true} 反之返回 {@code false}
     *
     * @see Objects#anyeq(Object, Object)
     */
    public static boolean strieq(Object a, Object b) {
        return streq(atos(a, Objects::strlower), atos(b, Objects::strlower));
    }

    /**
     * #brief：忽略大小写比较两个字符串是否不相等<p>
     *
     * @param a 一个对象
     * @param b 与另一个对象做比较
     * @return 如果两个对象的内容相等，那么返回 {@code true} 反之返回 {@code false}
     *
     * @see Objects#anyeq(Object, Object)
     */
    public static boolean strineq(Object a, Object b) {
        return !strieq(a, b);
    }

    /**
     * 判断两个字符串对象内容是否相等，该函数通过调用该 {@link Objects#anyeq(Object, Object)} 进行
     * 比较操作。比较两个对象的内存地址或两个对象的内容是否相等。
     *
     * @param a 一个对象
     * @param b 与另一个对象做比较
     * @return 如果两个对象的内容相等，那么返回 {@code true} 反之返回 {@code false}
     *
     * @see Objects#anyeq(Object, Object)
     */
    public static boolean streq(Object a, Object b) {
        return anyeq(atos(a), atos(b));
    }

    /**
     * 判断两个字符串对象内容是否不相等，该函数通过调用该 {@link Objects#anyeq(Object, Object)} 进行
     * 比较操作。比较两个对象的内存地址或两个对象的内容是否相等。
     *
     * <p>当然了，此函数也可以用于两个对象做比较。
     *
     * @param a 一个对象
     * @param b 与另一个对象做比较
     *
     * @return 如果两个对象的内容不相等，那么返回 {@code true} 反之返回 {@code false}
     *
     * @see Objects#anyeq(Object, Object)
     */
    public static boolean strneq(Object a, Object b) {
        return !streq(a, b);
    }

    /**
     * #brief：比较多个字符串中是否包含被比较的字符串<p>
     *
     * 多个字符串与被比较的字符串做比较，如果 {@code a} 数组中包含 {@code cmp} 那么
     * 则返回 {@code true}, 反之如果 {@code a} 数组不包含 {@code cmp} 那么则返回
     * {@code false}。
     *
     * <p> X: 可以使用多个字符串进行同时比较
     *
     * @param cmp
     *        被比较的字符串对象
     *
     * @param a
     *        与 {@code cmp} 做比较的字符串列表
     *
     * @return {@code a} 中包含 {@code cmp} 则返回 {@code true}，
     *         反之返回 {@code false}。
     */
    public static boolean strxeq(Object cmp, Object... a) {
        for (Object value : a)
            if (streq(cmp, value))
                return true;
        return false;
    }

    /**
     * #brief：比较多个字符串中是否不包含被比较的字符串<p>
     *
     * 多个字符串与被比较的字符串做比较，如果 {@code a} 数组中不包含 {@code cmp} 那么
     * 则返回 {@code true}, 反之如果 {@code a} 数组不包含 {@code cmp} 那么则返回
     *
     * <p> X: 可以使用多个字符串进行同时比较
     *
     * @param cmp
     *        被比较的字符串对象
     *
     * @param a
     *        与 {@code cmp} 做比较的字符串列表
     *
     * @return {@code a} 中不包含 {@code cmp} 则返回 {@code true}，
     *         反之返回 {@code false}。
     */
    public static boolean strxneq(Object cmp, Object... a) {
        return !strxeq(cmp, a);
    }

    /**
     * 返回一个格式化后的字符串类型，{@code fmt} 为原字符串，在这个字符串中如果要指定某个
     * 地方需要被格式化，则添加上 %s 占位符。
     * <p>
     * 伪代码示例（格式化 Hello World）：
     * <pre>
     *      var text = "Hello %s";
     *      println(Objects.snprintf(text, "World"));
     * </pre>
     *
     * @param fmt 未被格式化的原字符串。字符串中需要携带占位符 %s，如果没有这个符号
     *            那么函数将不会格式化任何内容。
     *
     * @param args 格式化参数，对应 %s 占位符的个数
     *
     * @return 返回被格式化后的字符串
     */
    public static String snprintf(Object fmt, Object... args) {
        return String.format(atos(fmt), args);
    }

    /**
     * 返回一个格式化后的字符串类型，{@code fmt} 为原字符串，在这个字符串中如果要指定某个
     * 地方需要被格式化，则添加上 {@code markerCharacter} 占位符。
     * <p>
     * 参数 {@code markerCharacter} 是指定占位符，我们都知道日志框架如（log4j, slf4j）这些
     * 框架格式化的占位符是 “{}” 当你需要替换某个地方的值的时候，只需要在字符串中添加上日志框架
     * 对应的占位符 “{}” 即可。
     * <p>
     * 伪代码示例（格式化 Hello World）：
     * <pre>
     *      var text = "Hello {}";
     *      println(Objects.snprintf_marker_character(text, "{}", "World"));
     * </pre>
     *
     * 由于这个格式化函数只处理 {@code markerCharacter} 并不处理其他的任何内容。所以它经过测试大约比 JDK 自带的
     * 格式化函数快 40 多倍。请尽量使用这个函数去对字符串进行格式化。<p>
     *
     * X: 这个格式化函数可以自定义占位符。
     *
     * @param fmt 未被格式化的原字符串。字符串中需要携带占位符 %s，如果没有这个符号
     *            那么函数将不会格式化任何内容。
     *
     * @param markerCharacter 占位符，如果标记
     *
     * @param args 格式化参数，对应 %s 占位符的个数
     *
     * @return 返回被格式化后的字符串
     */
    public static String xsnprintf(Object fmt, String markerCharacter, Object... args) {
        // markerCharacter 属性定义
        int markerLength = markerCharacter.length();
        char markerBegin = markerCharacter.charAt(0);
        List<Integer> markerCoordinates = Collections.<Integer>listOf();

        // 遍历字符串，获取占位符位置
        char[] fmtChars = atos(fmt).toCharArray();
        for (int i = 0; i < fmtChars.length; i++) {
            if (fmtChars[i] == markerBegin && (fmtChars.length - i) >= markerLength
                    && streq(atos(fmtChars, i, markerLength), markerCharacter)) {
                markerCoordinates.add(i);
                i += markerLength - 1;
            }
        }

        if (args.length != markerCoordinates.size())
            throw new InvalidArgumentException("占位符和目标参数个数不匹配，占位符个数：%s，参数个数：%s",
                    markerCoordinates.size(), args.length);

        StringBuilder builder = new StringBuilder();
        // 上一个占位符的位置
        int lcoord = 0;
        for (int i = 0; i < markerCoordinates.size(); i++) {
            int skipCount = 0;
            int markerCoordinate = markerCoordinates.get(i);
            // 添加解析后的字符到builder
            if (markerCoordinate == 0) {
                builder.append(args[i]);
            } else {
                skipCount = markerCoordinate - lcoord;
                builder.append(fmtChars, lcoord, skipCount);
                builder.append(args[i]);
            }
            lcoord += markerLength + skipCount;
        }

        // 如果存在占位符，那么将最后一段原文本添加到 Builder 中
        if (!markerCoordinates.isEmpty()) {
            int idx = collectionEnd(markerCoordinates) + markerCharacter.length();
            builder.append(fmtChars, idx, fmtChars.length - idx);
        } else {
            builder.append(fmt);
        }

        return builder.toString();
    }

    /**
     * #brief: 根据指定规则获取子字符串开始索引下标。<p>
     *
     * 根据指定规则获取子字符串开始索引下标，通过 idx 和 edx 判断是从前往后查询还是从后
     * 往前查询字符串索引下表。<p>
     *
     * 假设我们当前需要截取一个文件后缀，我们可以这样使用：
     * <code>
     *     // edx: 表示从后往前查找索引，也就是 lastIndexOf()，idx: 表示从前往后查找，也就是 indexOf()
     *     strcut("Hello World.txt", 0, "edx:.");
     * </code>
     *
     * @param obj
     *        字符串对象或任意可被 toString() 的对象
     *
     * @param regex
     *        索引查找规则，idx: 表示从前往后查询，edx: 表示从后往前查询。
     *
     * @return 返回字符串索引
     */
    public static int stridxof(Object obj, String regex) {
        String substr = strcut(regex, "idx:".length(), 0);
        /* index of */
        if (regex.startsWith("idx:"))
            return atos(obj).indexOf(substr);

        /* last index of */
        if (regex.startsWith("edx:"))
            return atos(obj).lastIndexOf(substr);

        /* err */
        throw new VegaRuntimeException("Error stridxof regex err!, Please use idx: or edx: prefix, example: idx:< edx:>!");
    }

    /**
     * 字符串裁剪，它和 {@link String#substring(int, int)} 的功能是一样的，因为这个
     * 函数只是做了个一个 {@code substring} 的调用，它的出现是为了让代码更简洁。
     *
     * @param obj
     *        一个 {@link Object} 对象，通过 {@code toString()} 转换成 {@code String}
     *        类型。
     *
     * @param off
     *        开始索引
     *
     * @param len
     *        结束索引
     *
     * @return 返回截取好的字符串
     */
    public static String strcut(Object obj, int off, int len) {
        return atos(obj, off, len);
    }

    /**
     * 字符串裁剪，它和 {@link String#substring(int, int)} 的功能是一样的，因为这个
     * 函数只是做了个一个 {@code substring} 的调用，它的出现是为了让代码更简洁。
     *
     * @param obj
     *        一个 {@link Object} 对象，通过 {@code toString()} 转换成 {@code String}
     *        类型。
     *
     * @param regex_off
     *        开始索引，使用 stridxof 规则，详情请查看 {@link #stridxof} 函数使用规则。
     *
     * @param len
     *        结束索引
     *
     * @return 返回截取好的字符串
     */
    public static String strcut(Object obj, String regex_off, int len) {
        return strcut(obj, stridxof(obj, regex_off), len);
    }

    /**
     * 字符串裁剪，它和 {@link String#substring(int, int)} 的功能是一样的，因为这个
     * 函数只是做了个一个 {@code substring} 的调用，它的出现是为了让代码更简洁。
     *
     * @param obj
     *        一个 {@link Object} 对象，通过 {@code toString()} 转换成 {@code String}
     *        类型。
     *
     * @param off
     *        开始索引
     *
     * @param regex_end
     *        结束索引，使用 stridxof 规则，详情请查看 {@link #stridxof} 函数使用规则。
     *
     * @return 返回截取好的字符串
     */
    public static String strcut(Object obj, int off, String regex_end) {
        return strcut(obj, off, stridxof(obj, regex_end));
    }

    /**
     * 字符串裁剪，它和 {@link String#substring(int, int)} 的功能是一样的，因为这个
     * 函数只是做了个一个 {@code substring} 的调用，它的出现是为了让代码更简洁。
     *
     * @param obj
     *        一个 {@link Object} 对象，通过 {@code toString()} 转换成 {@code String}
     *        类型。
     *
     * @param regex_off
     *        开始索引，使用 stridxof 规则，详情请查看 {@link #stridxof} 函数使用规则。
     *
     * @param regex_end
     *        结束索引，使用 stridxof 规则，详情请查看 {@link #stridxof} 函数使用规则。
     *
     * @return 返回截取好的字符串
     */
    public static String strcut(Object obj, String regex_off, String regex_end) {
        return strcut(obj, stridxof(obj, regex_off), stridxof(obj, regex_end));
    }

    /**
     * 字符串分割，分割一个字符串为多个子字符串。并使用 {@code String[]} 对象
     * 数组返回分割结果。
     *
     * <p>可以指定分隔符，并且可以使用正则表达式进行分割。
     *
     * @param obj
     *        字符串对象
     *
     * @param regexp
     *        正则表达式
     *
     * @return 分割后的多个子字符串
     */
    public static String[] strtok(Object obj, String regexp) {
        return atos(obj).split(regexp);
    }

    /**
     * 字符串分割，分割一个字符串为多个子字符串。并使用 {@code String[]} 对象
     * 数组返回分割结果。并且每个 token 都去除前后空格
     *
     * <p>可以指定分隔符，并且可以使用正则表达式进行分割。
     *
     * <p> X: 每个 token 都会去除前后空格
     *
     * @param obj
     *        字符串对象
     *
     * @param regexp
     *        正则表达式
     *
     * @return 分割后的多个子字符串，并去除前后空格
     */
    public static String[] strxtok(Object obj, String regexp) {
        String[] ret = strtok(obj, regexp);
        for (int i = 0; i < ret.length; i++)
            ret[i] = ret[i].trim();
        return ret;
    }

    /**
     * 替换 {@code obj} 字符串中所有满足 {@code regexp} 正则表达式要求
     * 的字符。将这些字符替换为参数 {@code replacement}。
     *
     * @param obj
     *        字符串对象
     *
     * @param regexp
     *        正则表达式
     *
     * @param replacement
     *        替换的字符串
     *
     * @return 替换后的字符串
     */
    public static String strrep(Object obj, String regexp, Object replacement) {
        return atos(obj).replaceAll(regexp, atos(replacement));
    }

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数<p>
     *
     * 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数。如果参数 {@code obj} 中
     * 包含 {@code regexp} 则返回 {@code true}，反之返回 {@code false}。<p>
     *
     * 这个函数默认不启用 {@code Pattern} 正则表达还匹配模式对象缓存。如需使用缓存
     * 请调用 {@link #strxmatch(Object, String)}
     *
     * @param obj
     *       字符串对象
     *
     * @param regexp
     *        正则表达式规则
     *
     * @return 如果 {@code obj} 中满足正则表达式 {@code regexp} 匹配条件则
     *         返回 {@code true}，反之返回 {@code false}。
     */
    public static boolean strmatch(Object obj, String regexp) {
        return strxmatch(obj, regexp, false);
    }

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数<p>
     *
     * 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数。如果参数 {@code obj} 中
     * 包含 {@code regexp} 则返回 {@code true}，反之返回 {@code false}。<p>
     *
     * X: 这个函数默认启用 {@code Pattern} 正则表达还匹配模式对象缓存。性能
     *    更高！
     *
     * @param obj
     *       字符串对象
     *
     * @param regexp
     *        正则表达式规则
     *
     * @return 如果 {@code obj} 中满足正则表达式 {@code regexp} 匹配条件则
     *         返回 {@code true}，反之返回 {@code false}。
     */
    public static boolean strxmatch(Object obj, String regexp) {
        return strxmatch(obj, regexp, true);
    }

    private static Pattern _patternCacheComputeIfAbsent(String regexp) {
        /* 如果正则表达式编译规则不存在，则编译正则并保存。存在则直接
           获取数据返回。*/
        return compiled.computeIfAbsent(regexp, k -> Pattern.compile(regexp));
    }

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数<p>
     *
     * 使用正则表达式匹配字符串中是否包含 {@code regexp} 参数。如果参数 {@code obj} 中
     * 包含 {@code regexp} 则返回 {@code true}，反之返回 {@code false}。
     *
     * @param obj
     *       字符串对象
     *
     * @param regexp
     *        正则表达式规则
     *
     * @param enablePatternCache
     *        是否开启 Pattern 对象缓存，如果开启了缓存则会从缓存中查找已经
     *        编译好的 Pattern 对象。
     *
     * @return 如果 {@code obj} 中满足正则表达式 {@code regexp} 匹配条件则
     *         返回 {@code true}，反之返回 {@code false}。
     */
    private static boolean strxmatch(Object obj,
                                     String regexp,
                                     boolean enablePatternCache) {
        Pattern pattern = enablePatternCache ?
                _patternCacheComputeIfAbsent(regexp) : Pattern.compile(regexp);
        assert pattern != null;
        return pattern.matcher(atos(obj)).find();
    }

    /**
     * #biref：判断一个字符串是否是数字<p>
     *
     * 判断一个字符串是否是数字，如果是数字则返回 {@code true}，反之返回
     * {@code false}。
     *
     * @param obj
     *        任意对象类型，通过 {@code atos()} 转换成 String
     *
     * @return {@code true} 表示当前字符串是一个数字，反之返回 {@code false}.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean strdig(Object obj) {
        return optionalIfError(() -> Double.parseDouble(atos(obj)), true, false);
    }

}
