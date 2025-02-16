package Tema4_ServiciosEnRed.Examen.Examen3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class ClienteFTP3 {

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            // Conectar al servidor FTP
            ftpClient.connect("127.0.0.1");
            ftpClient.login("usuario1", "usuario1");

            // Confirmar la conexión
            if (ftpClient.isConnected()) {
                System.out.println("Conexión al servidor FTP exitosa.");

                // Aquí puedes modificar la lógica para interactuar con las opciones del menú
                // Por ejemplo, la opción para eliminar un archivo
                String archivoAEliminar = "NUEVODIR/hola.txt"; // Ruta completa del archivo

                // Eliminar el archivo
                boolean eliminado = ftpClient.deleteFile(archivoAEliminar);

                if (eliminado) {
                    System.out.println("Archivo eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el archivo. Verifique el nombre o los permisos.");
                }
            }

            // Cerrar la conexión
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
