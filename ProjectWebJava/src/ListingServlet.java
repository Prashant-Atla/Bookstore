

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListingServlet
 */
@WebServlet("/ListingServlet")
public class ListingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    private int hitCount; 

    public void init() { 
       // Reset hit counter.
       hitCount = 0;
    } 
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		hitCount++;
		 String searchString = request.getParameter("listbox");
		 
		 BookHandler bookHandler = new BookHandler();
		 
	     Book[] booksList;
	     System.out.println("searchstring: " + searchString);
	     if(searchString.equalsIgnoreCase("All"))
	     {
	    booksList = bookHandler.list("");
	     }
	     else
	     {
	    booksList = bookHandler.list(searchString);
	     }
	    PrintWriter writer = response.getWriter();
	    String htmlresponse = "<html>";
htmlresponse+="<head>";
htmlresponse+="<meta charset='ISO-8859-1'>";
htmlresponse+="<title>Insert title here</title>";
htmlresponse+="</head>";
htmlresponse+="<body>";
htmlresponse+="<h2 style='align:center;'> Search for a list of books using title or author name or simply click Search for full list</h2>";
htmlresponse+="<div align='center'>";
htmlresponse+="<form>";
htmlresponse+="<a href='list.html'>Go Back</a>";
htmlresponse+="</form>";
htmlresponse+="</div>";
htmlresponse+="<div align='center'>";
htmlresponse+="<table border='1'>";
htmlresponse+="<tr><th text-align='center'>SNo.</th><th text-align='center'>Book Title</th>";
htmlresponse+="<th text-align='center'>Author</th><th text-align='center'>Price</th></tr>";
for(int i=0;i<booksList.length;i++)
{
int j=i+1;
htmlresponse+="<tr>";
htmlresponse+="<td>"+j+"</td>";
htmlresponse+="<td>"+booksList[i].getTitle()+"</td>";
htmlresponse+="<td>"+booksList[i].getAuthor()+"</td>";
htmlresponse+="<td>"+booksList[i].getPrice()+"</td>";
htmlresponse+="</tr>";
}
htmlresponse+="</table>";
htmlresponse+="</div>";
htmlresponse+="</body>";
htmlresponse+="</html>";
writer.println(htmlresponse);
 }

}
