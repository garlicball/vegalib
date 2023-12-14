package com.bitfashion.libraries.fashiontools;

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
 * @author bit-bitfashion
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
    public static byte[] copyOf(byte[] original, int off, int len) {
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
    public static boolean[] copyOf(boolean[] original, int off, int len) {
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
    public static char[] copyOf(char[] original, int off, int len) {
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
    public static short[] copyOf(short[] original, int off, int len) {
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
    public static int[] copyOf(int[] original, int off, int len) {
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
    public static long[] copyOf(long[] original, int off, int len) {
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
    public static float[] copyOf(float[] original, int off, int len) {
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
    public static double[] copyOf(double[] original, int off, int len) {
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
    public static <T> T[] copyOf(T[] original, int off, int len) {
        len = array_slice_length(original.length, off, len);
        Class<? extends T[]> newType = (Class<? extends T[]>) original.getClass();
        T[] ret = ((Object) newType == (Object) Object[].class)
                ? (T[]) new Object[len]
                : (T[]) Array.newInstance(newType.getComponentType(), len);
        heapcopy(original, off, ret, 0, len);
        return ret;
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
    public static <T> List<T> asList(T... a) {
        return java.util.Arrays.asList(a);
    }

}
