package IOStreamStudy;

import java.io.*;

public class GBKtoUTF8 {
    public static void main(String[] args) throws IOException {
        transEnc("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\a.txt");
    }

    private static void transEnc(String filepath) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "gbk");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream((new File(filepath).getParentFile()) + "utf_8.txt"), "utf-8");
        int txt;
        while ((txt = isr.read()) != -1) {
            osw.write(txt);
        }
        osw.close();
        isr.close();
    }
}
