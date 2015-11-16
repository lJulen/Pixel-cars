package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

public class VentanaLog extends JFrame implements ActionListener {
	JTextField textField;
	JTextField textField_1;
	JFrame frame;
	public VentanaLog(){
		frame = new JFrame("Log in");
		setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(107, 35, 171, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Bookshelf Symbol 7", Font.BOLD, 11));
		textField_1.setBounds(107, 71, 171, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(285, 128, 89, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(32, 38, 46, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(32, 74, 65, 14);
		getContentPane().add(lblContrasea);		
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "Log in":VentanaUsuario Usuario=new VentanaUsuario();
			String nombre=textField.getText();
			String contra=textField_1.getText();
			if(nombre.equals("Julen") && contra.equals("Garro")){
				JOptionPane.showMessageDialog(null,"Bienvenido");
				Usuario.setVisible(true);
			}
			/*else{
				JOptionPane.showMessageDialog(null,"ERROR");
			}*/
		this.dispose();
			}
	
	}	
		
}
