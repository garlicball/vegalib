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

import com.thesmartkbd.vagalib.refection.ClassModifier;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class ClassModifierTest {

    public void visitor0() {}
    public void visitor1() {}
    public void visitor2() {}

    @Test
    public void editClass() {
        ClassModifier editor = new ClassModifier(ClassModifierTest.class);
        System.out.println(Arrays.toString(editor.toBytecode()));
    }

}
