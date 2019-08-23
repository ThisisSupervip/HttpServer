package com.thisisvip;

import com.thisisvip.enums.ContentType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class WebServer implements Runnable {

    private static Logger logger = Logger.getLogger(WebServer.class.getName());

    private ServerSocket serverSocket = null;

    private Socket socket = null;

    private static final int PORT = 8081;

    //线程数
    private static final int N_THREADS = 3;

    public static void main(String[] args) {

        // ExecutorService executor = new ThreadPoolExecutor(N_THREADS,N_THREADS,
        //       10L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(N_THREADS));
        WebServer server = new WebServer(getPortParam(args));
        server.listener();

    }

    WebServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            logger.info("http服务器在" + port + "开启");
        } catch (IOException e) {

        }
    }

    private void listener(){
        while (true) {
            try {
                socket = serverSocket.accept();
                Thread t = new Thread(this);
                t.start();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String buffer;
            logger.info("\n\n客户端:");
            while ((buffer = br.readLine()) != null && !buffer.equals("")) {
                System.out.println(buffer);
            }
            bw.write("HTTP/1.1 200 OK\n");
            bw.write("Content-Type: " + ContentType.HTML + "; charset=UTF-8\n\n");
            bw.write("<html><body><h1>Hello World</h1></body></html>");
            bw.flush();
            bw.close();
            br.close();
            socket.close();

        } catch (IOException e) {

        }
    }

    private static int getPortParam(String[] args) throws NumberFormatException {
        if (args.length > 0) {
            int port = Integer.parseInt(args[0]);
            if (port > 0 && port < 65535) {
                return port;
            } else {
                throw new NumberFormatException("请输入合法的端口号（0-65535）");
            }
        } else {
            return PORT;
        }
    }
}
