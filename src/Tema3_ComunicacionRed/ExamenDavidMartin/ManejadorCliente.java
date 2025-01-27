package Tema3_ComunicacionRed.ExamenDavidMartin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Clase ManejadorCliente que implementa Runnable y gestiona la comunicación entre el cliente
 */
public class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Creamos los datainputstream y dataoutputstream para enviar y recibir mensajes del cliente
        try (DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            // Variable mensaje
            String mensaje;

            while (true) {
                mensaje = entrada.readUTF(); // Lee mensaje del cliente
                System.out.println("Mensaje recibido: " + mensaje);

                if ("*".equals(mensaje)) { // Verifica si se solicita desconexión
                    System.out.println("Cliente desconectado.");
                    break;
                }

                salida.writeUTF(mensaje); // Envía la cadena procesada al cliente
            }

        } catch (IOException e) { // Manejamos las posibles excepciones
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // Cierra el socket al finalizar
            } catch (IOException e) { // Manejamos las posibles excepciones
                e.printStackTrace();
            }
        }
    }//Fin run

}//Fin ManejadorCliente
