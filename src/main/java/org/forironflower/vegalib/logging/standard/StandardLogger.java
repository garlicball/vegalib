package org.forironflower.vegalib.logging.standard;

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

/* Creates on 2019/11/05. */

import org.forironflower.vegalib.io.IOUtils;
import org.forironflower.vegalib.logging.Logger;
import org.forironflower.vegalib.time.DateFormatter;
import org.forironflower.vegalib.Objects;

/**
 * @author forironflower
 */
public class StandardLogger implements Logger {

    private final String classpath;

    public StandardLogger(String name) {
        this.classpath = name;
    }

    public StandardLogger(Class<?> aClass) {
        this.classpath = aClass.getName();
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void info(String message, Object... args) {
        logprint(classpath, "INFO", message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        logprint(classpath, "WARN", message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        logprint(classpath, "DEBUG", message, args);
    }

    @Override
    public void error(String message, Object... args) {
        logprint(classpath, "ERROR", message, args);
    }

    private static void logprint(String classpath, String level, String message, Object... args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        IOUtils.stdout.println("%s %s [%s] %s --- %s",
                DateFormatter.fmt(),
                level,
                stackTraceElement.getMethodName(),
                classpath,
                Objects.strfmt(message, args));
    }

}

