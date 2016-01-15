package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.sql.SQLException;



public class Informacion extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JButton btnCoche;
	private JButton btnMoto;
	private JButton btnCamion;
	private JButton btnMejores;
	private JButton btnRecord;
	private JLabel lblEstadsticas;
	PanelConImagen contentPane;
	private JTextField Tiempo1;
	private JTextField Tiempo2;
	private JTextField Tiempo3;
	

	public Informacion() 
	{
		setBounds(100, 100, 580, 400);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(contentPane.createImage("/Imagen/Pixel-Cars.jpg").getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCoche = new JButton("Coche");
		btnCoche.setFont(new Font("Jokerman", Font.PLAIN, 18));
		btnCoche.setBounds(36, 105, 148, 37);
		contentPane.add(btnCoche);
		btnCoche.addActionListener(this);
		
		btnMoto = new JButton("Moto");
		btnMoto.setFont(new Font("Jokerman", Font.PLAIN, 18));
		btnMoto.setBounds(36, 153, 148, 37);
		contentPane.add(btnMoto);
		btnMoto.addActionListener(this);
		
		btnCamion = new JButton("Camion");
		btnCamion.setFont(new Font("Jokerman", Font.PLAIN, 18));
		btnCamion.setBounds(36, 201, 148, 37);
		contentPane.add(btnCamion);
		btnCamion.addActionListener(this);
		
		btnMejores = new JButton("Mis Records");
		btnMejores.setFont(new Font("Jokerman", Font.PLAIN, 18));
		btnMejores.setBounds(36, 249, 148, 37);
		contentPane.add(btnMejores);
		btnMejores.addActionListener(this);
		
		btnRecord = new JButton("Record");
		btnRecord.setFont(new Font("Jokerman", Font.PLAIN, 18));
		btnRecord.setBounds(36, 297,148,37);
		contentPane.add(btnRecord);
		btnRecord.addActionListener(this);
		
		lblEstadsticas = new JLabel("ESTAD\u00CDSTICAS");
		lblEstadsticas.setBackground(Color.DARK_GRAY);
		lblEstadsticas.setFont(new Font("Jokerman", Font.BOLD | Font.ITALIC, 32));
		lblEstadsticas.setBounds(197, 11, 300, 61);
		contentPane.add(lblEstadsticas);
		
		Tiempo1 = new JTextField();
		Tiempo1.setBounds(270, 138, 155, 31);
		contentPane.add(Tiempo1);
		Tiempo1.setColumns(10);
		
		Tiempo2 = new JTextField();
		Tiempo2.setColumns(10);
		Tiempo2.setBounds(270, 180, 155, 31);
		contentPane.add(Tiempo2);
		
		Tiempo3 = new JTextField();
		Tiempo3.setColumns(10);
		Tiempo3.setBounds(270, 222, 155, 31);
		contentPane.add(Tiempo3);
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{ 
			case "Coche": 
				
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
				try {
					BaseDatos.devolverTiemposCoche();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Tiempo1.setText(BaseDatos.getPrimerTimeCoche());
				Tiempo2.setText(BaseDatos.getSegundoTimeCoche());
				Tiempo3.setText(BaseDatos.getTercerTimeCoche());
				System.out.println(BaseDatos.getPrimerTimeCoche());
				BaseDatos.close();
				break;
				
			case "Moto":
				
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
				try {
					BaseDatos.devolverTiemposMoto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tiempo1.setText(BaseDatos.getPrimerTimeMoto());
				Tiempo2.setText(BaseDatos.getSegundoTimeMoto());
				Tiempo3.setText(BaseDatos.getTercerTimeMoto());
				BaseDatos.close();
				break;
			
			case "Camion":
				
	
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
				try {
					BaseDatos.devolverTiemposCamion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tiempo1.setText(BaseDatos.getPrimerTimeCamion());
				Tiempo2.setText(BaseDatos.getSegundoTimeCamion());
				Tiempo3.setText(BaseDatos.getTercerTimeCamion());
				BaseDatos.close();
				break;
				
			case "Mis Records":
	
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
				try {
					BaseDatos.MisMejoresTiempos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tiempo1.setText(BaseDatos.getPrimerTimeRecord());
				Tiempo2.setText(BaseDatos.getSegundoTimeRecord());
				Tiempo3.setText(BaseDatos.getTercerTimeRecord());
				BaseDatos.close();
				break;
				
			case "Record":
					
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
				try {
					BaseDatos.MejoresTiempos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tiempo1.setText(BaseDatos.getPrimerTimeRecordTotal());
				Tiempo2.setText(BaseDatos.getSegundoTimeRecordTotal());
				Tiempo3.setText(BaseDatos.getTercerTimeRecordTotal());
				BaseDatos.close();
				break;
		}
		
	}
}
