package Tema4_ServiciosEnRed.Examen.Examen2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;

public class ClienteFTPDescarga {
    public static void main(String[] args) {
        String servidor = "127.0.0.1";
        int puerto = 21;
        String usuario = "usuario1";
        String contrasena = "1234";

        FTPClient clienteFTP = new FTPClient();

        try {
            // Conectar al servidor
            clienteFTP.connect(servidor, puerto);
            if (clienteFTP.login(usuario, contrasena)) {
                System.out.println("Conexión exitosa.");

                // Cambiar al modo pasivo y establecer tipo de archivo binario
                clienteFTP.enterLocalPassiveMode();
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                // Descargar archivo
                String archivoRemoto = "ContraseñaMercury2.txt";
                String archivoLocal = "C:\\Users\\Ruper\\Downloads\\prueba.txt";
                try (FileOutputStream fos = new FileOutputStream(archivoLocal)) {
                    boolean exito = clienteFTP.retrieveFile(archivoRemoto, fos);
                    if (exito) {
                        System.out.println("Archivo descargado correctamente.");
                    } else {
                        System.out.println("Error al descargar el archivo.");
                    }
                }
            } else {
                System.out.println("Error al iniciar sesión en el servidor FTP.");
            }
            clienteFTP.logout();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
