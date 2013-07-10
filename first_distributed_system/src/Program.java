import java.util.Scanner;


public class Program 
{
	public static void main(String[] args) throws InterruptedException 
	{
		MyServer server = new MyServer();
		(new Thread(new MyClient(3,7,"add"))).start();
		(new Thread(new MyClient(8,4,"subtract"))).start();
		server.go();
	}
}
