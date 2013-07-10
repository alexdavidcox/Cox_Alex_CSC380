import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MyServerThread implements Runnable
{
	Socket client;
	int clientID;
	MathLogic math;
	
	public MyServerThread(Socket client, int clientID) 
	{
		this.client = client;
		this.clientID = clientID;
		math = new MathLogic();
	}

	@Override
	public void run() 
	{
		try 
		{
			InputStream in = client.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			System.out.println("LOG: Client"+clientID+" has now connected.");
			
			//read protocol
			int a = Integer.parseInt(read.readLine());
			int b = Integer.parseInt(read.readLine());
			String command = read.readLine();
			
			if(command.equals("add"))
			{
				System.out.println("LOG: Client"+clientID+" wishes to "+command+" "+a+" and "+b);
				out.println(math.add(a, b));
			}
			else if(command.equals("subtract"))
			{
				System.out.println("LOG: Client"+clientID+" wishes to "+command+" "+a+" from "+b);
				out.println(math.subtract(a, b));
			}
			else
			{
				System.out.println("LOG: Client"+clientID+" gave a bad command.");
				out.println("bad command");
			}
			System.out.println("LOG: Client"+clientID+"'s session is terminated.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
