package Tema5_ProgramacionSegura.FicherosPoliticas;


public class Ejemplo1 {
	public static void main(String[] args) {	
		String t[] = { "java.class.path", "java.home", "java.vendor",
				 "java.version", "os.name", "os.version",
				"user.dir", "user.home", "user.name" };

		for (int i = 0; i < t.length; i++) {
			System.out.print("Propiedad:" + t[i]);
			try {
				String s = System.getProperty(t[i]);
				System.out.println("\t==> " + s);
			} catch (Exception e) {
				System.err.println("\n\tExcepci�n " + e.toString());
			}
		}// for
	}
}//..Ejemplo1
//java -Djava.security.policy=Politica1.policy Ejemplo1