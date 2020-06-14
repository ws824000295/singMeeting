package com.boot.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.boot.service.SingleMeetingService;
import com.google.gson.Gson;

public class Server {
	// socket服务端口
	private static int serverSocketPort = 7012;
	private static ServerSocket server;

	// 开启的tcp8888监听端口
	public static void scoketServer() throws IOException {
		server = new ServerSocket(serverSocketPort);
		while (true) {
			// 未连通前线程阻塞，连通后开启一个socket通道线程后继续监听8888端口
			Socket socket = server.accept();
			System.out.println(socket.getInetAddress().getHostAddress() + "连接进入");
			new SocketThread(socket).start();
		}
	}

}

// 一个服务器端口中监听多个客户端通道线程
class SocketThread extends Thread {
	// 所有通道写入流的集合

	private BufferedReader bufferedReader = null;
	private PrintWriter printWriter = null;
	private Socket socket = null;
    private String clientName = null;

	public SocketThread(Socket socket) throws IOException {
		this.socket = socket;
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.printWriter = new PrintWriter(socket.getOutputStream());
	}

	@Override
	public void run() {
		String string = null;
		 String ip = socket.getInetAddress().getHostAddress();
		while (true) {
			try {
				// 服务器在通道获取数据并做相应处理
				string = bufferedReader.readLine();
				System.out.println(string);
				Map<String, Object> map = new Gson().fromJson(string, Map.class);
				System.out.println("account=" + map.get("account") + ",clientIdent=" + map.get("clientIdent"));
				if (map.get("account") != null && map.get("clientIdent") != null) {
					try {
						
						this.clientName = map.get("account").toString();
        				SocketPrintWriter.printMap.put(this.clientName, this.printWriter);//添加信道
        				
						SingleMeetingService singleMeetingService = SpringBeanUtil.getBean(SingleMeetingService.class);
						singleMeetingService.againJoinMeeting(map.get("account").toString(), map.get("clientIdent").toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				 try {
	                	SocketPrintWriter.printMap.remove(this.clientName);
	                	SocketPrintWriter.defaultProject.remove(this.clientName);
	                    if(printWriter!=null) printWriter.close();
	                    if(bufferedReader!=null) bufferedReader.close();
	                    if(socket!=null) socket.close();
	                    System.out.println(ip + "退出，且已经删除了客户端通信信道"+this.clientName);
	                    break;
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
				e.printStackTrace();
			}
		}

	}
}