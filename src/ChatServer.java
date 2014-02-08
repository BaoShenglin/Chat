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
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true) {
				Socket s = ss.accept();
System.out.println("a client connected!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
