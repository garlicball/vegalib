package org.forironflower.vegalib.test;

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

/* Creates on 2023/6/20. */

import org.forironflower.vegalib.Bits;
import org.forironflower.vegalib.Objects;
import org.junit.Test;

import java.util.Date;

import static org.forironflower.vegalib.Assert.throwIfError;
import static org.forironflower.vegalib.io.IOUtils.stdout;

/**
 * @author forironflower
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
        stdout.println("fa, fb, fc=%s", Bits.bithas(flags, fa, fb, fc));
        stdout.println("fa, fc=%s", Bits.bithas(flags, fa, fc));
        stdout.println("fc, fb=%s", Bits.bithas(flags, fc, fb));
        stdout.println("fc, fd=%s", Bits.bithas(flags, fc, fd));
    }

    @Test
    public void dateAnycmpTest() throws InterruptedException {
        Date d1 = new Date();
        Thread.sleep(100);
        Date d2 = new Date();

        stdout.println("d1 > d2=%s", Objects.anycmp(d1, d2, Objects.ACMP_GT));
        stdout.println("d1 < d2=%s", Objects.anycmp(d1, d2, Objects.ACMP_LT));
        stdout.println("d1 = d2=%s", Objects.anycmp(d1, d2, Objects.ACMP_EQ));
    }

}
