
public class GetValueRegex {

	public static void main(String[] args) {
		String value="455回退给张三审批";
		String Regex=".*(回退).*";
		System.out.println(value.matches(Regex));
		System.out.println(value.replaceAll(Regex, "$1"));

	}

}
