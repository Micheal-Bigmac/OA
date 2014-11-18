package com.oa.listenner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//将内存中的一个对象持久化到一个文件中
public class Persistence {

	public static Map variables = null;
	private static String saveFileName =  System.getProperty("java.io.tmpdir")+"/temp.object";
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
		 saveToFile();
		return randomNameString;
	}

	public static void saveToFile() {
		try {
			FileOutputStream outputStream = new FileOutputStream(saveFileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(variables);
			objectOutputStream.flush();
			outputStream.flush();
			objectOutputStream.close();
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
	public static void removeVariable(String name){
		variables.remove(name);
	}
	public static void main(String[] args) {
		System.out.println(variables.size());
	}
}
