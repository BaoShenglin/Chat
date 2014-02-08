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
				System.exit(0);
			}
		});
		tfTex.addActionListener(new TFListener());
		setVisible(true);
		connect();
	}

	public void connect() {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = tfTex.getText().trim();
			taContent.setText(taContent.getText() + str);
			tfTex.setText("");
		}
	}

}
