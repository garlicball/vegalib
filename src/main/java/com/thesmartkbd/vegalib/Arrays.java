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

/* Creates on 2023/9/6. */

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/**
 * 数组工具类
 *
 * @author thesmartkbd
 */
public class Arrays {

    /**
     * @return 返回数组被截断后的长度
     */
    private static int array_slice_length(int size, int off, int len) {
        return len <= 0 ? (size - off) - Math.abs(len) : len;
    }

    /**
     * #brief：数组内存拷贝，将数组元素拷贝到另一个数组
     *
     * <P>该函数命名使用 c 语言中的内存拷贝函数命名。用于替换 {@link System#arraycopy} 函数
     * 使用，简化调用方式。可以拷贝任意数组对象。底层由 JVM C++ Native 方法实现。调用者无需
     * 关心实现。
     *
     * @param src
     *        原数组，被拷贝的数组对象。
     *
     * @param srcPos
     *        开始偏移量，从 {@code src[srcPos]} 作为拷贝起始位置。
     *
     * @param dest
     *        目标数组对象，将拷贝的数据放入 {@code dest} 对象中。
     *
     * @param destPos
     *        开始偏移量，从 {@code dest[destPos]} 作为拷贝起始位置。
     *
     * @param length
     *        拷贝长度，从偏移位置开始拷贝 {@code length} 个元素。
     */
    @SuppressWarnings("all")
    public static void heapcopy(Object src, int  srcPos,
                                Object dest, int destPos,
                                int length) {
        java.lang.System.arraycopy(src, srcPos, dest, destPos, length);
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static byte[] array_copy_of(byte[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        byte[] ret = new byte[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static boolean[] array_copy_of(boolean[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        boolean[] ret = new boolean[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static char[] array_copy_of(char[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        char[] ret = new char[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static short[] array_copy_of(short[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        short[] ret = new short[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static int[] array_copy_of(int[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        int[] ret = new int[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static long[] array_copy_of(long[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        long[] ret = new long[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static float[] array_copy_of(float[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        float[] ret = new float[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static double[] array_copy_of(double[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        double[] ret = new double[len];
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] array_copy_of(T[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        Class<? extends T[]> newType = (Class<? extends T[]>) original.getClass();
        T[] ret = ((Object) newType == (Object) Object[].class)
                ? (T[]) new Object[len]
                : (T[]) Array.newInstance(newType.getComponentType(), len);
        heapcopy(original, off, ret, 0, len);
        return ret;
    }

    public static <T> List<T> array_as_list(T[] original, int off, int len) {
        return array_as_list(array_copy_of(original, off, len));
    }

    /**
     * Returns a fixed-size list backed by the specified array. Changes made to
     * the array will be visible in the returned list, and changes made to the
     * list will be visible in the array. The returned list is
     * {@link Serializable} and implements {@link RandomAccess}.
     *
     * <p>The returned list implements the optional {@code Collection} methods, except
     * those that would change the size of the returned list. Those methods leave
     * the list unchanged and throw {@link UnsupportedOperationException}.
     *
     * @apiNote
     * This method acts as bridge between array-based and collection-based
     * APIs, in combination with {@link Collection#toArray}.
     *
     * <p>This method provides a way to wrap an existing array:
     * <pre>{@code
     *     Integer[] numbers = ...
     *     ...
     *     List<Integer> values = Arrays.asList(numbers);
     * }</pre>
     *
     * <p>This method also provides a convenient way to create a fixed-size
     * list initialized to contain several elements:
     * <pre>{@code
     *     List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
     * }</pre>
     *
     * <p><em>The list returned by this method is modifiable.</em>
     * To create an unmodifiable list, use
     * {@link Collections#unmodifiableList Collections.unmodifiableList}
     * or <a href="List.html#unmodifiable">Unmodifiable Lists</a>.
     *
     * @param <T> the class of the objects in the array
     * @param a the array by which the list will be backed
     * @return a list view of the specified array
     * @throws NullPointerException if the specified array is {@code null}
     */
    @SafeVarargs
    public static <T> List<T> array_as_list(T... a) {
        return java.util.Arrays.asList(a);
    }


    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt>
     * is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(long[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(int)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt> is
     * <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(int[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(short)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt>
     * is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(short[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(char)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt>
     * is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(char[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements
     * are separated by the characters <tt>", "</tt> (a comma followed
     * by a space).  Elements are converted to strings as by
     * <tt>String.valueOf(byte)</tt>.  Returns <tt>"null"</tt> if
     * <tt>a</tt> is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(byte[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(boolean)</tt>.  Returns <tt>"null"</tt> if
     * <tt>a</tt> is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(boolean[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(float)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt>
     * is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(float[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(double)</tt>.  Returns <tt>"null"</tt> if <tt>a</tt>
     * is <tt>null</tt>.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @since 1.5
     */
    public static String array_to_string(double[] a) {
        return java.util.Arrays.toString(a);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * If the array contains other arrays as elements, they are converted to
     * strings by the {@link Object#toString} method inherited from
     * <tt>Object</tt>, which describes their <i>identities</i> rather than
     * their contents.
     *
     * <p>The value returned by this method is equal to the value that would
     * be returned by <tt>Arrays.asList(a).toString()</tt>, unless <tt>a</tt>
     * is <tt>null</tt>, in which case <tt>"null"</tt> is returned.
     *
     * @param a the array whose string representation to return
     * @return a string representation of <tt>a</tt>
     * @see #deepToString(Object[])
     * @since 1.5
     */
    public static String array_to_string(Object[] a) {
        return java.util.Arrays.toString(a);
    }

}
