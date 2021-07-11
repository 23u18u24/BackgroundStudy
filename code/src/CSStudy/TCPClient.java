package CSStudy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8888);
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        os.write("你好，服务器".getBytes());
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes, 0, len));
        s.close();
    }
}
