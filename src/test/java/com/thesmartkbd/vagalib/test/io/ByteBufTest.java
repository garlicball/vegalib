package com.thesmartkbd.vagalib.test.io;

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

/* Creates on 2023/6/19. */

import com.thesmartkbd.vagalib.io.ByteBuf;
import com.thesmartkbd.vagalib.io.MutableFile;
import com.thesmartkbd.vagalib.io.IOUtils;
import org.junit.Test;

import java.util.Arrays;

import static com.thesmartkbd.vagalib.io.ByteBuf.SEEK_SET;
import static com.thesmartkbd.vagalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBuf.allocate();
        buffer.write(Integer.MAX_VALUE);
    }

    @Test
    public void readLong() {
        ByteBuf buffer = ByteBuf.allocate(1);

        buffer.write(1024L);
        buffer.seek(SEEK_SET, 0);
        System.out.println(buffer.readLong());

        buffer.write(2048L);
        buffer.seek(SEEK_SET, 8);
        System.out.println(buffer.readLong());
    }

    @Test
    public void copyFile() {
        byte[] b1 =
                IOUtils.read(new MutableFile("C:\\Users\\Lenovo\\Desktop\\微信图片_20230706154702.png").openReader());
        ByteBuf byteBuf = ByteBuf.wrap(b1);
        IOUtils.write(byteBuf.toByteArray(),
                new MutableFile("C:\\Users\\Lenovo\\Desktop\\123.png"));
    }

    @Test
    public void readTest() {
        ByteBuf buf = ByteBuf.allocate();
        for (int i = 0; i < 100; i++)
            buf.write((byte) i);

        buf.write((byte) 100);
        buf.write((byte) 101);
        buf.write((byte) 102);
        buf.write((byte) 103);

        int len;
        byte[] nbuf = new byte[10];
        buf.seek(SEEK_SET, 0);
        while ((len = buf.read(nbuf, 0, nbuf.length)) != -1)
            stdout.println("read len=%s, nbuf=%s", len, Arrays.toString(nbuf));

    }

}
