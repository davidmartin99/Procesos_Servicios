package Tema4_ServiciosEnRed.FTP;

import java.io.*;
import org.apache.commons.net.ftp.*;

public class SubirFichero {
	public static void main(String[] args) {
		// Se crea un cliente FTP
		FTPClient clienteFTP = new FTPClient();

		// Configuración del servidor FTP y credenciales
		String servidor = "localhost";  // Dirección del servidor FTP (en este caso, local)
		String user = "usuario1";       // Nombre de usuario para la conexión
		String pasw = "usuario1";       // Contraseña del usuario

		try {
			// Se intenta conectar con el servidor FTP
			System.out.println("Conectándose a " + servidor);
			clienteFTP.connect(servidor);

			// Se realiza el inicio de sesión con usuario y contraseña
			boolean login = clienteFTP.login(user, pasw);

			// Se configura el modo de transferencia de archivos en binario (para evitar corrupción de archivos)
			clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

			// Directorio donde se subirá el archivo en el servidor FTP
			String direc = "/NUEVODIR";

			// Se activa el modo pasivo para la conexión (necesario en muchos servidores)
			clienteFTP.enterLocalPassiveMode();

			if (login) { // Si el login es exitoso
				System.out.println("Login correcto");

				// Se intenta cambiar al directorio de trabajo en el servidor FTP
				if (!clienteFTP.changeWorkingDirectory(direc)) {
					// Si el directorio no existe, se intenta crearlo
					String directorio = "NUEVODIR";

					if (clienteFTP.makeDirectory(directorio)) {
						System.out.println("Directorio : " + directorio + " creado ...");
						// Se cambia al nuevo directorio creado
						clienteFTP.changeWorkingDirectory(directorio);
					} else {
						System.out.println("No se ha podido crear el Directorio");
						System.exit(0); // Sale del programa si falla la creación del directorio
					}
				}

				// Muestra el directorio actual en el servidor FTP
				System.out.println("Directorio actual: " + clienteFTP.printWorkingDirectory());

				// Ruta del archivo que se desea subir desde el sistema local
				String archivo = "C:\\Users\\david\\IdeaProjects\\Procesos_Servicios\\src\\Tema4_ServiciosEnRed\\FTP\\Lorem_ipsum.pdf";

				// Se abre el archivo para leerlo en un flujo de entrada
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

				// Se intenta subir el archivo al servidor FTP con el mismo nombre
				if (clienteFTP.storeFile("Lorem_ipsum.pdf", in))
					System.out.println("Subido correctamente... ");
				else
					System.out.println("No se ha podido subir el fichero... ");

				// Se cierra el flujo de entrada
				in.close();

				// Se cierra sesión en el servidor FTP
				clienteFTP.logout();

				// Se desconecta del servidor FTP
				clienteFTP.disconnect();
			}

		} catch (IOException ioe) {
			// Manejo de excepciones en caso de error
			ioe.printStackTrace();
		}
	} // Fin del main
} // Fin de la clase
