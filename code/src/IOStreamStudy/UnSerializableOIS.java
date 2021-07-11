package IOStreamStudy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UnSerializableOIS {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\oosW.txt"));
        System.out.println(ois.readObject());
        ois.close();
    }
}
