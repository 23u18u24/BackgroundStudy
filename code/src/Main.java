import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Properties;
@MyAnno(className = "ReflectStudy.Person", methodName = "eat")
public class Main {
    public static void main(String[] args) throws Exception {
        //1.解析注解
        //1.1获取该类的字节码文件
        Class mainClass = Main.class;
        //2.获取上边的注解对象
        MyAnno an = (MyAnno) mainClass.getAnnotation(MyAnno.class);
        //3.调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();

        Class aClass = Class.forName(className);
        Object o = aClass.getDeclaredConstructor().newInstance();
        Method method = aClass.getMethod(methodName);
        Object invoke = method.invoke(o);


    }
}
