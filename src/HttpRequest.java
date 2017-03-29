/**
 * @author Seema Suresh
 * @created_on: 2/27/2017
 *  * Own Code -  Help Forum - http://www.tutorialspoint.com/java/io/java_io_outputstream.htm
 */
public class HttpRequest 
{
	protected String file;
	
	public HttpRequest(String httpReq)
	{
		// TODO Auto-generated constructor stub
		String newLine[] = httpReq.split("\n"); 
		System.out.println("HTTP Request GET: \n" + newLine[0]);
		file = newLine[0].split(" ")[1];
		
	}
}
