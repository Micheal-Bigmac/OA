import java.util.HashSet;
import java.util.Set;

import com.oa.model.DocumentProperty;

public class test2 {
	public static void testSet() {
		Set<DocumentProperty> properties = new HashSet<DocumentProperty>();
		DocumentProperty property1 = new DocumentProperty();
		property1.setJava_lang_Long(1 + "");
		property1.setPropertyName("file");
		properties.add(property1);
		
		DocumentProperty property2 = new DocumentProperty();
		property2.setJava_lang_Integer(2 + "");
		property2.setPropertyName("file");
		properties.add(property2);
		
		DocumentProperty property3 = new DocumentProperty();
		property3.setJava_lang_String(3 + "");
		property3.setPropertyName("file");
		properties.add(property3);
		
		DocumentProperty property4 = new DocumentProperty();
		property4.setJava_util_Date(4 + "");
		property4.setPropertyName("file");
		properties.add(property4);
		
		for(DocumentProperty o : properties){
			System.out.println(o.toString());
		}
		System.out.println(properties.contains(property4));
		
	
	}

	public static void main(String[] args) {
		testSet();
	}
}
