package IOStreamStudy;

import java.io.*;

public class OutputStreamWriterTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\a.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        osw.write("你好");

        osw.close();
        fos.close();
    }
}
