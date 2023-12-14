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

import com.alibaba.fastjson.JSONObject;
import com.bitfashion.libraries.fashiontools.Objects;
import org.junit.Test;

import static com.bitfashion.libraries.fashiontools.collection.Collections.*;
import static com.bitfashion.libraries.fashiontools.io.IOUtils.stdout;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class CollectionsTest {

    @Test
    public void listDiffMapperTest() {
        // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
        var numstrs = listOf("1", "2", "3");
        // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
        var numints = listOf(1, 2, 3, 4, 5, 6);
        // 根据数字类型，取差集，预期结果为：[4，5，6]
        var ret = listDiff(numints, numstrs, Objects::intOf); // 通过 intOf 将 string 转为 int 做比较

        // 打印输出结果
        stdout.println("预期结果：%s", JSONObject.toJSONString(ret));
    }

    @Test
    public void listIntTest() {
        var a = listOf("a", "b", "c");
        var b = listOf("b", "c", "d");
        stdout.println(listInt(a, b));
    }

    @Test
    public void listIntTest2() {
        // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
        var numstrs = listOf("1", "2", "3");
        // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
        var numints = listOf(1, 2, 3, 4, 5, 6);
        // 根据数字类型，取差集，预期结果为：[1, 2, 3]
        var ret = listInt(numints, numstrs, Objects::intOf); // 通过 intOf 将 string 转为 int 做比较
    }

    @Test
    public void listSymmDiffTest() {
        var nums1 = listOf(1, 2, 3, 4);
        var nums2 = listOf(3, 4, 5, 6);
        stdout.println(listSymmDiff(nums1, nums2));
    }

}
