package ReflectStudy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo1 {
    public static class Person1 {
        private String name;
        private int age;
        public String otherName;
        public Person1() {
        }

        public Person1(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName1() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person1{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        //* Class.forName("全类名")：将字节码文件加载进内存，返回Class对象（源代码阶段）
        //  * 多用于配置文件，将类名定义在配置文件中。读取文件，加载类
        //* 类名.class：通过类名的属性class来获取
        //  * 多用于参数的传递
        //* 对象.getClass()：所有对象都有此方法
        //  * 多用于对象获取字节码
        Class aClass = Class.forName("ReflectStudy.Demo1$Person1");

        Class person1Class = Person1.class;

        Person1 p = new Person1();
        Class aClass1 = p.getClass();

//        System.out.println(aClass);
//        System.out.println(person1Class);
//        System.out.println(aClass1);

        //* 获取成员变量
        //  * Field[] getFields()
        //  * Field getField(String name)
//        Field[] fields = aClass1.getFields();
//        for (Field field : fields) {
//            System.out.println(field);
//        }
//        Field otherName = aClass1.getField("otherName");
//        Person1 p1 = new Person1();
//        System.out.println(otherName.get(p1));
//        otherName.set(p1, "张三");
//        System.out.println(otherName.get(p1));

        //  * Field[] getDeclaredFields()
        //  * Field getDeclaredField(String name)
//        for (Field declaredField : aClass.getDeclaredFields()) {
//            System.out.println(declaredField);
//        }
        Class personClass = Person.class;
//        Person p1 = new Person();
//        Field name = personClass.getDeclaredField("name");
//        System.out.println(name);
        //忽略访问权限修饰符的安全检查，暴力反射
//        name.setAccessible(true);
//        name.set(p1, "李四");
//        System.out.println(name.get(p1));
        //* 获取构造方法
        //  * Constructor<?>[] getConstructors()
        //  * Constructor\<T> getConstructors(类<?>... parameterTypes)
//        for (Constructor constructor : personClass.getConstructors()) {
//            System.out.println(constructor);
//        }
//        Constructor constructor = personClass.getConstructor(String.class, int.class);
//        System.out.println(constructor);
//        Object o = constructor.newInstance("张三", 18);
//        System.out.println(o);
        //  * Constructor\<?>[] getDeclaredConstructors(类<?>... parameterTypes)
        //  * Constructor\<T> getDeclaredConstructor()
        //* 获取成员方法
        //  * Method[] getMethods()
        //  * Method getMethod(String name, 类<?>... parameterTypes)
        Class personClass1 = Person.class;
        for (Method method : personClass1.getMethods()) {
            System.out.println(method);
        }
        //  * Method[] getDeclaredMethods()
        //  * Method getDeclaredMethod(String name, 类<?>... parameterTypes)
        //* 获取类名
        //  * String getName()
    }
}
