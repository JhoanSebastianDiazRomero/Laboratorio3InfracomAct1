package FileTransferDone;


import java.net.*;
import java.io.*;
import java.util.*;

public class Servidor 
{
	public static void main(String args[]) throws Exception
	{
		ServerSocket soc=new ServerSocket(5217);
		System.out.println("Servidor conectadoe en el puerto 5217");
		while(true)
		{
			System.out.println("Esperando cliente");
			transferfile t=new transferfile(soc.accept());

		}
	}
}

class transferfile extends Thread
{
	Socket ClientSoc;

	InputStream din;
	OutputStream dout;
	FileInputStream file;
	
	transferfile(Socket soc)
	{
		try
		{
			ClientSoc=soc;                        
			din=new DataInputStream(ClientSoc.getInputStream());
			dout=new DataOutputStream(ClientSoc.getOutputStream());
			System.out.println("Cliente conectado ...");
			start();

		}
		catch(Exception ex)
		{
		}        
	}
	void EnviarArchivoMultimedia() throws Exception
	{        
		file = new FileInputStream("data/MULTI.mp4");
		System.out.print("Empezando proceso para enviar archivo multimedia wtf \n");
		
      byte b [] = new byte [10000000];
      file.read(b,0,b.length);
      dout = ClientSoc.getOutputStream();
      System.out.println("Envíando archivo multimedia \n");
      dout.write(b,0,b.length);
      
      
      
      
      
      System.out.println("Archivo Multimedia envíado");
		
		

		
	}

	void EnviarArchivoNoMultimedia() throws Exception
	{
		file = new FileInputStream("data/NOMULTI");
		System.out.print("Empezando proceso para enviar archivo  no multimedia \n");
		
      byte b [] = new byte [10000000];
      file.read(b,0,b.length);
      dout = ClientSoc.getOutputStream();
      System.out.println("Envíando archivo no multimedia \n");
      dout.write(b,0,b.length);
      System.out.println("Archvo no Multimedia envíado \n");
	}


	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Esperando comando del cliente");
				int Command=din.read();
				if(Command == 1)
				{
					System.out.println("\\Se va a enviar el archivo multimedia ...");
					EnviarArchivoMultimedia();
					System.out.println("Se envió esa gonorrea ");
					continue;
				}
				else if(Command == 2)
				{
					System.out.println("\\Se va a enviar el archivo no multimedia ...");                
					EnviarArchivoNoMultimedia();
					continue;
				}
				else if(Command == 3)
				{
					System.out.println("\\Se va a terminar la sesión con el cliente actual ...");
					System.exit(1);
				}
			}
			catch(Exception ex)
			{
			}
		}
	}
}