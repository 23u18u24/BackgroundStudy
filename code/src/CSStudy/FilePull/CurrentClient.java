package CSStudy.FilePull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CurrentClient {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\src\\CSStudy\\FilePull\\TCP通信的文件上传案例.txt");
        Socket s = new Socket("localhost", 9999);
        OutputStream ops = s.getOutputStream();
        ops.write("你好，服务器，我开始上传了\n".getBytes());
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            ops.write(bytes, 0, len);
        }
        s.shutdownOutput();
        InputStream ips = s.getInputStream();
        while ((len = ips.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        fis.close();
        s.close();
    }
}
