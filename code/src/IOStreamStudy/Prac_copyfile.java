package IOStreamStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Prac_copyfile {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b_copy.txt");
        int len = fis.read();
        //边读边写
        while (len != -1) {
            fos.write(len);
            len = fis.read();
        }
        //读完再写
        byte[] bytes = new byte[1024];
        fis.close();
        fos.close();
    }
}
