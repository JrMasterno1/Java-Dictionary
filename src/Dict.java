import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Dict {
	TreeMap<String, Set<String>> dictionary;
	public Dict() {
		dictionary = new TreeMap<String, Set<String>>();
		LoadFile();
	}
	void LoadFile() {
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("slang.txt")));
			while((str = br.readLine()) != null) {
				String[] word = str.split("`");
				if (word.length == 2) {	
					String[] defStrings = word[1].split("\\|");
					for (String s : defStrings)
	                    s = s.trim();
					Set<String> definitionSet = new HashSet<String>(Arrays.asList(defStrings));
					dictionary.put(word[0].trim(), definitionSet);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Set<String> findBySlang(String slang){
		return dictionary.get(slang);
	}
}
