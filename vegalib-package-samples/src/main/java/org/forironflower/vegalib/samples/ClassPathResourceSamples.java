package org.forironflower.vegalib.samples;

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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           ClassPathResourceSamples.java                                    *|
|* Create Time:    2024/1/30 18:12                                                  *|
|* Author:         forironflower                                                    *|
|* EMail:          forironflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.forironflower.vegalib.refection.ClassPathResourceReader;
import org.junit.Test;

import static org.forironflower.vegalib.Arrays.array_to_string;
import static org.forironflower.vegalib.Objects.fprintlnf;

/**
 * ClassPathResourceSamples 演示
 *
 * @author forironflower
 */
public class ClassPathResourceSamples {

    @Test
    public void api_sample_classpath_resource() {
        fprintlnf("ClassPathResourceReader Text:  %s", ClassPathResourceReader.strread("classpath.resource.vegalib"));
        fprintlnf("ClassPathResourceReader Bytes: %s", array_to_string(ClassPathResourceReader.read("classpath.resource.vegalib")));
    }

}
