package IOStreamStudy;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamReaderTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\wu197\\Desktop\\后端学习\\code\\a.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read();

        bis.close();
        fis.close();

        FileReader fr = new FileReader("C:\\Users\\wu197\\Desktop\\后端学习\\code\\a.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

    }
}
