import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class testDate {
	public static void main(String[] args) throws ParseException {
		/*Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("hh:mm");
		
		System.out.println(format.parse("04:05"));*/
		Date datee = new Date();
//		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
		 
		  String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(datee);
		  String ss= ""+datee.getTime(); 
//		  System.out.println(date);
		  System.out.println(ss);
	}
}
