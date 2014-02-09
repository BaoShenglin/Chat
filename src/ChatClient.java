/*************************************************************************
	> File Name: ChatClient.java
	> Author: Bslin
	> Mail:  
	> Created Time: 2014年02月08日 星期六 16时04分41秒
 ************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame {

	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	private boolean bConnected = false;

	TextField tfTex = new TextField();
	TextArea taContent = new TextArea();

	Thread tRecv = new Thread(new RecvThread());

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		setLocation(400, 300);
		setSize(300, 300);
		add(taContent, BorderLayout.NORTH);
		add(tfTex, BorderLayout.SOUTH);
		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		tfTex.addActionListener(new TFListener());
		setVisible(true);
		connect();

		// new Thread(new RecvThread()).start();
		tRecv.start();
	}

	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
System.out.println("connected!");
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			dos.close();
			dis.close();
			s.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		/*
		try {
			bConnected = false;
			tRecv.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
				s.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}

	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = tfTex.getText().trim();
			// taContent.setText(str);
			tfTex.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private class RecvThread implements Runnable {
		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					// System.out.println(str);
					taContent.setText(taContent.getText() + str + '\n');
				}
			} catch (SocketException e) {
				System.out.println("bye!");
			} catch (EOFException e){
				System.out.println("bye bye!");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

}
