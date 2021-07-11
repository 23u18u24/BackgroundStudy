package IOStreamStudy;

import java.io.FileInputStream;
import java.io.IOException;

public class FfileInputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt");
        int a = fis.read();
        System.out.println(a);
        fis.close();
    }
}
