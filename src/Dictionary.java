import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Dictionary {

	private ArrayList<String>[] hashTable;

	public Dictionary() {
		hashTable = new ArrayList[100000];
		implementDict();

	}
	
	
	public String findCheatWord(String letters){
		
		int pos = makeHash(letters, true);
		int hash = makeHash(letters, false);
		
		
		String back = "";
		Iterator itr = hashTable[pos].iterator();
		while (itr.hasNext()) {
			String next = (String) itr.next();
			int temp = makeHash((String) next, false);
			if(temp == hash){
			back += next + " ";
			}

		}
		
		
		return back;
	}

	public void implementDict() {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("dictionary.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNext()) {
			String word = scanner.next();

			int hash = makeHash(word, true);
			addToTable(hash, word);

		}
		scanner.close();

	}
	
	
	public void addToTable(int digit, String word) {
		if (hashTable[digit] == null) {
			hashTable[digit] = new ArrayList<String>();
		}
		hashTable[digit].add(word);

	}

	public int makeHash(String word, boolean modulo) {
		char[] numbWord = word.toLowerCase().toCharArray();
		Arrays.sort(numbWord);
		int all = 0;
		for (int i = 0; i < numbWord.length; i++) {
			// char letter = word.charAt(i);
			double letter = (numbWord[numbWord.length - 1 - i] - 96) * Math.pow(27, i);
			all += letter;
		}
		if(modulo)
		all %= 5000;

		return all;
	}



	public String getWord() {
		// return hashTable[5].get(0);
		String back = null;
		Iterator itr = hashTable[49].iterator();
		while (itr.hasNext()) {
			back += itr.next() + " ";

		}
		return back;

	}

	public int getSize() {
		return hashTable[49].size();

	}

	public static void main(String[] args) {
		Dictionary bla = new Dictionary();
		System.out.println(bla.findCheatWord("itlo"));


	}

}
