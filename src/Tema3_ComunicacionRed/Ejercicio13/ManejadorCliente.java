package Tema3_ComunicacionRed.Ejercicio13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            String mensaje;
            while (true) {
                mensaje = entrada.readUTF(); // Lee mensaje del cliente
                System.out.println("Mensaje recibido: " + mensaje);

                if ("*".equals(mensaje)) { // Verifica si se solicita desconexión
                    System.out.println("Cliente desconectado.");
                    break;
                }

                String procesado = eliminarVocales(mensaje);
                salida.writeUTF(procesado); // Envía la cadena procesada al cliente
                // System.out.println("Respuesta del servidor: " + procesado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // Cierra el socket al finalizar
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String eliminarVocales(String texto) {
        return texto.replaceAll("[aeiouAEIOUáéíóúÁÉÍÓÚ]", ""); // Elimina vocales de la cadena
    }

}
