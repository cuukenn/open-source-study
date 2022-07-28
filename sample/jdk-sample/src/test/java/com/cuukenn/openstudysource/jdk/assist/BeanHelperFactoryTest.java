package com.cuukenn.openstudysource.jdk.assist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author changgg
 */
public class BeanHelperFactoryTest {
    @Test
    public void test_javassist() {
        BeanHelperFactory.IBeanHelper<UserDto, User> beanHelper = BeanHelperFactory.getBeanHelper(UserDto.class, User.class);
        User user = new User();
        user.setUsername("test001");
        user.setPassword("test001");
        UserDto userDto = beanHelper.toDTO(user);
        System.out.println(userDto);
        System.out.println(beanHelper.toEntity(userDto));
    }

    @Test
    @SneakyThrows
    public void test_javassist_2() {
        ClassPool classPool = ClassPool.getDefault();
        String className = "HelloRepository";
        classPool.importPackage(Repository.class.getPackage().getName());
        CtClass ctClazz = classPool.makeClass(className);
        CtClass repository = classPool.get(Repository.class.getName());
        ctClazz.addInterface(repository);
        SignatureAttribute signatureAttribute = new SignatureAttribute(
                ctClazz.getClassFile().getConstPool(),
                "Ljava/lang/Object;L" + Repository.class.getName().replaceAll("\\.", "/")
                        + "<"
                        + "L" + String.class.getName().replaceAll("\\.", "/") + ";"
                        + ">"
                        + ";");
        ctClazz.getClassFile().getAttributes().add(signatureAttribute);


        CtMethod method = ctClazz.getMethod("findOne", "(Ljava/lang/Object;)Ljava/lang/Object;");
        CtMethod findOne = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), ctClazz);
        findOne.setBody("{return $1;}");

        ConstPool constPool = ctClazz.getClassFile().getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation overrideAnnotation = new Annotation(Override.class.getName(), constPool);
        annotationsAttribute.addAnnotation(overrideAnnotation);
        findOne.getMethodInfo().addAttribute(annotationsAttribute);
        ctClazz.addMethod(findOne);
        ctClazz.writeFile("target/classes");

        System.out.println(((Repository<String, String>) ctClazz.toClass().newInstance()).findOne("hello"));
    }
}