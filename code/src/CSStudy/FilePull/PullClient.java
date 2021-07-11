package CSStudy.FilePull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PullClient {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\wu197\\Desktop\\WebStudy\\code\\src\\CSStudy\\FilePull\\TCP通信的文件上传案例.txt");
        Socket s = new Socket("localhost", 9999);
        InputStream ips = s.getInputStream();
        readAndload(ips, null);
        OutputStream ops = s.getOutputStream();
        ops.write("你好，服务器，我开始上传了\n".getBytes());
        readAndload(fis, ops);
        s.shutdownOutput();
        fis.close();
        s.close();
    }

    public static void readAndload(InputStream ips, OutputStream ops) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = ips.read(bytes)) != -1) {
            if (ops == null) {
                System.out.println(new String(bytes, 0, len));
            } else {
                ops.write(bytes, 0, len);
            }
        }
    }
}
