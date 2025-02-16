package Tema4_ServiciosEnRed.Ejercicio4_2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.swing.*;
import java.io.*;
import java.net.SocketException;

public class SubirFicheroFTP {

    public static void main(String[] args) {
        FTPClient clienteFTP = new FTPClient();
        String servidorFTP = "127.0.0.1"; // Dirección del servidor FTP local
        int puertoFTP = 21; // Puerto FTP por defecto
        String usuario = "usuario1"; // Usuario configurado en el servidor FTP
        String contrasena = "usuario1"; // Contraseña del usuario

        // Usar JFileChooser para seleccionar un archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo a subir");

        // Mostrar el diálogo para que el usuario seleccione un archivo
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo.");
            return;
        }

        // Obtener el archivo seleccionado
        File archivoLocal = fileChooser.getSelectedFile();
        System.out.println("Archivo seleccionado: " + archivoLocal.getAbsolutePath());

        // Conectar al servidor FTP
        try {
            System.out.println("Conectando al servidor FTP...");
            clienteFTP.connect(servidorFTP, puertoFTP);
            clienteFTP.enterLocalPassiveMode();  // Modo pasivo

            // Verificar la respuesta del servidor
            int respuesta = clienteFTP.getReplyCode();
            if (!FTPReply.isPositiveCompletion(respuesta)) {
                clienteFTP.disconnect();
                System.out.println("Conexión rechazada. Código de respuesta: " + respuesta);
                return;
            }

            // Iniciar sesión con el servidor FTP
            boolean login = clienteFTP.login(usuario, contrasena);
            if (!login) {
                System.out.println("Error de autenticación.");
                clienteFTP.disconnect();
                return;
            }
            System.out.println("Conexión establecida con éxito.");

            // Subir el archivo al servidor FTP
            try (InputStream inputStream = new FileInputStream(archivoLocal)) {
                boolean exito = clienteFTP.storeFile(archivoLocal.getName(), inputStream);
                if (exito) {
                    System.out.println("Archivo subido con éxito: " + archivoLocal.getName());
                } else {
                    System.out.println("Error al subir el archivo.");
                }
            }

            // Mostrar el contenido del directorio raíz en el servidor FTP
            System.out.println("Contenido del directorio raíz:");
            String[] archivos = clienteFTP.listNames();
            if (archivos != null && archivos.length > 0) {
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            } else {
                System.out.println("No se encontraron archivos en el servidor.");
            }

            // Desconectar del servidor FTP
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("Desconexión exitosa.");

        } catch (SocketException e) {
            System.out.println("Error de conexión al servidor FTP: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
