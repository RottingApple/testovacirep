package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.MouseInfo;

import javax.swing.JTextArea;

public class View_Error extends JFrame{
	JFrame screen;
	public View_Error(){
		JTextArea textArea = new JTextArea();
		textArea.setSize(220, 100);
		textArea.append("Wrong input has been detected\n");
		textArea.append("Please verify the input and try again");
		screen = new JFrame("Input Error");
		screen.setVisible(true);
		screen.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
		screen.setSize(240,120);
		screen.getContentPane().setLayout(null);
		screen.add(textArea);
	}
	
	public class Exit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			screen.setVisible(false);
		}
	}
}
