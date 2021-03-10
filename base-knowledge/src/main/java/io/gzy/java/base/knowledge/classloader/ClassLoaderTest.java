package io.gzy.java.base.knowledge.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: ClassLoaderTest
 * date: 2021/1/30 3:36 下午
 * author: guizhenyu
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null){
                    return super.loadClass(name);
                }

                byte[] b = new byte[is.available()];

                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
//io.gzy.java.base.knowledge.classloader.Pen
//        classLoader.loadClass("io.gzy.java.base.knowledge.classloader.Pen").newInstance()

//        System.out.println(obj.getClass());
//
//        System.out.println(obj instanceof Pen);

    }

}
