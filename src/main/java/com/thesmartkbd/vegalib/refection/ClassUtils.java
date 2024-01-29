package com.thesmartkbd.vegalib.refection;

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

/* Creates on 2019/5/16. */

import com.thesmartkbd.vegalib.collection.Collections;
import com.thesmartkbd.vegalib.exception.UnexistsException;
import com.thesmartkbd.vegalib.exception.VegaRuntimeException;
import com.thesmartkbd.vegalib.io.VegaFile;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;

import static com.thesmartkbd.vegalib.Objects.snprintf;
import static com.thesmartkbd.vegalib.Objects.strrep;

/**
 * 类加载器工具类
 *
 * @author thesmartkbd
 */
public class ClassUtils {
    /**
     * @return 根据当前线程的上下文，获取 {@link BytecodeClassLoader} 对象实例。
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取当前类加载器下的资源目录，并获取资源目录中的资源 URL 信息，如果资源
     * 不存在，则会返回 {@code null}.
     *
     * @param path
     *        资源所在目录地址
     *
     * @return 地址 URL
     */
    public static URL getResource(String path) {
        return getContextClassLoader().getResource(path);
    }

    /**
     * 获取当前类加载器下的资源目录，并获取资源目录中的资源输入流，如果资源
     * 不存在，则会返回 {@code null}.
     *
     * @param path
     *        资源所在目录地址
     *
     * @return 资源输入流
     */
    public static InputStream getResourceStream(String path) {
        return getContextClassLoader().getResourceAsStream(path);
    }

    /**
     * 扫描一个包下的所有类，并将这些类添加到一个 {@code List<Class<?>>}
     * 集合中。如果包不存在，则会抛出异常。
     *
     * @param basePackage
     *        指定需要扫描包
     *
     * @return 返回一个包含参数 {@code basePackage} 下的所有类对象。
     */
    public static List<ObjectPrimary> scanPackage(String basePackage) {
        List<ObjectPrimary> primaries = Collections.listOf();
        try {
            // 处理basePackages路径
            String clearPackage = strrep(basePackage, "\\.", "/");
            URL resource = getResource(clearPackage);
            if (resource == null)
                throw new UnexistsException("路径不存在：%s", basePackage);
            VegaFile file = new VegaFile(resource.getPath());
            for (VegaFile mutableFile : file.openDirectory()) {
                if (mutableFile.typeEquals(".class")) {
                    Class<?> clazz = Class.forName(
                            snprintf("%s.%s", basePackage, mutableFile.cleaname()));
                    primaries.add(new ObjectPrimary(clazz));
                }
            }
        } catch (Exception e) {
            throw new VegaRuntimeException(e);
        }
        return primaries;
    }

    /**
     * 实例化一个类对象，根据类的构造器传入参数数据。
     *
     * @param primary
     *        类对象
     *
     * @param parameters
     *        构造器参数，如果使用空构造器就不传
     *
     * @return 类对象实例
     */
    public static <T> T newInstance(Class<T> primary, Object... parameters) {
        try {
            Class<?>[] parametersClassArray = toClassArray(parameters);
            Constructor<T> constructor = primary.getConstructor(parametersClassArray);
            return constructor.newInstance(parameters);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将参数数组转换成类型数组
     */
    public static Class<?>[] toClassArray(Object... parameters) {
        List<Class<?>> parametersClassList = Collections.listOf();
        for (Object parameter : parameters)
            parametersClassList.add(parameter.getClass());
        Class<?>[] parametersClassArray = new Class<?>[parametersClassList.size()];
        parametersClassList.toArray(parametersClassArray);
        return parametersClassArray;
    }
}
