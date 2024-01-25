package com.thesmartkbd.vagalib.test.refection;

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

/* Creates on 2023/6/20. */

import lombok.Getter;
import lombok.ToString;
import com.thesmartkbd.vagalib.refection.ObjectProperty;
import org.junit.Test;

import static com.thesmartkbd.vagalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class ObjectPropertyTest {

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

    /**
     * 两个类，属性相同互相拷贝测试
     */
    @Test
    public void diffObjectCopy() {
        var a = new A();
        var b = new B();
        ObjectProperty.copy(a, b, "data");
        stdout.println("拷贝 A 数据到 B：\n    a: %s\n    b: %s", a, b);
    }

}
