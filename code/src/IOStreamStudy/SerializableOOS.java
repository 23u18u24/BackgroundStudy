package IOStreamStudy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableOOS {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\oosW.txt"));
        oos.writeObject(new Person("www",18));
        oos.close();
    }
}
