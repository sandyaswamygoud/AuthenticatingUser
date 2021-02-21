package details;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterDetails
 */
@WebServlet("/RegisterDetails")
public class RegisterDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	Connection con;
    public RegisterDetails() {
       try {
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
    	   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","practice");
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    String u_name=request.getParameter("name");
    String u_email=request.getParameter("email");
    int u_age=Integer.parseInt(request.getParameter("age"));
    int u_phno=Integer.parseInt(request.getParameter("phno"));
    PrintWriter out =response.getWriter();
    out.println("U_Name: "+u_name);
    out.println("U_Email: "+u_email);
    out.println("U_Age: "+u_age);
    out.println("U_phno: "+u_phno);
    try {
    	PreparedStatement pst = con.prepareStatement("insert into User_Details values(?,?,?,?)");
    	pst.setString(1, u_name);
    	pst.setString(2, u_email);
    	pst.setInt(3, u_age);
    	pst.setInt(4, u_phno);
    	int num =pst.executeUpdate();
    	out.println("no of records updated are :"+num);
    	
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
    try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}x
}
