package org.venorze.vegalib.test.io;

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
|* File:           VegaFileTest.java                                                *|
|* Create Time:    2023/6/7                                                         *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.junit.Test;
import org.venorze.vegalib.io.VegaDirectory;
import org.venorze.vegalib.io.VegaFile;

import static org.venorze.vegalib.Objects.vfprintln;

/**
 * @author venorze
 */
public class VegaFileTest {

    @Test
    public void getFileSize() {
        vfprintln("size: %s", new VegaFile("D:\\Dev\\Projects\\vegalib\\target\\vegalib-season.winter.2023.jar").size() / 1024);
    }

    @Test
    public void getDirectorySize() {
        long size = new VegaDirectory("C:\\Windows").size();
        vfprintln("size: %sBytes", size);
        vfprintln("size: %sKB", size / 1024);
        vfprintln("size: %sMB", size / 1024 / 1024);
    }

}
