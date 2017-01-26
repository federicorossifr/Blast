package blast;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import java.nio.file.*;
import java.nio.*;
public class Blast {
    public static Path filePath;
    public static void main(String[] args) throws Exception {
        if(args.length < 2) {
            System.out.println("Usage: java Blast <port> <filename>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        filePath = Paths.get(args[1]);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/blast", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Download file @ <your-ip>:"+port+"/blast");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println("Sending file to: "+t.getRemoteAddress());
            byte[] f = Files.readAllBytes(filePath);
            t.getResponseHeaders().add("Content-Disposition", "attachment; filename="+filePath.getFileName());            
            t.sendResponseHeaders(200, f.length);
            OutputStream os = t.getResponseBody();
            os.write(f);
            os.close();
        }
    }
} 
