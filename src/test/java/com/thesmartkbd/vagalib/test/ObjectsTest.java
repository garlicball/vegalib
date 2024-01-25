package com.thesmartkbd.vagalib.test;

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

import org.junit.Test;

import java.util.Date;

import static com.thesmartkbd.vagalib.Assert.throwIfError;
import static com.thesmartkbd.vagalib.Bits.bithas;
import static com.thesmartkbd.vagalib.Objects.*;
import static com.thesmartkbd.vagalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class ObjectsTest {

    @Test
    public void anycmpTest() {
        int fa = 0x1;
        int fb = 0x2;
        int fc = 0x4;
        int fd = 0x8;

        int flags = (fa | fb | fc);
        stdout.println("fa, fb, fc=%s", bithas(flags, fa, fb, fc));
        stdout.println("fa, fc=%s", bithas(flags, fa, fc));
        stdout.println("fc, fb=%s", bithas(flags, fc, fb));
        stdout.println("fc, fd=%s", bithas(flags, fc, fd));
    }

    @Test
    public void dateAnycmpTest() throws InterruptedException {
        var d1 = new Date();
        Thread.sleep(100);
        var d2 = new Date();

        stdout.println("d1 > d2=%s", anycmp(d1, d2, ACMP_GT));
        stdout.println("d1 < d2=%s", anycmp(d1, d2, ACMP_LT));
        stdout.println("d1 = d2=%s", anycmp(d1, d2, ACMP_EQ));
    }

}
