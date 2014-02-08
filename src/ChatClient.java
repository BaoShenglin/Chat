/*************************************************************************
	> File Name: ChatClient.java
	> Author: Bslin
	> Mail:  
	> Created Time: 2014年02月08日 星期六 16时04分41秒
 ************************************************************************/

import java.awt.*;
import java.awt.event.*;

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
		addWindowListener(new WindowMonitor());
		pack();
		setVisible(true);
	}

	class WindowMonitor extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}

