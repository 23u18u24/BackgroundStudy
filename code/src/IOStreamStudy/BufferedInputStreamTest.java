package IOStreamStudy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        System.out.println(bis.read());
        bis.close();
    }
}
