package Tema4_ServiciosEnRed.ServidorArchivos;

import java.io.*;

public class EstructuraFicheros implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // nombre
	private String path; // nombre completo
	private boolean isDir;// es un directorio

	private EstructuraFicheros[] estructuraFicheros; // lista de ficheros y carpetas
	private int numeroFicheros; // numero de fich de la carpeta

	// constructores
	public EstructuraFicheros(String name) throws FileNotFoundException {
		// Construtor por defecto que se usa en el servidor
		// name: Nombre completo (directorio+nombre) del fichero/directorio
		// en el servidor local
		File file = new File(name);
		this.name = file.getName();
		this.path = file.getPath();
		this.isDir = file.isDirectory();

		this.estructuraFicheros = getListaFiles();
		if (file.isDirectory()) {
			File[] ficheros = file.listFiles();
			this.numeroFicheros = 0;
			if (!(ficheros == null))
				this.numeroFicheros = ficheros.length;
		}
	}

	public EstructuraFicheros(String name, String path, boolean isDir, int numeroFicheros) {
		// Este constructor se usa en las operaciones
		// de actualizacion del arbol presentado en el cliente
		// No es obligatorio implementar este metodo
		this.name = name;
		this.path = path;
		this.isDir = isDir;
		this.numeroFicheros = numeroFicheros; // num ficheros si es directorio
	}

	public int getNumeroFicheros() {
		return numeroFicheros;
	}

	public EstructuraFicheros[] getEstructuraFicheros() {
		return estructuraFicheros;
	}

	public String toString() {
		String nom = this.name;
		if (this.isDir)
			nom = "(DIR) " + name;
		return nom;
	}

	public boolean isDir() {
		return isDir;
	}

	public String getName() {
		String name_dir = name;
		if (isDir) {
			// En el caso de un fichero solo se presenta el nombre
			// del mismo usando el metodo getName() asociado a la clase File.
			// En el caso de un directorio getName() devuelve el nombre completo
			// es decir el path absoluto y solo se necesita el nombre de la
			// carpeta
			// ya que el atributo path almacena dicha informaci�n.
			int l = path.lastIndexOf(File.separator);
			name_dir = path.substring(l + 1, path.length());
		}
		return name_dir;
	}

	public String getPath() {
		return path;
	}

	// Método interno para llenar el array con los ficheros y directorios del
	// directorio seleccionado.
	// Cada elemento del array es de tipo EstructuraFicheros
	EstructuraFicheros[] getListaFiles() {
		EstructuraFicheros[] estructuraFicheros = null;
		String sDirectorio = this.path;
		File file = new File(sDirectorio);
		File[] files = file.listFiles();
		int longitud = files.length;
		if (longitud > 0) {// por si se selecciona carpeta vacia
			// es un directorio, creo un nuevo nodo
			estructuraFicheros = new EstructuraFicheros[longitud]; // array con todos los ficheros
			for (int i = 0; i < files.length; i++) {
				EstructuraFicheros estructFicheros;
				String nombre = files[i].getName();
				String path = files[i].getPath();
				boolean isDir = files[i].isDirectory();
				int num = 0;
				// String parent = files[i].getParent();
				// si alguno de los ficheros del directorio seleccionado es
				// a su vez un directorio calculo el numero de ficheros
				if (isDir) {
					File[] fic = files[i].listFiles();
					if (!(fic == null))
						num = fic.length;
				}
				estructFicheros = new EstructuraFicheros(nombre, path, isDir, num);
				estructuraFicheros[i] = estructFicheros;
			} // for
		}
		return estructuraFicheros;
	}
}// ..EstructuraFicheros
