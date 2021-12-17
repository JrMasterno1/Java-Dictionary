import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Dict {
	HashMap<String, HashSet<String>> dictionary;
	public Dict() {
		dictionary = new HashMap<String, HashSet<String>>();
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
					HashSet<String> definitionSet = new HashSet<String>(Arrays.asList(defStrings));
					dictionary.put(word[0].trim(), definitionSet);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashSet<String> findBySlang(String slang){
		return dictionary.get(slang);
	}
	public void AddDefinition(String slang, String definition) {
		dictionary.get(slang).add(definition);
	}
	public boolean hashSlang(String slang) {
		if(dictionary.containsKey(slang)) return true;
		return false;
	}
	public void EditSlang(String slang, String old_value, String new_value) {
		HashSet<String > hs = dictionary.get(slang);
		hs.forEach(value -> {
			if (value.equals(old_value)) {
				hs.remove(value);
				hs.add(new_value);
			}
		});
	}
	public void deleteSlang(String slang) {
		dictionary.remove(slang);
	}
}
