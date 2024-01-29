package com.thesmartkbd.vegalib.exception;

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

/* Creates on 2023/4/30. */

import static com.thesmartkbd.vegalib.Objects.snprintf;

/**
 * @author thesmartkbd
 */
public class VegaIOException extends VegaRuntimeException {

    public VegaIOException() {
        super();
    }

    public VegaIOException(String message) {
        super(message);
    }

    public VegaIOException(String message, Object... args) {
        super(snprintf(message, args));
    }

    public VegaIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public VegaIOException(String message, Throwable cause, Object... args) {
        super(snprintf(message, args), cause);
    }

    public VegaIOException(Throwable cause) {
        super(cause);
    }

}
