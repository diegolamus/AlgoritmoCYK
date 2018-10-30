package cyk.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CYKVentanaPrincipal extends JFrame{
	private JTextArea cantidadProducciones;
	private JButton verRespuesta;
	private JLabel verRespuestaLabel;
	
	
	/**
	 * Constantes de los botones
	 */
	private final static String VER_RESPUESTA="Ver Respuesta";
	private final static String CANTIDAD_PRODUCCIONES="Aceptar";
	
	
	private Panel producciones;

	
	public CYKVentanaPrincipal () {
		setSize(600, 400);
		setTitle("Algoritmo CYK");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//declaracion del panel donde se van a introducir la producciones
		producciones = new Panel();
		
		//panel superior que muestra un label que indica escribir la cantidad de producciones
		Panel panelAux= new Panel();
		panelAux.setLayout(new GridLayout(1, 3));
		panelAux.add(new JLabel("Cantidad de producciones: "));
		cantidadProducciones = new JTextArea();
		panelAux.add(cantidadProducciones);
		
		JButton cantidadProduccionesBoton = new JButton(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.setActionCommand(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarPanelProducciones(Integer.parseInt(cantidadProducciones.getText()));
				add(producciones, BorderLayout.CENTER);
				
				
			}
		});
		panelAux.add(cantidadProduccionesBoton);
		
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
	
	public void modificarPanelProducciones (int caantidadProducciones) {
		producciones.setLayout(new GridLayout(caantidadProducciones+1, 2) );
		producciones.add(new JLabel("Produccion "));
		producciones.add(new JLabel("Variables que produce"));
		for (int i = 0; i < caantidadProducciones; i++) {
			producciones.add(new JTextField());
			producciones.add(new JTextField());
		}
		//this.remove(producciones);
		this.pack();
	}
	
	

	public static void main(String[] args) {
		CYKVentanaPrincipal principal = new CYKVentanaPrincipal();
		principal.setVisible(true);

	}

}
