package Tema4_ServiciosEnRed.Examen.Examen3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClienteFTP2 {
    public static void main(String[] args) {
        String servidor = "127.0.0.1"; // Dirección del servidor FTP
        int puerto = 21; // Puerto del servidor FTP
        String usuario = "usuario1"; // Usuario configurado en el servidor FTP
        String contrasena = "usuario1"; // Contraseña del usuario

        FTPClient clienteFTP = new FTPClient();

        try {
            // Conexión al servidor
            clienteFTP.connect(servidor, puerto);
            if (clienteFTP.login(usuario, contrasena)) {
                System.out.println("Conexión al servidor FTP exitosa.");

                // Modo pasivo y tipo de archivo binario
                clienteFTP.enterLocalPassiveMode();
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("\nOpciones:");
                    System.out.println("1. Listar archivos y directorios");
                    System.out.println("2. Subir archivo");
                    System.out.println("3. Descargar archivo");
                    System.out.println("4. Eliminar archivo");
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
                            break;
                        case 3:
                            System.out.print("Introduce el nombre del archivo a descargar: ");
                            String archivoDescargar = scanner.nextLine();
                            System.out.print("Introduce la ruta local para guardar el archivo: ");
                            String rutaDescargar = scanner.nextLine();
                            descargarArchivo(clienteFTP, archivoDescargar, rutaDescargar);
                            break;
                        case 4:
                            System.out.print("Introduce el nombre del archivo a eliminar: ");
                            String archivoEliminar = scanner.nextLine();
                            eliminarArchivo(clienteFTP, archivoEliminar);
                            break;
                        case 5:
                            System.out.println("Cerrando conexión...");
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
        String[] archivos = clienteFTP.listNames();
        if (archivos != null) {
            for (String nombre : archivos) {
                System.out.println(" - " + nombre);
            }
        } else {
            System.out.println("No se pudieron listar los archivos.");
        }
    }


    private static void subirArchivo(FTPClient clienteFTP, String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            try (FileInputStream fis = new FileInputStream(archivo)) {
                // Verificar que la conexión FTP esté activa
                if (!clienteFTP.isConnected()) {
                    clienteFTP.connect("127.0.0.1", 21); // Conectar nuevamente si la conexión está cerrada
                }

                // Intentar cambiar al directorio NUEVODIR en el servidor FTP
                boolean directorioCambio = clienteFTP.changeWorkingDirectory("NUEVODIR");
                if (!directorioCambio) {
                    // Si el directorio no existe, lo creamos
                    System.out.println("Directorio NUEVODIR no encontrado, creando...");
                    boolean directorioCreado = clienteFTP.makeDirectory("NUEVODIR");
                    if (directorioCreado) {
                        System.out.println("Directorio NUEVODIR creado correctamente.");
                    } else {
                        System.out.println("Error al crear el directorio NUEVODIR.");
                    }
                }

                // Intentamos subir el archivo
                boolean exito = clienteFTP.storeFile(archivo.getName(), fis);
                if (exito) {
                    System.out.println("Archivo subido correctamente.");
                } else {
                    System.out.println("Error al subir el archivo.");
                }
            } catch (IOException e) {
                System.out.println("Error: IOException caught while copying.");
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo no existe en la ruta especificada.");
        }
    }


    private static void descargarArchivo(FTPClient clienteFTP, String archivoRemoto, String rutaLocal) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(rutaLocal)) {
            boolean exito = clienteFTP.retrieveFile(archivoRemoto, fos);
            if (exito) {
                System.out.println("Archivo descargado correctamente.");
            } else {
                System.out.println("Error al descargar el archivo.");
            }
        }
    }

    private static void eliminarArchivo(FTPClient clienteFTP, String archivoRemoto) throws IOException {
        // Listar los archivos antes de intentar eliminar para verificar que el archivo existe
        System.out.println("Archivos disponibles antes de eliminar:");
        String[] archivos = clienteFTP.listNames();
        if (archivos != null) {
            for (String nombre : archivos) {
                System.out.println(" - " + nombre);
            }
        }

        // Verificar si el archivo existe en el servidor FTP
        boolean existe = false;
        for (String nombre : archivos) {
            if (nombre.equals(archivoRemoto)) {
                existe = true;
                break;
            }
        }

        if (existe) {
            // Intentar eliminar el archivo
            try {
                boolean exito = clienteFTP.deleteFile(archivoRemoto);
                if (exito) {
                    System.out.println("Archivo eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el archivo: El archivo no se pudo eliminar.");
                }
            } catch (IOException e) {
                System.out.println("Error al eliminar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe en el servidor FTP.");
        }
    }

}
