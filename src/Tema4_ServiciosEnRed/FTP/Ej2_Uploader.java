package Tema4_ServiciosEnRed.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.io.*;

public class Ej2_Uploader {
    public static void main(String[] args) {
        // Datos de conexión al servidor FTP local (FileZilla en XAMPP)
        String server = "127.0.0.1";
        int port = 14147; // Puerto correcto de FileZilla Server
        String user = "usuario1"; // Cambia por tu usuario FTP
        String pass = "usuario1"; // Cambia por tu contraseña FTP

        FTPClient ftpClient = new FTPClient();

        try {
            System.out.println("Conectando a FTP " + server + ":" + port + "...");
            ftpClient.connect(server, port);

            int replyCode = ftpClient.getReplyCode();
            if (!ftpClient.login(user, pass)) {
                System.out.println("Error: No se pudo autenticar en el servidor FTP.");
                return;
            }

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Conexión establecida correctamente.");

            // Seleccionar archivo con JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();

                try (InputStream inputStream = new FileInputStream(selectedFile)) {
                    System.out.println("Subiendo archivo: " + fileName + "...");
                    boolean done = ftpClient.storeFile(fileName, inputStream);

                    if (done) {
                        System.out.println(" Archivo subido correctamente.");
                    } else {
                        System.out.println(" Error al subir el archivo.");
                    }
                }
            } else {
                System.out.println("Operación cancelada por el usuario.");
            }

            // Listar archivos en el directorio raíz
            System.out.println("\n📂 Contenido del directorio raíz:");
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                System.out.println(" - " + file.getName());
            }

        } catch (IOException ex) {
            System.err.println(" Error en la conexión FTP: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                    System.out.println("🔌 Conexión FTP cerrada.");
                }
            } catch (IOException ex) {
                System.err.println("Error al cerrar la conexión FTP.");
                ex.printStackTrace();
            }
        }
    }
}
