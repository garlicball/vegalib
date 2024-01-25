package com.thesmartkbd.vagalib.refection;

/* ************************************************************************
 *
 * Copyright (C) 2020 thesmartkbd All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not useEnv this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2019/5/16. */

import com.thesmartkbd.vagalib.collection.Collections;
import com.thesmartkbd.vagalib.exception.UnexistsException;
import com.thesmartkbd.vagalib.io.MutableFile;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;

import static com.thesmartkbd.vagalib.Objects.sprintf;
import static com.thesmartkbd.vagalib.Objects.strrep;

/**
 * 类加载器工具类
 *
 * @author thesmartkbd
 */
public class JvmRefs {
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
    public static InputStream getStream(String path) {
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
            MutableFile file = new MutableFile(resource.getPath());
            for (MutableFile mutableFile : file.openDirectory()) {
                if (mutableFile.extensionEquals(".class")) {
                    var clazz = Class.forName(
                            sprintf("%s.%s", basePackage, mutableFile.cleaname()));
                    primaries.add(new ObjectPrimary(clazz));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
