package Tema4_ServiciosEnRed.Examen.Examen2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ClienteFTP {
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

                // Subir archivo
                String archivoLocal = "C:\\Users\\Ruper\\Downloads\\ContraseñaMercury2.txt";
                File archivo = new File(archivoLocal);
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    boolean exito = clienteFTP.storeFile(archivo.getName(), fis);
                    if (exito) {
                        System.out.println("Archivo subido correctamente.");
                    } else {
                        System.out.println("Error al subir el archivo.");
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