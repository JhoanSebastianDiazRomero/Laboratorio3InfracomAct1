package ServerPackage;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Servidor {

	public final static int SOCKET_PORT = 10272, size = 1500;
	public static int conectados = 0, cantidad = 0;
	public static boolean mode = true;
	public static String archivo;

	public static void main(String[] args) throws IOException {
		ServerSocket servsuck = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			/*_______________CONEXION DEL SERVIDOR____________________*/
			String result = "";
			System.out.println("Archivos disponibles para transefir a los clientes: \n "
					+ "\n Multimedia.mp4 \n NoMultimedia");
			archivo = sc.nextLine();
			System.out.println("Ingrese:\n\t[y/s/1] si desea una conexion con el cliente\n\t[n] Si desea ajustar el tamano del buffer");
			result = sc.nextLine();
			if(result.contains("y")||result.contains("s")||result.contains("1"))
				mode=false;
			if(mode) {
				System.out.println("Debido al modo de conexion \n ingrese el tamano del buffer...");
				cantidad=sc.nextInt();
				System.out.println();
			}
			System.out.println("Esperando a por conexiones en modo "+((mode)?cantidad+" ":"")+" "+((mode)?"servidor":"cliente")+"...");
			/*________________________________________________________*/

			servsuck = new ServerSocket(SOCKET_PORT);
			if (!mode)
				while (true)
					(new ThreadServidor(archivo,size, servsuck.accept())).start();
			else {
				ThreadServidor[] hilos = new ThreadServidor[cantidad];
				while (true) {
					hilos[conectados++]=new ThreadServidor(archivo, size, servsuck.accept());
					System.out.println("hay "+conectados+" conectados");
					if (conectados == cantidad) {
						for (ThreadServidor t : hilos)
							t.start();
						conectados = 0;
					}
				}
			}

		} catch (IOException e) {
			System.err.println("Could not listen on port " + SOCKET_PORT);
			System.exit(-1);
		} finally {
			if (servsuck != null)
				servsuck.close();
		}
	}
}
