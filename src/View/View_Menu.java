package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class View_Menu {
	private JFrame view_menu;
	private JButton btn_artpieces;
	private JButton btn_auctions;
	private JButton btn_exit;
	private JButton btn_Collectors;
	private JButton btn_Refresh;


	public View_Menu(){
		
		view_menu = new JFrame("Main_Menu");
		view_menu.setSize(300, 400);
		view_menu.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		view_menu.setLocation(dim.width/2-view_menu.getSize().width/2, dim.height/2-view_menu.getSize().height/2);
		btn_artpieces = new JButton("Art Pieces");
		btn_artpieces.setBounds(39, 62, 200, 50);
		view_menu.getContentPane().add(btn_artpieces);
		
		btn_auctions = new JButton("Auctions");
		btn_auctions.setBounds(39, 123, 200, 50);
		view_menu.getContentPane().add(btn_auctions);
		
		btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_exit.setBounds(39, 306, 200, 50);
		view_menu.getContentPane().add(btn_exit);
		
		JLabel lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMainMenu.setBounds(93, 21, 109, 30);
		view_menu.getContentPane().add(lblMainMenu);
		
		btn_Collectors = new JButton("Collectors");
		btn_Collectors.setBounds(39, 184, 200, 50);
		view_menu.getContentPane().add(btn_Collectors);
		
		btn_Refresh = new JButton("Refresh Connection");
		btn_Refresh.setBounds(39, 245, 200, 50);
		view_menu.getContentPane().add(btn_Refresh);
		
		view_menu.setVisible(true);
	}
	
	public JFrame getView_menu() {
		return view_menu;
	}

	public void setView_menu(JFrame view_menu) {
		this.view_menu = view_menu;
	}

	public JButton getBtn_Collectors() {
		return btn_Collectors;
	}

	public JButton getBtn_artpieces() {
		return btn_artpieces;
	}

	public JButton getBtn_auctions() {
		return btn_auctions;
	}
	public JButton getBtn_exit() {
		return btn_exit;
	}
	public JButton getBtn_Refresh() {
		return btn_Refresh;
	}

	public void setBtn_Refresh(JButton btn_Refresh) {
		this.btn_Refresh = btn_Refresh;
	}
	public void addRefreshListener(ActionListener listen){
		btn_Refresh.addActionListener(listen);
	}
	
	public void addPiecesListener(ActionListener listen){
		btn_artpieces.addActionListener(listen);
	}
	
	public void addAuctionsListener(ActionListener listen){
		btn_auctions.addActionListener(listen);
	}
	
	public void addCollectorsListener(ActionListener listen){
		btn_Collectors.addActionListener(listen);
	}
	public void addExitListener(ActionListener listen){
		btn_exit.addActionListener(listen);
	}
}
