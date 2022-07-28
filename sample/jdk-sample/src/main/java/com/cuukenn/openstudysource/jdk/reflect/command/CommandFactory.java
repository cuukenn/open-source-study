package com.cuukenn.openstudysource.jdk.reflect.command;

import com.cuukenn.openstudysource.jdk.util.PackageUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 包扫描
 * 泛型查询
 * 动态注入
 *
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommandFactory {
    private static final Map<Class<?>, Class<ICommandHandler<ICmd>>> COMMAND_HANDLER = new HashMap<>();

    static {
        Set<Class<?>> classes = PackageUtil.findAllClasses(CommandFactory.class.getPackage().getName(), ICommandHandler.class, true);
        for (Class<?> clazz : classes) {
            if (Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }
            for (Type type : clazz.getGenericInterfaces()) {
                if (type instanceof ParameterizedType) {
                    if (ICommandHandler.class.isAssignableFrom(((ParameterizedTypeImpl) type).getRawType())) {
                        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
                        //noinspection unchecked
                        COMMAND_HANDLER.put((Class<?>) typeArgument, (Class<ICommandHandler<ICmd>>) clazz);
                        break;
                    }
                }
            }
        }
    }

    public static Collection<Class<ICommandHandler<ICmd>>> getAllCommandHandler() {
        return COMMAND_HANDLER.values();
    }
}
