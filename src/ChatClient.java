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

	TextField tfTex = new TextField();
	TextArea taContent = new TextArea();

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
	}

	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = tfTex.getText().trim();
			taContent.setText(str);
			tfTex.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
