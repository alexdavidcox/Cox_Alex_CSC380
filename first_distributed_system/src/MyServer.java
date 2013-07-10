import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer
{	
	static int PORT;
	ServerSocket server;
	boolean accepting;
	int hits;
	
	public MyServer()
	{
		PORT = 8000;
		hits = 0;
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void go()
	{
		while(true)
		{
			try(Socket client = server.accept())
			{
				hits++;
				Thread thread = new Thread(new MyServerThread(client, hits));
				thread.run();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
