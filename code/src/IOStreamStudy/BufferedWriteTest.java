package IOStreamStudy;

import java.io.*;

public class BufferedWriteTest {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt");
        FileReader fr = new FileReader("C:\\Users\\wu197\\Desktop\\后端学习\\code\\b.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);

        bw.write("你好");
        bw.flush();

        System.out.println(br.read());

        bw.newLine();
        bw.flush();

        System.out.println(br.read());

        bw.write("世界!");

        System.out.println(br.readLine());

        bw.flush();

        System.out.println(br.read());

        bw.close();
    }
}
