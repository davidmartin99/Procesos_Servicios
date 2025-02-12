package Tema3_ComunicacionRed.ExamenDavidMartin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;


/**
 * Clase ManejadorCliente que implementa Runnable y gestiona la comunicación entre el cliente
 */
public class ManejadorCliente implements Runnable {
    private Socket socket;
    private HashSet<String> numerosRecibidos; // Creamos el HashSet

    // Clase con socket y numerosRecibidos
    public ManejadorCliente(Socket socket, HashSet<String> numerosRecibidos) {
        this.socket = socket;
        this.numerosRecibidos = numerosRecibidos;
    }

    @Override
    public void run() {
        // Creamos los datainputstream y dataoutputstream para enviar y recibir mensajes del cliente
        try (DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            // Variable mensaje
            String mensaje;
            String ultimoNumero = null; // Variable del último número

            while (true) {
                mensaje = entrada.readUTF(); // Lee mensaje del cliente

                if ("*".equals(mensaje)) { // Verifica si se solicita desconexión
                    salida.writeUTF(ultimoNumero != null ? ultimoNumero : "No hay números disponibles");
                    System.out.println("Cliente desconectado. HashSet de números: " + numerosRecibidos);
                    break;
                }

                // Almacena el número en el HashSet y actualiza el último número
                numerosRecibidos.add(mensaje);
                ultimoNumero = mensaje;
                System.out.println("Mensaje recibido: " + mensaje);
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
