package IOStreamStudy;

import java.io.FileWriter;
import java.io.IOException;

public class FFileWriter {
    public static void main(String[] args) throws IOException {
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt",true);
//            fw.write("你好");
//            fw.flush();
//            fw.write("\n");
//            fw.write("中国！");
//
//        } catch (IOException e) {
//            System.out.println(e);
//        } finally {
//            if (fw != null) {
//                try {
//                    fw.close();
//                } catch (IOException e) {
//                    System.out.println(e);
//                }
//            }
//        }

        //JDK7
//        try(FileWriter fw = new FileWriter("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt",true)){
//            fw.write("你好");
//            fw.flush();
//            fw.write("\n");
//            fw.write("中国！");
//        } catch (IOException e){
//            System.out.println(e);
//        }

        //JDK9
        FileWriter fw = new FileWriter("C:\\Users\\23u18u24\\Desktop\\后端学习\\code\\b.txt",true);
        try(fw) {
            fw.write("你好");
            fw.flush();
            fw.write("\n");
            fw.write("中国！");

        } catch (IOException e) {
            System.out.println(e);
        }
        fw.close();
    }
}
