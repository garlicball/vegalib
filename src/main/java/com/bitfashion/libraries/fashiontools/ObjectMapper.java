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

/* Create on 2023/8/10 */

/**
 * @author luotiansheng
 */
@FunctionalInterface
public interface ObjectMapper<T, R> {
    /**
     * 将泛型 T 的数据通过 Lambda 函数映射成为泛型 R
     *
     * @param t 映射数据对象
     * @return 处理后的结果
     */
    R apply(T t);
}
