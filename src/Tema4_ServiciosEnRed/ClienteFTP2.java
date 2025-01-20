package Tema4_ServiciosEnRed;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.IOException;

public class ClienteFTP2 {
    public static void main(String[] args) {
        // Creamos una instancia de la clase FTPClient que manejará la conexión FTP.
        FTPClient cliente = new FTPClient();

        // Dirección del servidor FTP al que se va a conectar.
        String servFTP = "ftp.rediris.es";

        // Mensaje informativo indicando que se va a intentar conectar al servidor.
        System.out.println("Nos conectamos a: " + servFTP);

        // Nombre de usuario utilizado para el login (en este caso es "anonymous").
        String usuario = "anonymous";

        try {
            // La clave para el login será también "anonymous".
            String clave = "anonymous";

            // Intentamos conectar con el servidor FTP.
            cliente.connect(servFTP);

            // Activamos el modo pasivo, que es necesario para establecer la transferencia de datos en algunos servidores FTP.
            cliente.enterLocalPassiveMode();

            // Intentamos hacer login con el nombre de usuario y la clave proporcionados.
            boolean login = cliente.login(usuario, clave);

            // Verificamos si el login fue exitoso o no.
            if (login) {
                System.out.println("Login correcto...");
            } else {
                // Si el login falla, desconectamos y terminamos el programa.
                System.out.println("Login incorrecto...");
                cliente.disconnect();
                System.exit(1);  // Terminamos la ejecución con un código de salida 1 (error).
            }

            // Mostramos el directorio actual en el servidor FTP.
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

            // Listamos los archivos y directorios en el directorio actual.
            // La función listFiles() devuelve un arreglo de FTPFile con los detalles de los archivos/directorios.
            FTPFile[] files = cliente.listFiles();

            // Mostramos cuántos archivos y directorios están presentes en el directorio actual.
            System.out.println("Ficheros en el directorio actual: " + files.length);

            // Creamos un array de cadenas con los tipos de archivos: Fichero, Directorio, Enlace simbólico.
            String[] tipos = { "Fichero", "Directorio", "Enlace simb." };

            // Recorremos el arreglo de archivos y mostramos el nombre y el tipo de cada uno.
            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }

            // Intentamos hacer logout del servidor FTP.
            boolean logout = cliente.logout();

            // Verificamos si el logout fue exitoso.
            if (logout) {
                System.out.println("Logout del servidor FTP...");
            } else {
                // Si el logout falla, mostramos un mensaje de error.
                System.out.println("Error al hacer Logout...");
            }

            // Desconectamos del servidor FTP de forma segura.
            cliente.disconnect();
            System.out.println("Desconectado...");

        } catch (IOException ioe) {
            // Si ocurre una excepción durante el proceso, la mostramos.
            ioe.printStackTrace();
        }
    }
}

