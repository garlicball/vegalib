package org.forironflower.vegalib.exception;

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

/* Creates on 2023/4/30. */

/**
 * @author forironflower
 */
public class OpenException extends VegaRuntimeException {

    public OpenException() {
        super();
    }

    public OpenException(String message) {
        super(message);
    }

    public OpenException(String message, Object... args) {
        super(message, args);
    }

    public OpenException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenException(String message, Throwable cause, Object... args) {
        super(message, args, cause);
    }

    public OpenException(Throwable cause) {
        super(cause);
    }

}
