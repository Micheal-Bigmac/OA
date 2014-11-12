import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class listToArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Date());
		List list=new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		String []arg=(String[]) list.toArray(new String[list.size()]);
		for (String string : arg) {
			System.out.println(string);
		}
	
	}

}
