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

/* Creates on 2023/6/19. */

import org.forironflower.vegalib.io.ByteBuf;
import org.forironflower.vegalib.Objects;
import org.junit.Test;

import static org.forironflower.vegalib.Objects.atoi;
import static org.forironflower.vegalib.Objects.atol;

/**
 * @author forironflower
 */
public class NumberValTest {

    @Test
    public void readInt() {
        ByteBuf buffer = ByteBuf.allocate();
        buffer.write(10086);
        System.out.println(Objects.atoi("10085"));
        System.out.println(Objects.atoi(buffer.toByteArray()));
    }


    @Test
    public void readLong() {
        ByteBuf buffer = ByteBuf.allocate();
        buffer.write(10086L);
        System.out.println(Objects.atol("10085"));
        System.out.println(Objects.atol(buffer.toByteArray()));
    }

}
