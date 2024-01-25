package com.thesmartkbd.vagalib.math

import java.util.*
import kotlin.math.max
import kotlin.math.min

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

/**
 * 数学工具包
 *
 * @author thesmartkbd
 */
object Maths {
    /**
     * @return 随机生成 0 - `x` 之间的随机整数
     */
    @JvmStatic
    fun randomNextInt(n: Int): Int = randomNextInt(0, n)

    /**
     * @return 生成 x - y 范围内的随机整数
     */
    @JvmStatic
    fun randomNextInt(x: Int, y: Int): Int =
            Random().nextInt(max(x, y) - min(x, y)) + min(x, y)

    /**
     * @return 随机生成 0 - `x` 之间的随机整数
     */
    @JvmStatic
    fun randomNextLong(n: Long): Long = randomNextLong(0, n)

    /**
     * @return 生成 x - y 范围内的随机整数
     */
    @JvmStatic
    fun randomNextLong(x: Long, y: Long): Long =
            Random().nextLong(max(x, y) - min(x, y)) + min(x, y)

    /**
     * @return 随机生成 0.0f - `x` 之间的单精度小数
     */
    @JvmStatic
    fun randomNextFloat(n: Float): Float = randomNextFloat(0.0f, n)

    /**
     * @return 随机生成 x - y 范围内的单精度小数
     */
    @JvmStatic
    fun randomNextFloat(x: Float, y: Float): Float =
            Random().nextFloat(max(x, y) - min(x, y)) + min(x, y)

    /**
     * @return 随机生成 0.0d - `x` 之间的双精度小数
     */
    @JvmStatic
    fun randomNextDouble(n: Double): Double = randomNextDouble(0.0, n)

    /**
     * @return 随机生成 x - y 范围内的双精度小数
     */
    @JvmStatic
    fun randomNextDouble(x: Double, y: Double): Double =
            Random().nextDouble(max(x, y) - min(x, y)) + min(x, y)

    /**
     * #brief: 根据概率百分比来决定是否触发了概率事件。
     *
     * 概率算法，每次运行会判断是否触发了指定 `percent` 的概率事件，
     * 如果触发了则返回 `true`。
     *
     * @param percent
     *        指定触发概率（百分比）
     *
     * @return 触发了概率事件返回 `true`，否则返回 `false`。
     */
    @JvmStatic
    fun probability(percent: Double): Boolean =
        randomNextDouble(-1.0, 100.0) <= percent

}
