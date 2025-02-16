package Tema4_ServiciosEnRed.ServidorArchivos;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.Socket;
import java.awt.*;
import java.awt.event.*;

public class ClienteFicheros extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;	
	static Socket socket;
	EstructuraFicheros estructuraFicherosNodo = null;
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
    EstructuraFicheros estructuraFicherosRaiz;

	// campos de cabecera parte superior
	static JTextField cab = new JTextField();
	static JTextField cab2 = new JTextField();
	static JTextField cab3 = new JTextField();

	// campos de mensajes parte inferior
	private static JTextField campo = new JTextField();
	private static JTextField campo2 = new JTextField();

	// botones
	JButton botonSubir = new JButton("Subir fichero");
	JButton botonDescargar = new JButton("Descargar fichero");
	JButton botonSalir = new JButton("Salir");
	
	// lista para los datos del directorio
	@SuppressWarnings("rawtypes")
	static JList listaDirectorios = new JList();
	
	// contenedor
	private final Container container = getContentPane();	
	
	// para saber directorio y fichero seleccionado
	static String direcSelec = "";
	static String ficheroSelec = "";
	static String ficheroCompleto = "";

	// constructor
	public ClienteFicheros(Socket socketIn) throws IOException {
		super("SERVIDOR DE FICHEROS BÁSICO");
		socket = socketIn;
		try {
			// flujo de salida -envio objeto
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			// flujo de entrada -recibo objeto
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		campo.setBounds(new Rectangle(3, 385, 485, 30));
		campo.setForeground(Color.blue);
		campo.setFont(new Font("Verdana", Font.BOLD, 12));
		campo2.setBounds(new Rectangle(3, 415, 485, 30));
		campo2.setForeground(Color.blue);
		campo2.setFont(new Font("Verdana", Font.BOLD, 12));

		cab.setBounds(new Rectangle(5, 5, 400, 30));
		cab.setBorder(null);
		cab.setForeground(Color.blue);
		cab.setFont(new Font("Arial", Font.BOLD, 14));

		cab2.setBounds(new Rectangle(350, 5, 140, 30));
		cab2.setBorder(null);
		cab2.setFont(new Font("Arial", Font.BOLD, 14));
		cab2.setForeground(Color.blue);

		cab3.setBounds(new Rectangle(5, 34, 300, 30));
		cab3.setBorder(null);
		cab3.setFont(new Font("Arial", Font.BOLD, 14));
		cab3.setForeground(Color.blue);

		botonSubir.setBounds(new Rectangle(350, 100, 140, 30));
		botonDescargar.setBounds(new Rectangle(350, 150, 140, 30));
		botonSalir.setBounds(new Rectangle(350, 200, 140, 30));
	
		listaDirectorios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// SINGLE_INTERVAL_SELECTION);
		JScrollPane barraDesplazamiento = new JScrollPane(listaDirectorios);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 300));
		barraDesplazamiento.setBounds(new Rectangle(5, 65, 335, 300));
		container.add(barraDesplazamiento);
		container.setLayout(null);

		container.add(campo);
		campo.setEditable(false);
		container.add(campo2);
		campo2.setEditable(false);
		container.add(botonSubir);
		container.add(botonDescargar);

		container.add(botonSalir);

		container.add(cab);
		container.add(cab2);
		container.add(cab3);
		cab.setEditable(false);
		cab2.setEditable(false);
		cab3.setEditable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(510, 450);
		setVisible(true);

		// --clic en un elemento de la lista
		listaDirectorios.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				if (listSelectionEvent.getValueIsAdjusting()) {
					ficheroSelec = "";
					ficheroCompleto = "";
				    estructuraFicherosNodo = (EstructuraFicheros) listaDirectorios.getSelectedValue();					
					if (estructuraFicherosNodo.isDir()) {
						// es un directorio
                       campo.setText("FUNCIÓN NO IMPLEMENTADA..... " );
					} else {
						// SE TRATA DE UN FICHERO
						ficheroSelec = estructuraFicherosNodo.getName();
						ficheroCompleto = estructuraFicherosNodo.getPath();
						campo.setText("FICHERO seleccionado: " + ficheroSelec);
					}// fin else
				}//
			}
		});// fin lista

		// --al hacer clic en el botón Salir
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket.close();					
					System.exit(0);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		// --al hacer clic en el botón Actualizar
		
		
		// --al hacer clic en el botón Descargar
		botonDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (ficheroCompleto.equals(""))
					return;
				System.out.println("PIDO ESTE FICHERO " + ficheroCompleto);
				PideFichero pideFichero = new PideFichero(ficheroCompleto);

				try {
					objectOutputStream.writeObject(pideFichero);
					// pido el fichero
					// Se abre un fichero para empezar a copiar lo que se
					// reciba. 
					FileOutputStream fileOutputStream = new FileOutputStream(ficheroSelec);
					// Se crea un ObjectInputStream del socket para leer los
					// bytes del fichero
					// obtengo los bytes
					Object obtengo = objectInputStream.readObject();
					if (obtengo instanceof ObtieneFichero) {
				        ObtieneFichero obtieneFichero = (ObtieneFichero)obtengo;						
						fileOutputStream.write(obtieneFichero.getContenidoFichero());
						fileOutputStream.close();
						JOptionPane.showMessageDialog(null,	"FICHERO DESCARGADO");
					}

				} catch (IOException e1) {e1.printStackTrace();}
				  catch (ClassNotFoundException e1) {e1.printStackTrace();}
			}
		});// Fin boton descargar

		// --al hacer clic en el botón Subir
		botonSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jFileChooser.setDialogTitle("Selecciona el Fichero a SUBIR AL SERVIDOR DE FICHEROS");
				int returnVal = jFileChooser.showDialog(jFileChooser, "Cargar");
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					String archivo = file.getAbsolutePath();
					String nombreArchivo = file.getName();
					BufferedInputStream bufferedInputStream;
					try {
						bufferedInputStream = 
								new BufferedInputStream(new FileInputStream(archivo));
						long bytes = file.length();// fichero.length();		
						System.out.println("tamaño:" + file.length());
						byte[] buffer = new byte[(int) bytes];
						int i, j = 0;

						while ((i = bufferedInputStream.read()) != -1) {							
							buffer[j] = (byte) i; //carga los datos en el array
							j++;
						}
						bufferedInputStream.close(); // cerrar stream de entrada
						Object enviarFichero = new EnviaFichero(buffer, nombreArchivo, direcSelec);
						objectOutputStream.writeObject(enviarFichero);
                        JOptionPane.showMessageDialog(null,"FICHERO CARGADO");
						
						//obtengo de nuevo la lista de ficheros
						estructuraFicherosNodo = (EstructuraFicheros) objectInputStream.readObject();
						EstructuraFicheros[] estructuraFicherosLista = estructuraFicherosNodo.getEstructuraFicheros();
						direcSelec = estructuraFicherosNodo.getPath();
						llenarLista(estructuraFicherosLista, estructuraFicherosNodo.getNumeroFicheros());
						campo2.setText("Número de ficheros en el directorio: " + estructuraFicherosLista.length);

					} catch (FileNotFoundException e1) {e1.printStackTrace();} 
					  catch (IOException ee) {ee.printStackTrace();	}					
					  catch (ClassNotFoundException e2) {e2.printStackTrace();}
				}
			}

		});// Fin boton cargar
	}// ..FIN CONSTRUCTOR
		// -----------------------------------------------------------------------------

	// REPETITIVO-------------------------------------------------------------
	public void run() {				
		try {
			cab.setText("Conectando con el servidor ........");
             // OBTENER DIRECTORIO RAIZ
			estructuraFicherosRaiz = (EstructuraFicheros) objectInputStream.readObject();			
			EstructuraFicheros[] estructuraFicherosNodos = estructuraFicherosRaiz.getEstructuraFicheros();//	 		
			// Directorio seleccionadoara saber directorio y fichero seleccionado
			direcSelec = estructuraFicherosRaiz.getPath();  
			//if(Raiz.getNumeFich()> 0)
			llenarLista(estructuraFicherosNodos,  estructuraFicherosRaiz.getNumeroFicheros());
			cab3.setText("RAIZ: " + direcSelec);
			cab.setText("CONECTADO AL SERVIDOR DE FICHEROS");			
            campo2.setText("Número de ficheros en el directorio: " + estructuraFicherosRaiz.getNumeroFicheros());
			
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.exit(1);
		}      
		
	}// fin run
		// -----------------------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void llenarLista(EstructuraFicheros[] estructuraFicheros, int numero) {
		if (numero==0) return;
		DefaultListModel defaultListModel = new DefaultListModel();
		listaDirectorios.setForeground(Color.blue);
		Font fuente = new Font("Courier", Font.PLAIN, 12);
		listaDirectorios.setFont(fuente);
		listaDirectorios.removeAll();		
		
		for (int i = 0; i < estructuraFicheros.length; i++)
			defaultListModel.addElement(estructuraFicheros[i]);
		
		try {
			listaDirectorios.setModel(defaultListModel);
		} catch (NullPointerException n) {	}

	}// Fin llenarLista

	
	// main---------------------------------------------------------------------
	public static void main(String[] args) throws IOException {
		int puerto = 44441;
		//"192.168.0.195" localhost
		Socket socket = new Socket("127.0.0.1", puerto);		
		ClienteFicheros clienteFicheros = new ClienteFicheros(socket);
		clienteFicheros.setBounds(0, 0, 540, 500);
		clienteFicheros.setVisible(true);
		new Thread(clienteFicheros).start();
	}// ..FIN main
}// .fin clase
