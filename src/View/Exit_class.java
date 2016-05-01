package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class Exit_class {
	JFrame exit_frame;
	JFrame show_frame;
	JButton btn_exit;
	
	public void Set_Exit_class(JFrame exit_frame, JFrame show_frame) {
		this.exit_frame = exit_frame;
		this.show_frame = show_frame;
		
	}
	public Exit_class(){
		btn_exit = new JButton();
		btn_exit.setText("Exit");
	}
	public void addExitListener(ActionListener listen){
		this.btn_exit.addActionListener(listen);
	}
}
