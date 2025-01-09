package Tema3_ComunicacionRed.SocketsUDP.UDP.Ejercicio11;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClienteAlumno {

    public static void main(String[] args) {
        try {
            // Crear socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Dirección y puerto del servidor
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 12345;

            // Scanner para lectura de teclado
            Scanner scanner = new Scanner(System.in);
            String idAlumno;

            System.out.println("Cliente UDP iniciado. Introduce '*' para salir.");

            while (true) {
                // Leer el idAlumno desde el teclado
                System.out.print("Introduce el ID del alumno a consultar: ");
                idAlumno = scanner.nextLine().trim();

                // Salir del bucle si se introduce '*'
                if (idAlumno.equals("*")) {
                    System.out.println("Finalizando cliente...");
                    break;
                }

                // Enviar el idAlumno al servidor
                byte[] bufferEnviar = idAlumno.getBytes();
                DatagramPacket paqueteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, direccionServidor, puertoServidor);
                socket.send(paqueteEnviar);

                // Recibir el objeto Alumno del servidor
                byte[] bufferRecibir = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibir, bufferRecibir.length);
                socket.receive(paqueteRecibido);

                // Deserializar el objeto Alumno recibido
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Alumno alumno = (Alumno) objectInputStream.readObject();

                // Mostrar los datos del alumno recibido
                if (alumno != null && alumno.getIdAlumno() != null) {
                    System.out.println("Datos del alumno recibido:");
                    System.out.println("ID: " + alumno.getIdAlumno());
                    System.out.println("Nombre: " + alumno.getNombre());
                    System.out.println("Curso: " + (alumno.getCurso() != null ? alumno.getCurso().getDescripcion() : "Sin curso"));
                    System.out.println("Nota: " + alumno.getNota());
                } else {
                    System.out.println("Alumno no encontrado.");
                }
            }

            // Cerrar recursos
            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
