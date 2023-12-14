package com.bitfashion.vortextools.test;

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

import com.alibaba.fastjson.JSON;
import com.bitfashion.libraries.fashiontools.Arrays;
import org.junit.Test;

import java.util.List;

import static com.bitfashion.libraries.fashiontools.Objects.stringOf;
import static com.bitfashion.libraries.fashiontools.collection.Collections.listOf;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class ArraysTest {

    @Test
    public void arraycopy_type_char() {
        char[] a = new char[] {
                'h', 'e', 'l', 'l', 'o',
                ',',
                ' ',
                'w', 'o', 'l', 'r', 'd', '!'
        };
        System.out.println(new String(Arrays.copyOf(a, 7, -1)));
    }

    @Test
    public void arraycopy_type_object() {
        var   h = listOf('h');
        var   e = listOf('e');
        var  l0 = listOf('l');
        var  l1 = listOf('l');
        var   o = listOf('o');
        var dot = listOf(',');
        var emp = listOf(' ');
        var   w = listOf('w');
        var  o1 = listOf('o');
        var   l = listOf('l');
        var   r = listOf('r');
        var   d = listOf('d');
        var end = listOf('!');

        List[] original = new List[] {
                h,
                e,
                l0,
                l1,
                o,
                dot,
                emp,
                w,
                o1,
                l,
                r,
                d,
                end
        };

        List<Character>[] sliced = Arrays.copyOf(original, 7, -1);
        System.out.println(JSON.toJSONString(sliced));
    }

    @Test
    public void copyof_type_string() {
        System.out.println(stringOf("Hello, Wolrd!", 7, 0));
    }

}
