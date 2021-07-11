package FunctionInterfaceStudy;

//接口中只有一个抽象方法，称为函数式接口
//接口中也可以有其他方法（默认、静态、私有）
@FunctionalInterface
public interface DefineFunctionInterface {
    public abstract String method();
}
