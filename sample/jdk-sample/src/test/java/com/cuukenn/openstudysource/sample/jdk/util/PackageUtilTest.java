package com.cuukenn.openstudysource.sample.jdk.util;

import com.cuukenn.openstudysource.sample.jdk.reflect.command.CommandFactory;
import com.cuukenn.openstudysource.sample.jdk.reflect.command.ICommandHandler;
import org.junit.Test;

import java.util.Set;

/**
 * @author changgg
 */
public class PackageUtilTest {
    @Test
    public void test_package_scan() {
        Set<Class<?>> allClasses = PackageUtil.findAllClasses(CommandFactory.class.getPackage().getName(), ICommandHandler.class, true);
        System.out.println(allClasses);
    }
}