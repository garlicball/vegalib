package com.thesmartkbd.vegalib.test;

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

/* Creates on 2023/6/20. */

import com.thesmartkbd.vegalib.ApiTemplateResult;
import lombok.Getter;
import lombok.ToString;
import com.thesmartkbd.vegalib.BeanUtils;
import org.junit.Test;

import static com.thesmartkbd.vegalib.collection.Collections.listOf;
import static com.thesmartkbd.vegalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class BeanUtilsTest {

    @Test
    public void copyForNewInstance() {
        var ret = ApiTemplateResult.ok("what the fuck?");
        System.out.println(BeanUtils.copyProperties(ret, ApiTemplateResult.class));
    }

    @Test
    public void copyForList() {
        var uncopy = listOf(ApiTemplateResult.ok("a"),
                            ApiTemplateResult.ok("b"),
                            ApiTemplateResult.ok("c"),
                            ApiTemplateResult.ok("d"));
        var rets = BeanUtils.copyProperties(uncopy, ApiTemplateResult.class);
        stdout.println(rets);
    }

    /* Instance of A */
    @Getter
    @ToString
    public static class A {
        private String data = "i am A instance.";
    }

    /* Instance of B */
    @Getter
    @ToString
    public static class B {
        private String data = "i am B instance.";
    }

    @Test
    public void notDifferenceObjectCopy() {
        stdout.println(BeanUtils.copyProperties(new A(), B.class));
    }

}
