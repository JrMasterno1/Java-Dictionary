import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Dict {
	private HashMap<String, HashSet<String>> dictionary;
	private LinkedList<String> historyList, deleteList;
	private LinkedHashMap<String, HashSet<String>> modifiedList;
	public Dict() {
		dictionary = new HashMap<String, HashSet<String>>();
		historyList = new LinkedList<String>();
		deleteList = new LinkedList<String>();
		modifiedList = new LinkedHashMap<String, HashSet<String>>();
		
		loadMap(dictionary, "slang.txt");
		loadLinkedList(historyList, "history.txt");
		loadLinkedList(deleteList, "delete.txt");
		for (Iterator iterator = deleteList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			dictionary.remove(string);
		}
		loadMap(modifiedList, "modified.txt");
		for (Map.Entry<String, HashSet<String>> entry : modifiedList.entrySet()) {
			String key = entry.getKey();
			HashSet<String> val = entry.getValue();
			dictionary.put(key, val);
		}
	}
	void loadMap(Map<String, HashSet<String>> map, String filename) {
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			while((str = br.readLine()) != null) {
				String[] word = str.split("`");
				if (word.length == 2) {	
					String[] defStrings = word[1].split("\\|");
					for (String s : defStrings) {
	                    s = s.strip();
					}
					HashSet<String> definitionSet = new HashSet<String>(Arrays.asList(defStrings));
					map.put(word[0].strip(), definitionSet);
				}
			}
		} catch (IOException e) {
			
		}
	}
	void loadLinkedList(LinkedList<String> list, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			String str;
			while((str = br.readLine()) != null) {
				list.add(str);
			}
			br.close();
		} catch (IOException e) {
			
		}
	}
	void saveLinkedList(LinkedList<String> list, String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				bw.write(string);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			
		}
		
	}
	void saveModified() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("modified.txt")));
			for (Entry<String, HashSet<String>> entry : modifiedList.entrySet()) {
				String key = entry.getKey();
				HashSet<String> val = entry.getValue();
				Iterator<String> it = val.iterator();
				String def = it.next();
				while (it.hasNext()) {
					def += "|" + it.next();
					
				}
				bw.write(key+ "`" + def);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			
		}
	}
	public void Save() {
		saveLinkedList(deleteList, "delete.txt");
		saveLinkedList(historyList, "history.txt");
		saveModified();
	}
	public void AddHistory(String his) {
		historyList.add(his);
	}
	public LinkedList<String> getHistory(){
		return historyList;
	}
	public HashSet<String> searchSlang(String slang){
		return dictionary.get(slang);
	}

	public void AddDefinition(String slang, String definition) {
		dictionary.get(slang).add(definition);
		modifiedList.put(slang, dictionary.get(slang));
	}
	public void AddNew(String slang, String definition) {
		HashSet<String> defs = new HashSet<String>();
		defs.add(definition);
		dictionary.put(slang, defs);
		modifiedList.put(slang, dictionary.get(slang));
	}
	public boolean hasSlang(String slang) {
		return dictionary.containsKey(slang);
	}
	public boolean EditSlang(String slang, String old_value, String new_value) {
		HashSet<String > hs = dictionary.get(slang);
		boolean edited = false;
		for (String value: hs) {
			if (value.strip().equals(old_value)) {
				hs.remove(old_value);
				hs.add(new_value);
				edited = true;
				break;
			}
		}
		if (edited) {		
			//dictionary.put(slang, hs);
			modifiedList.put(slang, hs);
		}
		return edited;
	}
	public void deleteSlang(String slang) {
		deleteList.add(slang);
		dictionary.remove(slang);
	}
	public ArrayList<String> searchDefinition(String definition) {
		ArrayList<String> slangs = new ArrayList<String>();
		for (Entry<String, HashSet<String>> entry : dictionary.entrySet()) {
			String slang = entry.getKey();
			HashSet<String> definitions = entry.getValue();
			for (Iterator iterator = definitions.iterator(); iterator.hasNext();) {
				String defString = (String) iterator.next();
				if (defString.contains(definition)) {
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
	public HashMap<String, ArrayList<String>> definitionGame() {
		HashMap<String, ArrayList<String>> game = new HashMap<String, ArrayList<String>>();
		while(game.size() != 4) {
			String definition = dictionary.get(randomSlang()).iterator().next();
			game.put(definition, searchDefinition(definition));
		}
		return game;
	}
	public void reset() {
		loadMap(dictionary, "slang.txt");
		for (Map.Entry<String, HashSet<String>>entry : modifiedList.entrySet()) {
			String key = entry.getKey();
			HashSet<String> val = entry.getValue();
			if (dictionary.containsKey(key) == false) {
				dictionary.remove(key);
			}
			
		}
		historyList.clear();
		deleteList.clear();
		modifiedList.clear();
	}
}
