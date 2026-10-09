package training;

import java.util.ArrayList;

public class Collections {

	
	public static void positvsList( ArrayList<Integer> numbers){
		
		numbers.removeIf(number -> number < 0);
	}
	
	public static ArrayList<String> shortToLong(ArrayList<String> names) {
			
		names.sort((name1, name2) -> name1.length() - name2.length());
		
		return names;
	}
}
