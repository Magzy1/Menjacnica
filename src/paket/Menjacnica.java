package paket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Menjacnica")
public class Menjacnica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	double kurs,kurs1,kurs2,kurs3;
       
    public Menjacnica() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		if(request.getParameter("iznosDin")==null)
		{
			out.println("<h2 style=\"color:red\">Nije definisan iznos u dinariam za konverziju!!!</h2>");
			return;	
		}
		kurs1= GetKursTelenorBanka.kursEvra();
		kurs2= GetKursOTPBanka.kursEvra();
		kurs3= GetKursIntesaBanka.kursEvra();
		if (kurs1>kurs2) kurs=kurs1;
		else kurs=kurs2;
		if (kurs3>kurs) kurs3=kurs;
		
		if(kurs==kurs1) out.println("Kurs po Mobi banci je: "+kurs1);
		else if(kurs==kurs2) out.println("Kurs po OTP banci je: "+kurs2);
		else if(kurs==kurs3) out.println("Kurs po Intesa banci je: "+kurs3);
		
		
		int iznosDin=Integer.parseInt(request.getParameter("iznosDin"));
		double iznosEur=iznosDin/kurs;
		out.print("<h2 style=\"color:blue\">"+iznosDin+"dinara = "
		+String.format("%,.2f", iznosEur)+"evra.</h2>");
		out.println("<hr>");
		out.println("Po definisanom kursu 1evro ="+kurs+"dinara");
	}

}
