package Tema3_ComunicacionRed.Apartado_3_6.MulticastSockets.ejercicio7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class E07_MulticastCliente {
    private static final String MULTICAST_IP = "225.0.0.1";
    private static final int PUERTO = 6000;
    private static MulticastSocket socket;
    private static InetAddress grupo;

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre:");

        JFrame frame = new JFrame("Cliente Multicast - " + nombre);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createTitledBorder("Mensajes recibidos"));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton salirButton = new JButton("Salir");

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(salirButton, BorderLayout.SOUTH);

        frame.setVisible(true);

        try {
            socket = new MulticastSocket(PUERTO);
            grupo = InetAddress.getByName(MULTICAST_IP);
            socket.joinGroup(grupo);

            Thread escucha = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);

                        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                        textArea.append("Mensaje recibido: " + mensaje + "\n");
                    }
                } catch (IOException ex) {
                    textArea.append("Error al recibir mensajes: " + ex.getMessage() + "\n");
                }
            });
            escucha.start();

            salirButton.addActionListener(e -> {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.leaveGroup(grupo);
                        socket.close();
                    }
                } catch (IOException ex) {
                    textArea.append("Error al salir del grupo: " + ex.getMessage() + "\n");
                }
                frame.dispose();
                System.exit(0);
            });

        } catch (IOException ex) {
            textArea.append("Error al iniciar el cliente: " + ex.getMessage() + "\n");
        }
    }
}
