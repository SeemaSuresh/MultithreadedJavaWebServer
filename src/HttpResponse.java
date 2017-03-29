import java.io.File;
import java.io.FileInputStream;


/**
 * @author Seema Suresh
 * @created_on: 2/27/2017
 * @Own Code - StackOverFlow Help
 */
public class HttpResponse 
{
	protected HttpRequest httpReq;
	protected String HttpRes;
	//Uncomment the below line and comment the line following it inorder to give absoulte file paths
	//protected String path = "";
	protected String path = "WebPages/";

	public HttpResponse(HttpRequest httpReq)
	{
		// TODO Auto-generated constructor stub
		this.httpReq = httpReq;
		path += httpReq.file;
		File f = new File(path);
		f  = f.getAbsoluteFile();
		//System.out.println(f.toString());
		try
		{
			FileInputStream fis = new FileInputStream(f);	
			//on successful finding of file - post the http header details of the server request. If the file not found this execution skips for 404 error
			HttpRes = "HTTP/1.1 200 \r\n Server: Create Server/1.0 \r\n Content-Type: text/html \r\n Content-Length: "
			          + f.length() + " \r\n"; 	
			System.out.println("Http Response POST = " + HttpRes);
			fis.close();
		}
		catch(NullPointerException e)
        {
            
        }
		catch(Exception e)
		{
			HttpRes = HttpRes.replace("200", "500");		
		}
	}
}
