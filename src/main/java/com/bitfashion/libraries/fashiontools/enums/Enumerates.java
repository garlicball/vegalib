package com.bitfashion.libraries.fashiontools.enums;

/* ************************************************************************
 *
 * Copyright (C) 2020 bit-fashion All rights reserved.
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

/* Create on 2023/9/14 */

import com.bitfashion.libraries.fashiontools.exception.InvalidArgumentException;
import com.bitfashion.libraries.fashiontools.refection.ObjectPrimary;

import static com.bitfashion.libraries.fashiontools.Objects.strieq;

/**
 * @author bit-fashion
 */
public class Enumerates {

    @SuppressWarnings("unchecked")
    private static <E extends Enum<E>> E[] values(Class<? extends Enum<E>> enumClass) {
        return (E[]) new ObjectPrimary(enumClass).staticInvoke("values");
    }

    @SuppressWarnings({"unchecked", "UnusedReturnValue"})
    public static <E extends Enum<E>> E checkout(Class<? extends Enum<E>> enumClass, String name) {
        Enum<E>[] values = values(enumClass);
        for (Enum<E> value : values)
            if (strieq(value.name(), name))
                return (E) value;
        throw new InvalidArgumentException("参数错误【%s】常量不存在！", name);
    }

}
