package IOStreamStudy;

import java.io.IOException;
import java.io.FileReader;

public class FFileReader {
    public static void main(String[] args) throws IOException {
        java.io.FileReader isr = new java.io.FileReader("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b_copy.txt");
        int len = isr.read();
        while (len!=-1) {
            System.out.println((char)len);
            len = isr.read();
        }
        isr.close();
    }
}
