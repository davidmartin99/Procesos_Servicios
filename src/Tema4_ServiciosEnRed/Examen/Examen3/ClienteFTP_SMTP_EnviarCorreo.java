package Tema4_ServiciosEnRed.Examen.Examen3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class ClienteFTP_SMTP_EnviarCorreo {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);

        String servidorFTP = "localhost";
        int puertoFTP = 21;
        String usuarioFTP = "usuario1";
        String contrasenaFTP = "usuario1";

        FTPClient clienteFTP = new FTPClient();

        System.out.println("Iniciando aplicación...");
        try {
            // Conexión al servidor FTP
            clienteFTP.connect(servidorFTP, puertoFTP);
            if (clienteFTP.login(usuarioFTP, contrasenaFTP)) {
                System.out.println("Conexión al servidor FTP exitosa.");
                clienteFTP.enterLocalPassiveMode();
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                String archivoAdjunto = null;

                while (true) {
                    System.out.println("\nOpciones:");
                    System.out.println("1. Listar archivos y directorios en el servidor FTP");
                    System.out.println("2. Subir archivo al servidor FTP");
                    System.out.println("3. Descargar archivo del servidor FTP");
                    System.out.println("4. Enviar correo (con o sin archivo adjunto)");
                    System.out.println("5. Salir");
                    System.out.print("Selecciona una opción: ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir salto de línea

                    switch (opcion) {
                        case 1:
                            listarArchivos(clienteFTP);
                            break;
                        case 2:
                            System.out.print("Introduce la ruta del archivo a subir: ");
                            String rutaSubir = scanner.nextLine();
                            subirArchivo(clienteFTP, rutaSubir);
                            archivoAdjunto = rutaSubir;
                            break;
                        case 3:
                            System.out.print("Introduce el nombre del archivo a descargar: ");
                            String archivoDescargar = scanner.nextLine();
                            System.out.print("Introduce la ruta local para guardar el archivo: ");
                            String rutaDescargar = scanner.nextLine();
                            descargarArchivo(clienteFTP, archivoDescargar, rutaDescargar);
                            archivoAdjunto = rutaDescargar;
                            break;
                        case 4:
                            enviarCorreo(scanner, archivoAdjunto);
                            break;
                        case 5:
                            System.out.println("Cerrando aplicación...");
                            clienteFTP.logout();
                            clienteFTP.disconnect();
                            return;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Error al iniciar sesión en el servidor FTP.");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void listarArchivos(FTPClient clienteFTP) throws IOException {
        System.out.println("Archivos y directorios disponibles:");
        for (String nombre : clienteFTP.listNames()) {
            System.out.println(" - " + nombre);
        }
    }

    private static void subirArchivo(FTPClient clienteFTP, String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            try (FileInputStream fis = new FileInputStream(archivo)) {
                boolean exito = clienteFTP.storeFile(archivo.getName(), fis);
                if (exito) {
                    System.out.println("Archivo subido correctamente.");
                } else {
                    System.out.println("Error al subir el archivo.");
                }
            }
        } else {
            System.out.println("El archivo no existe en la ruta especificada.");
        }
    }

    private static void descargarArchivo(FTPClient clienteFTP, String archivoRemoto, String rutaLocal) throws IOException {
        File destino = new File(rutaLocal);

        // Si la ruta local es un directorio, añade el nombre del archivo remoto
        if (destino.isDirectory()) {
            destino = new File(destino, archivoRemoto);
        }

        try (FileOutputStream fos = new FileOutputStream(destino)) {
            boolean exito = clienteFTP.retrieveFile(archivoRemoto, fos);
            if (exito) {
                System.out.println("Archivo descargado correctamente en: " + destino.getAbsolutePath());
            } else {
                System.out.println("Error al descargar el archivo.");
            }
        }
    }

    private static void enviarCorreo(Scanner scanner, String archivoAdjunto) throws NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, InvalidKeySpecException, InvalidKeyException {
        AuthenticatingSMTPClient clienteSMTP = new AuthenticatingSMTPClient("TLS");

        String servidorSMTP = "localhost";
        int puertoSMTP = 25;
        String remitente = "postmaster@localhost";


        System.out.print("Introduce el destinatario del correo: ");
        String destinatario = scanner.nextLine();
        System.out.print("Introduce el asunto del correo: ");
        String asunto = scanner.nextLine();
        System.out.print("Introduce el mensaje del correo: ");
        String mensajeTexto = scanner.nextLine();

        try {
            clienteSMTP.connect(servidorSMTP, puertoSMTP);
            System.out.println("Conectado al servidor SMTP: " + clienteSMTP.getReplyString());

            clienteSMTP.ehlo("localhost");
            System.out.println("Respuesta EHLO: " + clienteSMTP.getReplyString());

            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);
            clienteSMTP.setSender(remitente);
            clienteSMTP.addRecipient(destinatario);

            Writer writer = clienteSMTP.sendMessageData();
            if (writer != null) {
                writer.write(cabecera.toString());
                writer.write("\n" + mensajeTexto);
                if (archivoAdjunto != null) {
                    writer.write("\n\nArchivo adjunto: " + archivoAdjunto);
                }
                writer.close();
                if (clienteSMTP.completePendingCommand()) {
                    System.out.println("Correo enviado con éxito.");
                } else {
                    System.err.println("Error al enviar el correo.");
                }
            }

            clienteSMTP.logout();
            clienteSMTP.disconnect();
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor SMTP.");
            e.printStackTrace();
        }
    }
}
