package CSStudy.FilePull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CurrentServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(9999);
        Socket accept = ss.accept();
        OutputStream ops = accept.getOutputStream();
        ops.write("你好，客户端".getBytes());
        InputStream ips = accept.getInputStream();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\src\\CSStudy\\FilePull\\成功上传.txt");
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = ips.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
            fos.write(bytes, 0, len);
        }
        ops.write("上传成功".getBytes());
        accept.close();
        ss.close();
        fos.close();
    }
}
