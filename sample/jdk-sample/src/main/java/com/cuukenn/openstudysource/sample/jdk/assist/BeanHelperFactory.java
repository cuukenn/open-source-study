package com.cuukenn.openstudysource.sample.jdk.assist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * javassist在存在泛型时不能与原函数签名不一致，即需保留泛型写法否者报错，故需要先获取已经存在的method然后修改body
 * 生成的class文件反编译出来后不符合java语法
 *
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanHelperFactory {
    private static final Object LOCK = new Object();
    private static final Map<String, IBeanHelper<IDto, IEntity>> MAP = new HashMap<>();

    private static <D extends IDto, E extends IEntity> String getKey(Class<D> dtoClass, Class<E> entityClass) {
        return BeanHelperFactory.class.getPackage().getName() + "." + dtoClass.getSimpleName() + "_" + entityClass.getSimpleName() + "_" + IBeanHelper.class.getSimpleName();
    }

    public static <D extends IDto, E extends IEntity> IBeanHelper<D, E> getBeanHelper(Class<D> dtoClass, Class<E> entityClass) {
        IBeanHelper<IDto, IEntity> beanHelper = MAP.get(getKey(dtoClass, entityClass));
        if (beanHelper != null) {
            //noinspection unchecked
            return (IBeanHelper<D, E>) beanHelper;
        }
        synchronized (LOCK) {
            beanHelper = MAP.get(getKey(dtoClass, entityClass));
            if (beanHelper != null) {
                //noinspection unchecked
                return (IBeanHelper<D, E>) beanHelper;
            }
            IBeanHelper<D, E> aClass = createClass(dtoClass, entityClass);
            //noinspection unchecked
            MAP.put(getKey(dtoClass, entityClass), (IBeanHelper<IDto, IEntity>) aClass);
            return aClass;
        }
    }

    @SneakyThrows
    private static <D extends IDto, E extends IEntity> IBeanHelper<D, E> createClass(Class<D> dtoClass, Class<E> entityClass) {
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendSystemPath();
        classPool.importPackage(IBeanHelper.class.getPackage().getName());
        classPool.importPackage(dtoClass.getPackage().getName());
        classPool.importPackage(entityClass.getPackage().getName());
        String className = getKey(dtoClass, entityClass);
        CtClass ctClass = classPool.makeClass(className);
        {
            CtClass beanHelperInterface = classPool.get(IBeanHelper.class.getName());
            ctClass.addInterface(beanHelperInterface);

            SignatureAttribute signatureAttribute = new SignatureAttribute(
                    ctClass.getClassFile().getConstPool(),
                    "Ljava/lang/Object;"
                            + "L" + IBeanHelper.class.getName().replaceAll("\\.", "/")
                            + "<"
                            + "L" + dtoClass.getName().replaceAll("\\.", "/") + ";"
                            + "L" + entityClass.getName().replaceAll("\\.", "/") + ";"
                            + ">;");
            ctClass.getClassFile().addAttribute(signatureAttribute);

            CtConstructor constructor = new CtConstructor(new CtClass[]{}, ctClass);
            constructor.setBody("{}");
            ctClass.addConstructor(constructor);
        }

        ConstPool constPool = ctClass.getClassFile().getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation overrideAnnotation = new Annotation(Override.class.getName(), constPool);
        annotationsAttribute.addAnnotation(overrideAnnotation);
        {
            CtMethod method = ctClass.getMethod("toDTO", String.format("(L%s;)L%s;",
                    IEntity.class.getName().replaceAll("\\.", "/"),
                    IDto.class.getName().replaceAll("\\.", "/")));
            CtMethod toDTO = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), ctClass);
            StringBuilder dtoMethodStr = new StringBuilder();
            dtoMethodStr.append("{");
            dtoMethodStr.append(entityClass.getName()).append(" entity = (").append(entityClass.getName()).append(")$1;");
            dtoMethodStr.append(dtoClass.getName()).append(" dto = new ").append(dtoClass.getName()).append("();");
            for (Field field : dtoClass.getDeclaredFields()) {
                char[] name = field.getName().toCharArray();
                name[0] = String.valueOf(name[0]).toUpperCase().toCharArray()[0];
                dtoMethodStr.append("dto.set").append(name).append("(").append("entity.get").append(name).append("());");
            }
            dtoMethodStr.append("return dto;");
            dtoMethodStr.append("}");
            toDTO.setBody(dtoMethodStr.toString());
            toDTO.getMethodInfo().addAttribute(annotationsAttribute);
            ctClass.addMethod(toDTO);
        }

        {
            CtMethod method = ctClass.getMethod("toEntity", String.format("(L%s;)L%s;",
                    IDto.class.getName().replaceAll("\\.", "/"),
                    IEntity.class.getName().replaceAll("\\.", "/")));
            CtMethod toEntity = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), ctClass);
            StringBuilder entityMethodStr = new StringBuilder();
            entityMethodStr.append("{");
            entityMethodStr.append(dtoClass.getName()).append(" dto = (").append(dtoClass.getName()).append(")$1;");
            entityMethodStr.append(entityClass.getName()).append(" entity = new ").append(entityClass.getName()).append("();");
            for (Field field : entityClass.getDeclaredFields()) {
                char[] name = field.getName().toCharArray();
                name[0] = String.valueOf(name[0]).toUpperCase().toCharArray()[0];
                entityMethodStr.append("entity.set").append(name).append("(").append("dto.get").append(name).append("());");
            }
            entityMethodStr.append("return entity;");
            entityMethodStr.append("}");
            toEntity.setBody(entityMethodStr.toString());
            toEntity.getMethodInfo().addAttribute(annotationsAttribute);
            ctClass.addMethod(toEntity);
        }
        ctClass.writeFile("target/classes");
//        return null;
        //noinspection unchecked
        return (IBeanHelper<D, E>) ctClass.toClass().newInstance();
    }

    /**
     * @author changgg
     */
    public interface IBeanHelper<D extends IDto, E extends IEntity> {
        /**
         * entry转dto
         *
         * @param entity 实体
         * @return dto
         */
        D toDTO(E entity);

        /**
         * dto 转 entry
         *
         * @param dto 传输对象
         * @return entity
         */
        E toEntity(D dto);
    }
}
