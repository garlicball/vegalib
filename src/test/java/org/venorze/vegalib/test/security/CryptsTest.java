package org.venorze.vegalib.test.security;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 venorze                                                    *|
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
|* File:           CryptsTest.java                                                *|
|* Create Time:    2024/1/29 17:45                                                   *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.junit.Test;
import org.venorze.vegalib.security.Crypts;

/**
 * @author venorze
 */
public class CryptsTest {

    @Test
    public void md5() {
        System.out.printf("md5lower32: %s\n", Crypts.Encoder.md5lower32("Hello World"));
    }

}
