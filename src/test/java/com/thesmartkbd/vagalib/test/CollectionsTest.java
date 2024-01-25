package com.thesmartkbd.vagalib.test;

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

import com.alibaba.fastjson.JSONObject;
import com.thesmartkbd.vagalib.Objects;
import org.junit.Test;

import static com.thesmartkbd.vagalib.collection.Collections.*;
import static com.thesmartkbd.vagalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
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
        var ret = listDiff(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较

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
        var ret = listInt(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
    }

    @Test
    public void listSymmDiffTest() {
        var nums1 = listOf(1, 2, 3, 4);
        var nums2 = listOf(3, 4, 5, 6);
        stdout.println(listSymmDiff(nums1, nums2));
    }

}
