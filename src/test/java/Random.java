import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Random {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		// add elements to al, including duplicates
		al.add("divya");
		al.add("divya");
		al.add("kiruthika");
		
		System.out.println(al.size());
		for (int a=0;a<al.size();a++){
			System.out.println(al.get(a));
		}
		
		Set<String> hs = new HashSet<>();
		hs.addAll(al);
		al.clear();
		al.addAll(hs);
		
		System.out.println(al.size());
		for (int a=0;a<al.size();a++){
			System.out.println(al.get(a));
		}
	}

}
