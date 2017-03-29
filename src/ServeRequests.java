import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 * @author Seema Suresh
 * @created_on: 2/27/2017
 * Own Code - Not referred
 */

public class ServeRequests implements Runnable
{
    protected Socket clientSocket;
    protected BufferedReader br;
    protected PrintWriter pw;

    public ServeRequests(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
 //Calls to read an incoming url query for a file and call the http req and response constructors to process the http requests
    public void run() 
    {
        try 
        {
        	br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
			
			String httpReq = "";
			while(br.ready() || httpReq.length() == 0)
			{
				httpReq += (char)br.read();
			}
			System.out.println(httpReq);
			
			HttpRequest req = new HttpRequest(httpReq);
			HttpResponse res = new HttpResponse(req);
			
			pw.write(res.HttpRes.toCharArray());
			pw.close();
			br.close();
			
        }
        catch(NullPointerException e)
        {
            //System.out.print("NullPointerException Caught");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

}
