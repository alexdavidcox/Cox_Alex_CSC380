import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class MyClient implements Runnable
{
	int a;
	int b;
	String command;
	
	

	public MyClient(int a, int b, String command) 
	{
		this.a = a;
		this.b = b;
		this.command = command;
	}


	@Override
	public void run() 
	{
		try(Socket connection = new Socket("localhost", MyServer.PORT))
		{
			BufferedReader read = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			PrintWriter write = new PrintWriter(connection.getOutputStream(), true);
			
			//write protocol
			write.println(a);
			write.println(b);
			write.println(command);
			
			String result = read.readLine();
			System.out.println("Result: "+result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
