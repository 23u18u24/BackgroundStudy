package CSStudy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        Socket accept = ss.accept();
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();

        outputStream.write("你好，客户端".getBytes());
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        System.out.println(new String(bytes, 0, read));
        accept.close();
        ss.close();
    }
}
