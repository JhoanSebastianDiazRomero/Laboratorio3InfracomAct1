package FileTransferDone;


import java.net.*;
import java.io.*;
import java.util.*;


class Cliente
{
	public static void main(String args[]) throws Exception
	{
		Socket soc=new Socket("192.168.0.12",5217);
		transferfileClient t=new transferfileClient(soc);
		t.displayMenu();

	}
}

class transferfileClient
{
	Socket ClientSoc;

	InputStream din;
	OutputStream dout;
	BufferedReader br;
	FileOutputStream file;
	transferfileClient(Socket soc)
	{
		try
		{
			ClientSoc=soc;
			din = ClientSoc.getInputStream();  
			dout = ClientSoc.getOutputStream();
			br=new BufferedReader(new InputStreamReader(System.in));
			
		}
		catch(Exception ex)
		{
		}        
	}
	void FileMultimedia() throws Exception
	{        
		byte []b = new byte [10000000];
        file = new FileOutputStream("data/MULTI.MP4");
        System.out.println(din.read()); 
        din.read(b,0,b.length);
        
        
        
        file.write(b,0,b.length);
        
        System.out.println("Se transfirió el archivo Multimedia correctamente");
		
        
		


	}

	void FileNoMultimedia() throws Exception
	{
		
		byte []b = new byte [10000000];
        file = new FileOutputStream("data/NOMULTI");
        din.read(b,0,b.length);
        
        file.write(b,0,b.length);
        System.out.println("Se transfirió el archivo no multimedia correctamente");

	}

	public void displayMenu() throws Exception
	{
		while(true)
		{    
			System.out.println("[ MENU ]");
			System.out.println("1. recibir archivo multimedia");
			System.out.println("2. recibir archivo no multimedia");
			System.out.println("3. Exit");
			System.out.print("\\nEnter Choice :");
			int choice;
			choice=Integer.parseInt(br.readLine());
			if(choice==1)
			{
				dout.write(1);
			FileMultimedia();
			}
			else if(choice==2)
			{
				dout.write(2);
				FileNoMultimedia();
			}
			else
			{
				dout.write(3);
				System.exit(1);
			}
		}
	}
	
	public void hash(byte[] buffer) {
		HMAC.getDigest(buffer);
	}
}