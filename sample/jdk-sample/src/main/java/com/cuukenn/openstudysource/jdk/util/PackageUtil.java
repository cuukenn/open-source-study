package com.cuukenn.openstudysource.jdk.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PackageUtil {
    /**
     * 扫描指定包下的指定接口的实现类
     *
     * @param packageName    包名
     * @param interfaceClass 接口名称
     * @param recursion      是否递归查询
     * @return 实现类
     */
    @SneakyThrows
    public static Set<Class<?>> findAllClasses(String packageName, Class<?> interfaceClass, boolean recursion) {
        return findAllClasses(packageName, interfaceClass::isAssignableFrom, recursion);
    }

    /**
     * 扫描指定包下的指定接口的实现类
     *
     * @param packageName 包名
     * @param classFilter 类过滤器
     * @param recursion   是否递归查询
     * @return 实现类
     */
    @SneakyThrows
    public static Set<Class<?>> findAllClasses(String packageName, Predicate<Class<?>> classFilter, boolean recursion) {
        if (packageName == null || packageName.length() <= 0) {
            return Collections.emptySet();
        }
        if (classFilter == null) {
            return Collections.emptySet();
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(packageName.replaceAll("\\.", "/"));
        Set<Class<?>> classes = new HashSet<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if ("file".equalsIgnoreCase(resource.getProtocol())) {
                classes.addAll(exactFileDirectory(new File[]{new File(resource.getPath())}, classFilter, recursion, packageName));
            } else {
                System.out.println(resource);
            }
        }
        return classes;
    }

    /**
     * 扫描文件夹
     *
     * @param fileDirectories 文件夹
     * @param classFilter     类过滤器
     * @param recursion       是否递归
     * @param packageName     包名
     * @return 类
     */
    private static Set<Class<?>> exactFileDirectory(File[] fileDirectories, Predicate<Class<?>> classFilter, boolean recursion, String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (File file : fileDirectories) {
            File[] subFileDirectories = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (file.isDirectory()) {
                        return recursion;
                    } else {
                        String absolutePath = file.getAbsolutePath();
                        if (absolutePath.endsWith(".class")) {
                            addToClassSet(absolutePath);
                        }
                        return false;
                    }
                }

                @SneakyThrows
                private void addToClassSet(String absolutePath) {
                    absolutePath = absolutePath.replace(File.separator, ".");
                    String className = absolutePath.substring(absolutePath.indexOf(packageName));
                    className = className.substring(0, className.lastIndexOf("."));
                    Class<?> targetClass = classLoader.loadClass(className);
                    if (classFilter.test(targetClass)) {
                        classes.add(targetClass);
                    }
                }
            });
            if (recursion && subFileDirectories != null && subFileDirectories.length > 0) {
                classes.addAll(exactFileDirectory(subFileDirectories, classFilter, recursion, packageName));
            }
        }
        return classes;
    }
}
