package com.thesmartkbd.vegalib.test.io;

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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           ByteBufTest.java                                                 *|
|* Create Time:    2023/6/19                                                        *|
|* Author:         fsilverflower                                                    *|
|* EMail:          fsilverflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.thesmartkbd.vegalib.io.ByteBuf;
import com.thesmartkbd.vegalib.io.VegaFile;
import com.thesmartkbd.vegalib.io.IOUtils;
import org.junit.Test;

import java.util.Arrays;

import static com.thesmartkbd.vegalib.io.ByteBuf.SEEK_SET;
import static com.thesmartkbd.vegalib.io.IOUtils.stdout;

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
                IOUtils.read(new VegaFile("C:\\Users\\Lenovo\\Desktop\\微信图片_20230706154702.png").openReader());
        ByteBuf byteBuf = ByteBuf.wrap(b1);
        IOUtils.write(byteBuf.toByteArray(),
                new VegaFile("C:\\Users\\Lenovo\\Desktop\\123.png"));
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
