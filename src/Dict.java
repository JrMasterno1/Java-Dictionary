import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

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
	public HashSet<String> searchSlang(String slang){
		return dictionary.get(slang);
	}
	public void AddDefinition(String slang, String definition) {
		dictionary.get(slang).add(definition);
	}
	public boolean hashSlang(String slang) {
		if(dictionary.containsKey(slang)) return true;
		return false;
	}
	public boolean EditSlang(String slang, String old_value, String new_value) {
		HashSet<String > hs = dictionary.get(slang);
		boolean edited = false;
		for (String value: hs) {
			if (value.equals(old_value)) {
				value = new_value;
				edited = true;
			}
		}
		return edited;
	}
	public void deleteSlang(String slang) {
		dictionary.remove(slang);
	}
	public ArrayList<String> searchDefinition(String definition) {
		ArrayList<String> slangs = new ArrayList<String>();
		for (Entry<String, HashSet<String>> entry : dictionary.entrySet()) {
			String slang = entry.getKey();
			HashSet<String> definitions = entry.getValue();
			for (Iterator iterator = definitions.iterator(); iterator.hasNext();) {
				String defString = (String) iterator.next();
				if (definition.equalsIgnoreCase(defString)) {
					slangs.add(slang);
					break;
				}
			}
			
		}
		return slangs;
	}
	public String randomSlang() {
		Random r = new Random();
		ArrayList<String> slangs = new ArrayList<String>(dictionary.keySet());
		return slangs.get(r.nextInt(slangs.size()));
	}
	public HashMap<String, HashSet<String>> slangGame() {
		HashMap<String, HashSet<String>> game = new HashMap<String, HashSet<String>>();
		while(game.size() != 4) {
			String slang = randomSlang();
			game.put(slang, dictionary.get(slang));	
		}
		return game;
	}
}
