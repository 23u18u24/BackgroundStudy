package CSStudy.FilePull;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PullServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(9999);
        Socket accept = ss.accept();
        OutputStream ops = accept.getOutputStream();
        ops.write("你好，客户端".getBytes());
        InputStream ips = accept.getInputStream();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\src\\CSStudy\\FilePull\\成功上传.txt");
        PullClient.readAndload(ips, fos);
        ops.write("上传成功".getBytes());
        PullClient.readAndload(ips, null);
        ss.close();
        fos.close();
    }
}
