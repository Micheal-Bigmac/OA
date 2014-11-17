package test;
public class testRegex {
	public static void main(String[] args) {
		String name = "Fork(领导三人会签)";
		String Regex = "^(Fork).*";
		System.out.println(name.matches(Regex));
	}
}
