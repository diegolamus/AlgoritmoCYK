package cyk.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cyk.entidades.Variable;
import cyk.modelo.AlgoritmoCYK;


public class CYKVentanaPrincipal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea cantidadProducciones;
	private JButton verRespuestaBoton;
	private JLabel verRespuestaLabel;
	
	
	private JMenuBar barraMenu;
	private JMenu menuarchiv;
	private JMenuItem ayuda;
	
	
	/**
	 * Constantes de los botones
	 */
	private final static String VER_RESPUESTA="Ver Respuesta";
	private final static String CANTIDAD_PRODUCCIONES="Aceptar";
	
	
	/**
	 * panel dinamico en el que se va a escribir las producciones
	 */
	private Panel producciones;
	
	private ArrayList<ArrayList<JTextField>> produccionesCadenas;

	/**
	 * enlace con el modelo
	 */
	private AlgoritmoCYK mundo;
	
	public CYKVentanaPrincipal () {
		setSize(600, 400);
		setTitle("Algoritmo CYK");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mundo = new AlgoritmoCYK();
		barraMenu = new JMenuBar();
		menuarchiv = new JMenu("Sobre este programa");
		ayuda = new JMenuItem("Como usar este programa");
		menuarchiv.add(ayuda);
		barraMenu.add(menuarchiv);
		
		setJMenuBar(barraMenu);
		
		barraMenu.add(menuarchiv);
		
		ayuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// RUTA DEL ARCHIVO EN DATA
					File path = new File("data/Instrucciones.pdf");
					Desktop.getDesktop().open(path);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
			
		
		//declaracion del panel donde se van a introducir la producciones
		producciones = new Panel();
		
		//panel superior que muestra un label que indica escribir la cantidad de producciones
		Panel panelAux= new Panel();
		panelAux.setLayout(new GridLayout(1, 3));
		panelAux.add(new JLabel("Cantidad de producciones: "));
		cantidadProducciones = new JTextArea();
		panelAux.add(cantidadProducciones);
		
		//boton que controla la creacion del panel estatico
		JButton cantidadProduccionesBoton = new JButton(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.setActionCommand(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(producciones);
				producciones = new Panel();
				modificarPanelProducciones(Integer.parseInt(cantidadProducciones.getText()));
				add(producciones, BorderLayout.CENTER);
				
				
			}
		});
		panelAux.add(cantidadProduccionesBoton);
		
		add(panelAux, BorderLayout.NORTH);
		
		
		
		
		Panel panelAuxInferior = new Panel();
		panelAuxInferior.setLayout(new GridLayout(2, 2));
		panelAuxInferior.add(new JLabel("Cadena a probar: "));
		JTextField cadenaAProbar = new JTextField();
		panelAuxInferior.add(cadenaAProbar);
		
		verRespuestaBoton = new JButton(VER_RESPUESTA);
		verRespuestaBoton.setActionCommand(VER_RESPUESTA);
		verRespuestaBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Variable [] variablesEntradas = new Variable[Integer.parseInt(cantidadProducciones.getText())];
				for (int i = 0; i < produccionesCadenas.size(); i++) {
					Variable temporal = new Variable(produccionesCadenas.get(i).get(0).getText(), produccionesCadenas.get(i).get(1).getText().split(","));
					variablesEntradas[i]= temporal;
				}
				//TODO
				String [][] respuestaAMostrar = mundo.generaMatriz(variablesEntradas, cadenaAProbar.getText());
				verRespuestaLabel.setText(respuestaAMostrar[0][respuestaAMostrar.length-1]);
				
			}
		});
		
		
		verRespuestaLabel = new JLabel("Aun no se ha dado una respuesta");
		
		panelAuxInferior.add(verRespuestaBoton);
		panelAuxInferior.add(verRespuestaLabel);
		
		
		
		add(panelAuxInferior, BorderLayout.SOUTH);
		
	}

	
	/**
	 * Modifica el tamaño del panel para recibir la cantidad de producciones que se van a evaluar.
	 * @param caantidadProducciones cantidad de producciones que se van a evaluar. Acorde a la cantidad se expande el panel.
	 */
	public void modificarPanelProducciones (int caantidadProducciones) {
		producciones.setLayout(new GridLayout(caantidadProducciones+1, 2) );
		producciones.add(new JLabel("Produccion "));
		producciones.add(new JLabel("Variables que produce"));
		produccionesCadenas = new ArrayList<ArrayList<JTextField>>();
		for (int i = 0; i < caantidadProducciones; i++) {
			JTextField a = new JTextField();
			JTextField b = new JTextField();
			ArrayList<JTextField> temp = new ArrayList<JTextField>();
			temp.add(a);
			temp.add(b);
			produccionesCadenas.add(temp);
			producciones.add(produccionesCadenas.get(i).get(0));
			producciones.add(produccionesCadenas.get(i).get(1));
			
		}
		
		this.pack();
		this.setSize(600, 110 + caantidadProducciones*50);
	}
	
	

	public static void main(String[] args) {
		CYKVentanaPrincipal principal = new CYKVentanaPrincipal();
		principal.setVisible(true);

	}

}
