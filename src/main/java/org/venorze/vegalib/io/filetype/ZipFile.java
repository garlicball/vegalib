package org.venorze.vegalib.io.filetype;

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
|* File:           ZipFile.java                                                *|
|* Create Time:    2024/2/29 18:02                                                   *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.venorze.vegalib.io.VegaFile;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * @author venorze
 */
public class ZipFile extends VegaFile {

    public ZipFile(File file) {
        super(file);
    }

    public ZipFile(String pathname) {
        super(pathname);
    }

    public ZipFile(String pathname, Object... args) {
        super(pathname, args);
    }

    public ZipFile(String parent, String child) {
        super(parent, child);
    }

    public ZipFile(File parent, String child) {
        super(parent, child);
    }

    public ZipFile(URI uri) {
        super(uri);
    }

    public ZipFile(URL url) {
        super(url);
    }

}
