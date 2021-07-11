package IOStreamStudy;

import java.io.*;

public class BufferedOutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt");
        FileInputStream fis = new FileInputStream("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bos.write(123);
        bos.flush();

        System.out.println(bis.read());

        bis.close();
        bos.close();
    }
}
