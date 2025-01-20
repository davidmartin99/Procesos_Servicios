package Tema4_ServiciosEnRed;

// Importamos las clases necesarias de la biblioteca Apache Commons Net.
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.IOException;
import java.net.SocketException;

public class ClienteFTP1 {
    public static void main(String[] args) throws SocketException, IOException {
        // Creamos una instancia de la clase FTPClient para gestionar la conexión al servidor FTP.
        FTPClient cliente = new FTPClient();

        // Dirección del servidor FTP al que queremos conectarnos.
        String servFTP = "ftp.rediris.es"; // Servidor FTP

        // Mensaje informativo indicando que se iniciará la conexión.
        System.out.println("Nos conectamos a: " + servFTP);

        // Intentamos establecer la conexión con el servidor FTP.
        cliente.connect(servFTP);

        // Mostramos la respuesta inicial del servidor, que suele contener un mensaje de bienvenida.
        System.out.print(cliente.getReplyString());

        // Obtenemos el código de respuesta del servidor, que indica si la conexión fue exitosa.
        int respuesta = cliente.getReplyCode();
        System.out.println("Respuesta: " + respuesta);

        // Comprobamos si la respuesta del servidor es positiva.
        // FTPReply.isPositiveCompletion verifica si el código indica éxito (códigos 2xx).
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            // Si la conexión no fue exitosa, desconectamos del servidor.
            cliente.disconnect();
            System.out.println("Conexión rechazada: " + respuesta);
            // Terminamos el programa porque no tiene sentido continuar si no podemos conectarnos.
            System.exit(0);
        }

        // Si llegamos aquí, significa que la conexión fue exitosa.
        // Procedemos a desconectarnos del servidor de forma segura.
        cliente.disconnect();
        System.out.println("Conexión finalizada."); // Confirmamos que la conexión se cerró correctamente.
    }
}
