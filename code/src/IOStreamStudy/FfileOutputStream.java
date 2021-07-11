package IOStreamStudy;

import java.io.FileOutputStream;
import java.io.IOException;

public class FfileOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt");
        // 一次写一个字节
        fos.write(97);
        // 一次写多个字节
        byte[] b = {49, 48, 48};
        fos.write(b, 0, 3);
        fos.close();
        FileOutputStream fos1 = new FileOutputStream("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt",true);
        // 一次写一个字节
        fos1.write('\t');
        // 一次写多个字节
        byte[] b1 = {50, 48, 48};
        fos1.write(b1, 0, 3);
        fos1.close();
    }
}
