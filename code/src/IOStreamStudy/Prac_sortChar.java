package IOStreamStudy;

import java.io.*;
import java.util.HashMap;

public class Prac_sortChar {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        FileReader fr = new FileReader("C:\\Users\\wu197\\Desktop\\后端学习\\code\\a.txt");
        BufferedReader br = new BufferedReader(fr);
        String txt;
        while ((txt = br.readLine()) != null) {
            String[] txts = txt.split("\\.");
            System.out.println(txts[0]);
            System.out.println(txts[1]);
            hashMap.put(txts[0], txts[1]);
        }
        FileWriter fw = new FileWriter("C:\\Users\\wu197\\Desktop\\后端学习\\code\\a.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (String s : hashMap.keySet()) {
            String value = hashMap.get(s);
            bw.write(value);
        }
        bw.close();
        br.close();
    }
}
