package paket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetKursOTPBanka
{
	final static String	markerLinije = ">Evro</";
	final static String prefixKursa = "<td>";
	final static String sufixKursa = "</td>";
	
	public static double kursEvra()
	{
		URL bankURL;
		try
		{
			bankURL=new URL("https://www.otpsrbija.rs/kursna-lista/");
			URLConnection conn=bankURL.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String htmlLinija;
			while((htmlLinija=br.readLine())!=null)
			{
				if(htmlLinija.indexOf(markerLinije)>0) 
				{
					for(int n=0;n<3;n++) htmlLinija=br.readLine();
					htmlLinija=htmlLinija.substring(htmlLinija.indexOf(prefixKursa)+prefixKursa.length());
					String kurs=htmlLinija.substring(0, htmlLinija.indexOf(sufixKursa));
					kurs= kurs.replace(",",".");
					return Double.parseDouble(kurs);
				}
			}
		}catch (IOException e) {e.printStackTrace();}
		return 0.0;
	}
}
