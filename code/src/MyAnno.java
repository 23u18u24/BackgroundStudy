import java.lang.annotation.*;


@Target(value = {ElementType.TYPE/*表示该注解能作用于类上*/,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
//@Documented//是否会在api文档中体现（javadoc命令生成api文件）
//@Inherited//表示该注解会自动被子类继承
public @interface MyAnno {
    String className();
    String methodName();
}
