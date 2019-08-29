package com.thisisvip;

import com.thisisvip.enums.ContentType;
import sun.nio.ch.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebServer implements Runnable {

    private static Logger logger = Logger.getLogger(WebServer.class.getName());

    private ServerSocket serverSocket = null;

    private Socket socket = null;

    private static final int PORT = 8081;

    //线程数
    private static final int N_THREADS = 3;

    public static void main(String[] args) {


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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(N_THREADS,N_THREADS,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        while (true) {
            try {
                socket = serverSocket.accept();
                Thread t = new Thread(this);
                t.start();
                //threadPoolExecutor.execute(this);
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void run() {
        try {


            System.out.println("===================");
            HttpRequest request = new HttpRequest(socket.getInputStream());
            HttpResponse response = new HttpResponse(request);
            System.out.println(request.toString());
            System.out.println(response.responseHeader);
            System.out.println("===================");
            OutputStream out = socket.getOutputStream();
            out.write(response.toBytes());
            out.flush();
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
