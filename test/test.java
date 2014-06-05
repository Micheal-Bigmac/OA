import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class test {

	public void test2(){
		String path="d:/process.xml";
//		System.out.println(System.currentTimeMillis());
		String tempString=path.substring(0,path.lastIndexOf("."));
		String tempString2=path.substring(path.lastIndexOf("."));
		System.out.println(tempString);
		System.out.println(tempString2);
//		Calendar calendar=Calendar.getInstance();
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyyMMddhhmmss");
//		System.err.println(simpleDateFormat.format(new Date()));
//		System.err.println(simpleDateFormat2.format(new Date()));
		File file=new File("g:/processdefinition.xml");
		System.out.println(file.toString());
		System.out.println(file.getAbsolutePath());
	}
	public static void test(){
		List it =new ArrayList();
		System.out.println(it.size());
	}
	@Test
	public void testJson() throws JSONException{
		JSONArray array=new JSONArray();
		JSONObject object=new JSONObject();
		object.put("id", 11);
		object.put("title", "metting");
		object.put("start", System.currentTimeMillis());
		object.put("end", System.currentTimeMillis()+10000);
		object.put("backgroundColor", "#102222");
		array.put(object);
		System.err.println(array.toString());
	}
	public static void main(String[] args) {
//		test();
		System.out.println(new Date());
		List list=new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list.get(1));
		System.out.println(list.indexOf("c"));
	}
}
