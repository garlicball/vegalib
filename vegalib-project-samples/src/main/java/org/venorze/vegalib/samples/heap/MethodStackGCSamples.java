package org.venorze.vegalib.samples.heap;

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
|* File:           MethodStackGCSamples.java                                        *|
|* Create Time:    2024/4/3 13:32                                                   *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import java.util.List;

import static org.forironflower.vegalib.collection.Collections.listOf;

/**
 * @author venorze
 */
public class MethodStackGCSamples {

    public static void main(String[] args) {
        new MethodStackGCSamples().call();
    }

    public void call() {
        for (int i = 0; i < 100000; i++)
            foo();
    }

    public void foo() {
        List<Object> list = listOf();
        for (int i = 0; i < 100000000; i++)
            list.add(new Object());
    }

}
