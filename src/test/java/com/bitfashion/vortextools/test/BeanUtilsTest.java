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

/* Creates on 2023/6/20. */

import com.bitfashion.libraries.fashiontools.ApiTemplateResult;
import lombok.Getter;
import lombok.ToString;
import com.bitfashion.libraries.fashiontools.BeanUtils;
import org.junit.Test;

import static com.bitfashion.libraries.fashiontools.collection.Collections.listOf;
import static com.bitfashion.libraries.fashiontools.io.IOUtils.stdout;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class BeanUtilsTest {

    @Test
    public void copyForNewInstance() {
        var ret = ApiTemplateResult.ok("what the fuck?");
        System.out.println(BeanUtils.copyProperties(ret, ApiTemplateResult.class));
    }

    @Test
    public void copyForList() {
        var uncopy = listOf(ApiTemplateResult.ok("a"),
                            ApiTemplateResult.ok("b"),
                            ApiTemplateResult.ok("c"),
                            ApiTemplateResult.ok("d"));
        var rets = BeanUtils.copyProperties(uncopy, ApiTemplateResult.class);
        stdout.println(rets);
    }

    /* Instance of A */
    @Getter
    @ToString
    public static class A {
        private String data = "i am A instance.";
    }

    /* Instance of B */
    @Getter
    @ToString
    public static class B {
        private String data = "i am B instance.";
    }

    @Test
    public void notDifferenceObjectCopy() {
        stdout.println(BeanUtils.copyProperties(new A(), B.class));
    }

}
