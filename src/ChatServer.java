/*************************************************************************
	> File Name: ChatServer.java
	> Author: Bslin
	> Mail:  
	> Created Time: 2014年02月08日 星期六 17时40分59秒
 ************************************************************************/

import java.awt.*;
import java.net.*;
import java.io.*;

public class ChatServer {


	public static void main(String[] args) {
		DataOutputStream dos = null;
		DataInputStream dis = null;
		boolean started = false;
		try {
			ServerSocket ss = new ServerSocket(8888);
			started = true;
			while(started) {
				boolean bConnected = false;
				Socket s = ss.accept();
System.out.println("a client connected!");
				bConnected = true;
				dos = new DataOutputStream(s.getOutputStream());
				dis = new DataInputStream(s.getInputStream());
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println(str);
				}
				dis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
