package org.forironflower.vegalib.samples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 forironflower                                              *|
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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           CollectionSamples.java                                           *|
|* Create Time:    2024/2/03 19:45                                                  *|
|* Author:         forironflower                                                    *|
|* EMail:          forironflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.junit.Test;

import java.util.List;

import static org.forironflower.vegalib.Objects.fprintlnf;
import static org.forironflower.vegalib.collection.Collections.*;

/**
 * ClassPathResourceSamples 演示
 *
 * @author forironflower
 */
public class CollectionSamples {

    private static final List<Integer> X = listOf(1, 2, 3);
    private static final List<Integer> Y = listOf(3, 4, 5);

    /**
     * listInt 获取两个集合（X, Y）之间的交集部分
     */
    @Test
    public void api_sample_collection_list_int() {
        fprintlnf(listInt(X, Y));
    }

    /**
     * listDiff 获取一个集合对于另一个集合两个集合之间的差集
     */
    @Test
    public void api_sample_collection_list_diff() {
        fprintlnf(listDiff(X, Y));
        fprintlnf(listDiff(Y, X));
    }

    /**
     * listSymmDiff 获取一个集合对于另一个集合两个集合之间的对称差集
     */
    @Test
    public void api_sample_collection_list_symm_diff() {
        fprintlnf(listSymmDiff(X, Y));
    }

}
