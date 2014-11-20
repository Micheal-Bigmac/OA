package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.omg.CORBA.PRIVATE_MEMBER;

//将内存中的一个对象持久化到一个文件中
public class Persistence23 {

	private static Map variables = null;
	private static String saveFileName = System.getProperty("java.io.tmpdir")+"/temp.object";

	static {
		if (new File(saveFileName).exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(saveFileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				variables = (Map) objectInputStream.readObject();
				objectInputStream.close();
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (variables == null) {
				variables = new HashMap();
			}
		}
	}

	public static String setVariable(Serializable value) {
		String randomNameString = new Random().nextLong() + "";
		if (variables != null) {
			variables.put(randomNameString, value);
		}
		
		// saveToFile();
		return randomNameString;
	}

	private static void saveToFile() {
		try {
			FileOutputStream outputStream = new FileOutputStream(saveFileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(variables);
			objectOutputStream.flush();
			// objectOutputStream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Serializable getVariable(String name) {
		if (variables != null) {
			return (Serializable) variables.get(name);
		}
		return null;
	}

	public static void testsaveToFile() {
		/*List<String> arraies = new ArrayList<String>();
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		arraies.add(Persistence.setVariable("sdf"));
		for (int i = 0; i < arraies.size(); i++) {
			System.out.print( arraies.get(i)+"  ");
			System.out.println(Persistence.getVariable(arraies.get(i)));
		}*/
		System.out.println("1689105960272394008|product");
		System.out.println(com.oa.listenner.Persistence.getVariable("1689105960272394008"));

	}

	public static void testReadFromFile() {
		System.out.println(Persistence23.getVariable("ss1"));
		System.out.println(Persistence23.getVariable("ss2"));
	}

	public static void main(String[] args) {

//		System.out.println(variables.size());
//		testsaveToFile();
		String name="sfasf3435s|sfsaf";
		System.out.println(name.replaceAll("(.*)\\|.*", "$1"));
		// testReadFromFile();
		System.out.println(Persistence23.class.getClass().getClassLoader().getResource("/").getPath());

//		 System.out.println(System.getProperty("java.io.tmpdir"));
	}
}
