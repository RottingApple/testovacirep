package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class View_Art_Pieces extends Exit_class{
		private JFrame artpiece_frame;
		private JTable table;
		private JTextField textField_name;
		private JTextField textField_date;
		private JTextField textField_value;
		private JButton btn_Add;
		private JButton btn_Remove;
		private JButton btn_Edit;
		private JButton btn_AddAuthrs;
		private JComboBox<String> comboBox_Category;
		private JComboBox<String> comboBox_Authors;
		private JComboBox<String> comboBox_Galery;
		private JButton btn_next;
		private JComboBox<String> comboBox_cat;
		private JButton btn_Filter;
		private String[] columnNames = {"Name","Category","Created","In Galery","Value","ID"};
		private JTextField textField_fvalue;
		private DefaultTableModel model;
		private JComboBox<String> comboBox_style;
		private JTextField textField_3;
		private JButton btn_search;

	public View_Art_Pieces(JFrame show_frame){
		super();
		artpiece_frame = new JFrame("Art_Pieces");
		artpiece_frame.setSize(1000,700);
		artpiece_frame.getContentPane().setLayout(null);
		artpiece_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		artpiece_frame.setLocation(dim.width/2-artpiece_frame.getSize().width/2, dim.height/2-artpiece_frame.getSize().height/2);
		
		Set_Exit_class(show_frame, this.artpiece_frame);
		JScrollPane scrp_table = new JScrollPane();
		scrp_table.setSize(530, 480);
		scrp_table.setLocation(450, 125);
		artpiece_frame.getContentPane().add(scrp_table);
		model = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		table = new JTable(model);
		scrp_table.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(	250,100));
		table.setFillsViewportHeight(true);
		
		btn_Add = new JButton("Add Piece");
		btn_Add.setBounds(10, 11, 147, 45);
		artpiece_frame.getContentPane().add(btn_Add);
		
		btn_Remove = new JButton(" Remove Selected");
		btn_Remove.setBounds(10, 125, 147, 45);
		artpiece_frame.getContentPane().add(btn_Remove);
		
		btn_Edit = new JButton("Change Location");
		btn_Edit.setBounds(10, 67, 147, 45);
		artpiece_frame.getContentPane().add(btn_Edit);
		
		textField_name = new JTextField();
		textField_name.setBounds(285, 91, 157, 33);
		artpiece_frame.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		JLabel lblname = new JLabel("Name");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblname.setBounds(213, 98, 46, 14);
		artpiece_frame.getContentPane().add(lblname);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategory.setBounds(213, 238, 57, 26);
		artpiece_frame.getContentPane().add(lblCategory);
		
		JLabel lblCreated = new JLabel("Created");
		lblCreated.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreated.setBounds(213, 142, 57, 14);
		artpiece_frame.getContentPane().add(lblCreated);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValue.setBounds(213, 196, 46, 14);
		artpiece_frame.getContentPane().add(lblValue);
		
		textField_date = new JTextField();
		textField_date.setBounds(285, 135, 157, 33);
		artpiece_frame.getContentPane().add(textField_date);
		textField_date.setColumns(10);
		
		textField_value = new JTextField();
		textField_value.setBounds(285, 183, 157, 33);
		artpiece_frame.getContentPane().add(textField_value);
		textField_value.setColumns(10);
		
	    comboBox_Category = new JComboBox<String>();
		comboBox_Category.setBounds(285, 243, 157, 20);
		artpiece_frame.getContentPane().add(comboBox_Category);
		
		JLabel lblAuthors = new JLabel("Authors");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthors.setBounds(213, 330, 57, 14);
		artpiece_frame.getContentPane().add(lblAuthors);
		
		comboBox_Authors = new JComboBox<String>();
		comboBox_Authors.setBounds(285, 329, 157, 20);
		artpiece_frame.getContentPane().add(comboBox_Authors);
		
		btn_AddAuthrs = new JButton("Add Author");
		btn_AddAuthrs.setBounds(305, 376, 105, 23);
		artpiece_frame.getContentPane().add(btn_AddAuthrs);
		
		JLabel lblGalery = new JLabel("Galery");
		lblGalery.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGalery.setBounds(213, 287, 46, 26);
		artpiece_frame.getContentPane().add(lblGalery);
		
		comboBox_Galery = new JComboBox<String>();
		comboBox_Galery.setBounds(285, 287, 157, 20);
		artpiece_frame.getContentPane().add(comboBox_Galery);
		
		btn_exit.setBounds(10, 605, 128, 45);
		artpiece_frame.getContentPane().add(btn_exit);
		
		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(414, 30, 65, 40);
		artpiece_frame.getContentPane().add(lblNewLabel);
		
		textField_fvalue = new JTextField();
		textField_fvalue.setColumns(10);
		textField_fvalue.setBounds(757, 46, 94, 20);
		artpiece_frame.getContentPane().add(textField_fvalue);
		
		btn_next = new JButton("Next Page");
		btn_next.setBounds(846, 605, 128, 45);
		artpiece_frame.getContentPane().add(btn_next);
		
		JLabel lblNewLabel_1 = new JLabel("Galery");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(521, 11, 65, 20);
		artpiece_frame.getContentPane().add(lblNewLabel_1);
		
		comboBox_cat = new JComboBox<String>();
		comboBox_cat.setBounds(489, 46, 128, 20);
		artpiece_frame.getContentPane().add(comboBox_cat);
		
		JLabel lblNewLabel_2 = new JLabel("Value");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(788, 7, 46, 31);
		artpiece_frame.getContentPane().add(lblNewLabel_2);
		
		btn_Filter = new JButton("Filter");
		btn_Filter.setBounds(885, 45, 89, 23);
		artpiece_frame.getContentPane().add(btn_Filter);
		
		JLabel lblStyle = new JLabel("Style");
		lblStyle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStyle.setBounds(662, 14, 46, 14);
		artpiece_frame.getContentPane().add(lblStyle);
		
		comboBox_style = new JComboBox();
		comboBox_style.setBounds(634, 46, 96, 20);
		artpiece_frame.getContentPane().add(comboBox_style);
		
		JLabel lblSearchSpecificArt = new JLabel("Search");
		lblSearchSpecificArt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSearchSpecificArt.setBounds(213, 410, 89, 33);
		artpiece_frame.getContentPane().add(lblSearchSpecificArt);
		
		JLabel lblArtPiece = new JLabel("Art Piece");
		lblArtPiece.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArtPiece.setBounds(130, 461, 57, 14);
		artpiece_frame.getContentPane().add(lblArtPiece);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(223, 454, 200, 33);
		artpiece_frame.getContentPane().add(textField_3);
		
		btn_search = new JButton("Show Details");
		btn_search.setBounds(249, 498, 147, 33);
		artpiece_frame.getContentPane().add(btn_search);
	
		//view_window.setVisible(true);
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public JButton getBtn_Filter() {
		return btn_Filter;
	}

	public void setBtn_Filter(JButton btn_Filter) {
		this.btn_Filter = btn_Filter;
	}

	public JComboBox<String> getComboBox_cat() {
		return comboBox_cat;
	}

	public void setComboBox_cat(JComboBox<String> comboBox_cat) {
		this.comboBox_cat = comboBox_cat;
	}

	public JTextField getTextField_name() {
		return textField_name;
	}

	public void setTextField_name(JTextField textField_name) {
		this.textField_name = textField_name;
	}

	public JTextField getTextField_value() {
		return textField_value;
	}

	public void setTextField_value(JTextField textField_value) {
		this.textField_value = textField_value;
	}

	public JTextField getTextField_fvalue() {
		return textField_fvalue;
	}

	public void setTextField_fvalue(JTextField textField_fvalue) {
		this.textField_fvalue = textField_fvalue;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JFrame getView_window() {
		return artpiece_frame;
	}

	public void setView_window(JFrame view_window) {
		this.artpiece_frame = view_window;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public JComboBox<String> getComboBox_style() {
		return comboBox_style;
	}

	public void setComboBox_style(JComboBox<String> comboBox_style) {
		this.comboBox_style = comboBox_style;
	}
	
	public JTextField getTextField() {
		return textField_name;
	}

	public void setTextField(JTextField textField) {
		this.textField_name = textField;
	}

	public JTextField getTextField_date() {
		return textField_date;
	}

	public void setTextField_date(JTextField textField_1) {
		this.textField_date = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_value;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_value = textField_2;
	}

	public JButton getBtn_Add() {
		return btn_Add;
	}

	public void setBtn_Add(JButton btn_Add) {
		this.btn_Add = btn_Add;
	}

	public JButton getBtn_Remove() {
		return btn_Remove;
	}

	public void setBtn_Remove(JButton btn_Remove) {
		this.btn_Remove = btn_Remove;
	}

	public JButton getBtn_Edit() {
		return btn_Edit;
	}

	public void setBtn_Edit(JButton btn_Edit) {
		this.btn_Edit = btn_Edit;
	}

	public JButton getBtn_AddAuthrs() {
		return btn_AddAuthrs;
	}

	public void setBtn_AddAuthrs(JButton btn_AddAuthrs) {
		this.btn_AddAuthrs = btn_AddAuthrs;
	}

	public JComboBox<String> getComboBox_Category() {
		return comboBox_Category;
	}

	public void setComboBox_Category(JComboBox<String> comboBox_Category) {
		this.comboBox_Category = comboBox_Category;
	}

	public JComboBox<String> getComboBox_Authors() {
		return comboBox_Authors;
	}

	public void setComboBox_Authors(JComboBox<String> comboBox_Authors) {
		this.comboBox_Authors = comboBox_Authors;
	}

	public JComboBox<String> getComboBox_Galery() {
		return comboBox_Galery;
	}

	public void setComboBox_Galery(JComboBox<String> comboBox_Galery) {
		this.comboBox_Galery = comboBox_Galery;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public JButton getBtn_Exit() {
		return btn_exit;
	}

	public void setBtn_Exit(JButton btn_Return) {
		this.btn_exit = btn_Return;
	}

	public JButton getBtn_search() {
			return btn_search;
		}
	public void setBtn_search(JButton btn_search) {
			this.btn_search = btn_search;
		}

	public void addAddListener(ActionListener listen){
		this.btn_Add.addActionListener(listen);
	}
	public void addFilterListener(ActionListener listen){
		this.btn_Filter.addActionListener(listen);
	}
	public void addAuthorListener(ActionListener listen){
		this.btn_AddAuthrs.addActionListener(listen);
	}
	public void addEditListener(ActionListener listen){
		this.btn_Edit.addActionListener(listen);
	}
	public void addRemoveListener(ActionListener listen){
		this.btn_Remove.addActionListener(listen);
	}
	public void addNextPageListener(ActionListener listen){
		this.btn_next.addActionListener(listen);
	}
	public void addSearchListener(ActionListener listen){
		this.btn_search.addActionListener(listen);
	}
}
