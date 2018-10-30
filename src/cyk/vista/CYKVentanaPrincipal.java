package cyk.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class CYKVentanaPrincipal extends JFrame{
	private JTextArea cantidadProducciones;
	private JButton verRespuesta;
	private JLabel verRespuestaLabel;
	private final static String VER_RESPUESTA="Ver Respuesta";
	
	public CYKVentanaPrincipal () {
		setSize(600, 400);
		setTitle("Algoritmo CYK");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//panel superior que muestra un label que indica escribir la cantidad de producciones
		Panel panelAux= new Panel();
		panelAux.setLayout(new GridLayout(1, 2));
		panelAux.add(new JLabel("Cantidad de producciones: "));
		cantidadProducciones = new JTextArea();
		panelAux.add(cantidadProducciones);
		add(panelAux, BorderLayout.NORTH);
		
		
		
		
		
		
		Panel panelAuxInferior = new Panel();
		panelAuxInferior.setLayout(new GridLayout(1, 2));
		verRespuesta = new JButton(VER_RESPUESTA);
		verRespuesta.setActionCommand(VER_RESPUESTA);
		verRespuestaLabel = new JLabel("Aun no se ha dado una respuesta");
		
		panelAuxInferior.add(verRespuesta);
		panelAuxInferior.add(verRespuestaLabel);
		
		add(panelAuxInferior, BorderLayout.SOUTH);
		
	}
	
	
	

	public static void main(String[] args) {
		CYKVentanaPrincipal principal = new CYKVentanaPrincipal();
		principal.setVisible(true);

	}

}
