package Tema4_ServiciosEnRed.ServidorArchivos;

import java.io.*;
import java.net.*;


class HiloServidor extends Thread {
	Socket socket;	
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	EstructuraFicheros estructuraFicheros;

	public HiloServidor(Socket socket, EstructuraFicheros estructuraFicheros) throws IOException {
		this.socket = socket;
		this.estructuraFicheros = estructuraFicheros;
		this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
		this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
	}

	//
	public void run() {
		try {
			// envio al cliente el objeto EstructuraFicheros 
			objectOutputStream.writeObject(estructuraFicheros);

			while (true) {
				// primero leo lo que me pide cliente
				Object peticion = objectInputStream.readObject();

				if (peticion instanceof PideFichero) {
					PideFichero pideFichero = (PideFichero) peticion;
					// escribo en el stream
					enviarFichero(pideFichero);
				}
				
				if (peticion instanceof EnviaFichero) {
					EnviaFichero enviaFichero = (EnviaFichero) peticion;
					System.out.println(enviaFichero.getNombre() + "*" + enviaFichero.getDirectorio());
					
					 File directorio = new File(enviaFichero.getDirectorio()); 
				     File fichero1 = new File(directorio,enviaFichero.getNombre());	//creo fichero en el directorio
					   
					FileOutputStream fileOutputStream = new FileOutputStream(fichero1);
					
					//System.out.println(enviaFichero.getNombre() + "*" + enviaFichero.getDirectorio());
					
					fileOutputStream.write(enviaFichero.getContenidoFichero());
					fileOutputStream.close();
					EstructuraFicheros estructuraFicheros = new EstructuraFicheros(enviaFichero.getDirectorio());
					objectOutputStream.writeObject(estructuraFicheros);					
				}
			}
		} catch (IOException e) {
			// cuando un cliente Cierra la conexion		
			try {
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
				System.out.println("Cerrando cliente");
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // de run()
    
	private void enviarFichero(PideFichero pideFichero) {
		File file = new File(pideFichero.getNombreFichero()); 
		// crea flujo de entrada
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			long bytes = file.length();
			//System.out.println("fichero:" + pideFichero.getNombreFichero());
			byte[] buffer = new byte[(int) bytes];
			int i, j = 0;
			
			while ((i = fileInputStream.read()) != -1) {// lee datos del flujo de entrada
				//System.out.println(i);
				buffer[j] = (byte) i;
				j++;
			}
			fileInputStream.close(); // cerrar stream de entrada
			Object obtieneFichero = new ObtieneFichero(buffer);
			objectOutputStream.writeObject(obtieneFichero);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// ..EnviarFichero
}// ..HiloServidor
