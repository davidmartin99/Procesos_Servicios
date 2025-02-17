package Tema4_ServiciosEnRed.Ejercicio4_3;

import java.io.*;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear una instancia de FTPClient
        FTPClient clienteFTP = new FTPClient();
        String servidorFTP = "127.0.0.1";  // Dirección del servidor FTP local
        int puertoFTP = 21;  // Puerto por defecto para FTP
        String usuario = "usuario1"; // Usuario configurado en el servidor FTP
        String contrasena = "usuario1"; // Contraseña del usuario

        // Pedir usuario y contraseña por teclado


        try {
            // Conexión al servidor FTP
            System.out.println("Conectándose a: " + servidorFTP);
            clienteFTP.connect(servidorFTP, puertoFTP);
            clienteFTP.enterLocalPassiveMode();  // Modo pasivo para la conexión

            // Obtener la respuesta del servidor FTP
            int respuesta = clienteFTP.getReplyCode();
            System.out.println("Respuesta del servidor: " + clienteFTP.getReplyString());

            // Comprobar si la respuesta fue exitosa (código 2xx)
            if (!FTPReply.isPositiveCompletion(respuesta)) {
                clienteFTP.disconnect();
                System.out.println("Conexión rechazada: " + respuesta);
                System.exit(0);
            }

            // Autenticación con el servidor FTP
            boolean login = clienteFTP.login(usuario, contrasena);
            if (login) {
                System.out.println("Conexión establecida con éxito.");
            } else {
                System.out.println("Error de autenticación.");
                clienteFTP.disconnect();
                System.exit(0);
            }

            // Listar los archivos en el servidor FTP
            String[] archivos = clienteFTP.listNames();
            if (archivos != null && archivos.length > 0) {
                System.out.println("Archivos disponibles en el servidor FTP:");
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }

                // Pedir al usuario el nombre del archivo a descargar
                System.out.print("Introduce el nombre del archivo a descargar: ");
                String archivoSeleccionado = scanner.nextLine();

                // Intentar descargar el archivo
                descargarArchivo(clienteFTP, archivoSeleccionado);
            } else {
                System.out.println("No se encontraron archivos en el servidor.");
            }

            // Desconectar del servidor FTP
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("Desconexión exitosa.");

        } catch (SocketException e) {
            System.out.println("Error de conexión al servidor: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            scanner.close();  // Cerrar el scanner
        }
    }

    // Método para descargar un archivo desde el servidor FTP
    private static void descargarArchivo(FTPClient clienteFTP, String archivo) {
        try (OutputStream outputStream = new FileOutputStream(archivo)) {
            // Intentar descargar el archivo y guardarlo en el disco local
            boolean exito = clienteFTP.retrieveFile(archivo, outputStream);
            if (exito) {
                System.out.println("Archivo descargado con éxito: " + archivo);
            } else {
                System.out.println("Error al descargar el archivo: " + archivo);
            }
        } catch (IOException e) {
            System.out.println("Error al descargar el archivo: " + e.getMessage());
        }
    }
}
