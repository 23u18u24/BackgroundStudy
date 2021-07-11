package IOStreamStudy;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt", Charset.forName("utf-8"));
        BufferedReader br = new BufferedReader(fr);
        System.out.println(br.readLine());
        br.close();
    }
}
