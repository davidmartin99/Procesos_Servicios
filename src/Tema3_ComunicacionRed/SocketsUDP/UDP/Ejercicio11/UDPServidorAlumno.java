package Tema3_ComunicacionRed.SocketsUDP.UDP.Ejercicio11;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class UDPServidorAlumno {

    public static void main(String[] args) {
        try {
            // Crear socket UDP en el puerto 12345
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado...");

            // Inicializar un array de 5 objetos Alumno
            Curso curso1 = new Curso("01", "Matemáticas");
            Curso curso2 = new Curso("02", "Física");
            Curso curso3 = new Curso("03", "Historia");
            Curso curso4 = new Curso("04", "Programación");
            Curso curso5 = new Curso("05", "Biología");

            Alumno[] alumnos = {
                    new Alumno("01", "Juan", curso1, 85),
                    new Alumno("02", "María", curso2, 90),
                    new Alumno("03", "Luis", curso3, 78),
                    new Alumno("04", "Ana", curso4, 88),
                    new Alumno("05", "Pedro", curso5, 95)
            };

            while (true) {
                // Recibir solicitud del cliente
                byte[] bufferRecibir = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibir, bufferRecibir.length);
                socket.receive(paqueteRecibido);

                // Convertir el idAlumno recibido a String
                String idAlumnoSolicitado = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("ID Alumno solicitado: " + idAlumnoSolicitado);

                // Buscar el alumno con el id solicitado
                Alumno alumnoEncontrado = null;
                for (Alumno alumno : alumnos) {
                    if (alumno.getIdAlumno().equals(idAlumnoSolicitado)) {
                        alumnoEncontrado = alumno;
                        break;
                    }
                }

                // Serializar el objeto Alumno y enviarlo al cliente
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(alumnoEncontrado);
                objectOutputStream.flush();
                byte[] bufferEnviar = byteArrayOutputStream.toByteArray();

                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                DatagramPacket paqueteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnviar);

                System.out.println("Enviado: " + (alumnoEncontrado != null ? alumnoEncontrado : "Alumno no encontrado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
