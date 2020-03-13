package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import Logger.LoggerInfo;
import Servicios.CheckSum;
import Servicios.Tempo;
import Logger.Logger;

public class Cliente {

	/**
	 * Puerto de la conexión
	 */
	public final static int puerto = 10272;
	/**
	 * Dirección IP del servidor y carpeta donde se va a guardar el arhivo transferido al cliente
	 */
	public static final String direccion = "localhost", RUTA = "./dataCliente/";

	/**
	 * Espacio para el archivo
	 */
	public final static int tamanio = (int) (Math.pow(2, 20) * 251); 

	public static void main(String[] args) throws Exception {
		int bytesRead, current = 0;
		Tempo timer = new Tempo();
		FileOutputStream fileOut = null;
		OutputStream outs = null;
		PrintWriter printW = null;
		BufferedReader bufferedR=null;
		Socket socket = null;
		String hash = new String();
		Scanner sc = new Scanner(System.in);

		try {
			

			
			socket = new Socket(direccion, puerto);
			System.out.println("Conectando al servidor");
			System.out.println("Se ha conectado correctamente al servidor");
			bufferedR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outs =  socket.getOutputStream(); 





			hash = bufferedR.readLine();

			socket.getOutputStream().flush();
			timer.start();
			InputStream is = socket.getInputStream(); // recepción del socket
			byte mybytearray[] = new byte[tamanio]; // representación byte a byte del archivo
			fileOut = new FileOutputStream(RUTA + "SocketPort" + socket.getLocalPort() + ".mp4"); // Stream de envio de archivos
			outs = new BufferedOutputStream(fileOut); // Stream de escritura del cliente
			bytesRead = is.read(mybytearray, 0, mybytearray.length);
			current = bytesRead;
			int div =bytesRead;

			do {
				bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
				if (bytesRead >= 0)
					current += bytesRead;
			} while (bytesRead > -1);
			outs.write(mybytearray, 0, current);

			outs.flush();


			int size =(int) (new File(RUTA + "fileof" + socket.getLocalPort() + ".mp4").length()), paqs =(int) size/div +1;

			socket.getOutputStream().flush();
			boolean correcto = hash.equals(CheckSum.checksum(RUTA + "SocketPort" + socket.getLocalPort() + ".mp4"));
			System.out.println("El archivo fue recibido "+((correcto)?"correctamente":"incorrectamente"));
			System.out.println("El archivo fue descargado (" + current + " bytes leidos)");
			Logger.loggear(new LoggerInfo("socket "+socket.getLocalPort(), timer.stop()+"", paqs, paqs, size, size),
					RUTA + "fileof" + socket.getLocalPort() + ".mp4",
					size);
		} 
		//Cerrar todos los canales
		finally {
			if (fileOut != null)
				fileOut.close();
			if (outs != null)
				outs.close();
			if (socket != null)
				socket.close();
			if (printW != null)
				printW.close();
			if (bufferedR != null)
				bufferedR.close();
		}
	}
}
