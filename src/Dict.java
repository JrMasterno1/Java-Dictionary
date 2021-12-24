import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

public class Dict {
	private HashMap<String, HashSet<String>> dictionary;
	private LinkedList<String> historyList, deleteList;
	private LinkedHashMap<String, HashSet<String>> addList, editList;
	public Dict() {
		dictionary = new HashMap<String, HashSet<String>>();
		historyList = new LinkedList<String>();
		deleteList = new LinkedList<String>();
		addList = new LinkedHashMap<String, HashSet<String>>();
		editList = new LinkedHashMap<String, HashSet<String>>();
		
		LoadHistory();
		LoadDict();
	}
	void LoadDict() {
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
	void LoadHistory() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("history.txt")));
			String str;
			while((str = br.readLine()) != null) {
				historyList.add(str);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void appendAddList(String slang, String definition) {
		if(addList.containsKey(slang)) {
			addList.get(slang).add(definition);
		}
		else {
			HashSet<String> defs = new HashSet<String>();
			defs.add(definition);
			addList.put(slang, defs);
		}
	}
	public void appendEditList(String slang, String old_value, String new_value) {
		if(editList.containsKey(slang)) {
			if(editList.get(slang).contains(old_value)) {
				HashSet<String> defs = editList.get(slang);
				defs.remove(old_value);
				defs.add(new_value);
			}
			else {
				editList.get(slang).add(new_value);
			}
		}
		else {
			HashSet<String> defs = new HashSet<String>();
			defs.add(new_value);
			editList.put(slang, defs);
		}
	}
	public void AddDefinition(String slang, String definition) {
		appendAddList(slang, definition);
		dictionary.get(slang).add(definition);
	}
	public void AddNew(String slang, String definition) {
		appendAddList(slang, definition);
		HashSet<String> defs = new HashSet<String>();
		defs.add(definition);
		dictionary.put(slang, defs);
	}
	public boolean hasSlang(String slang) {
		return dictionary.containsKey(slang);
	}
	public boolean EditSlang(String slang, String old_value, String new_value) {
		HashSet<String > hs = dictionary.get(slang);
		boolean edited = false;
		for (String value: hs) {
			if (value.equals(old_value)) {
				appendEditList(slang, old_value, new_value);
				value = new_value;
				edited = true;
			}
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
	public HashMap<String, ArrayList<String>> definitionGame() {
		HashMap<String, ArrayList<String>> game = new HashMap<String, ArrayList<String>>();
		while(game.size() != 4) {
			String definition = dictionary.get(randomSlang()).iterator().next();
			game.put(definition, searchDefinition(definition));
		}
		return game;
	}
}
