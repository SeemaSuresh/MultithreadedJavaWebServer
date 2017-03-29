/**
 * 
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.*;
	
/**
 * @author Seema Suresh
 * @created_on: 2/27/2017
 * @reference: Referred tutorials on Multi-threading in Java:http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
 * @refernce: https://www.shubhsblog.com/programming/multithreaded-webserver-java.html
 */
public class Webserver implements Runnable
{
           //Declare client port, socket, And Thread Pool
	    protected int Port = 8080;
	    protected ServerSocket Socket;
	    protected boolean ConnectionStop = false;
	    protected Thread runningThread;
	    protected ExecutorService Pool = Executors.newFixedThreadPool(10);
	    
	    //Constructor 
	    public Webserver(int port)
	    {
	        this.Port = port;
	    }

	    //Method to create synchronized thread and also print the time at which the thread was initialized to show
	    //Synchronization
	    @Override
	    public void run()
	    {
	        synchronized(this)
	        {
	            this.runningThread = Thread.currentThread(); //Referred
	        }
	        enableSocket();
	        while(!isServerStopped())
	        {
	            Socket cliSocket;
	            try 
	            {
	            	//mycode Implementation
	            	cliSocket = this.Socket.accept();
	                OutputStream output = cliSocket.getOutputStream();
			//To Show thread sync and creation/request time
	                long time = System.currentTimeMillis();
	                Date date = new Date(time); //My code
	                output.write(("HTTP/1.1 200 OK\n: Thread Server with Pool - " +	date + "").getBytes());
	                
	                System.out.println("Request processed: " + date);
	            } catch (IOException e) 
	            {
	                if(isServerStopped()) 
	                {
	                    System.out.println("Server Stopped");
	                    break;
	                }
	                throw new RuntimeException("Error in client socket connection", e);
	            }
	          //mycode Implementation
	            this.Pool.execute(
	                    new ServeRequests(cliSocket));
	            
	        }
	        this.Pool.shutdown();
	        System.out.println("Server Stopped") ;
	    }
	    
	    
	    //to shut down the server after timer elapses
	    private synchronized boolean isServerStopped() 
	    {
	        return this.ConnectionStop;
	    }

	    public synchronized void stop()
	    {
	        this.ConnectionStop = true;
	        try 
	        {
	            this.Socket.close();
	        } 
	        catch (IOException e) 
	        {
	            throw new RuntimeException("Error stopping server", e);
	        }
	    }

    //Initiliaze the port for client 
	    private void enableSocket() 
	    {//mycode Implementation
	        try
	        {
	            this.Socket = new ServerSocket(this.Port);
	        } 
	        catch (IOException e) {
	            throw new RuntimeException("Error opening port 8080", e);
	        }
	    }
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// Create a server object at given port and hit start
		Webserver server = new Webserver(8000);
		new Thread(server).start();
		//mycode Implementation
		try 
		{
		    Thread.sleep(200 * 1000);
		} 
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
		server.stop();
	}
}
