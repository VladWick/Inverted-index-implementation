import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InvertedIndex {
	
	static Map<String, List<Tuple>> index = new HashMap<>();
	static Scanner scanner;
	
	public void search(List<String> searchWords) {
		ExtendedTuple exTuple;
		for(String str: searchWords) {
			List<Tuple> currentWordList = index.get(str);
			exTuple = new ExtendedTuple(str, currentWordList);
			exTuple.print();
		}
		System.out.println();
	}
	
	public void indexFile(File file) throws FileNotFoundException {
		
		scanner = new Scanner(file);
		int positionInFile = 0;
		while(scanner.hasNext()) {
			
			String str = scanner.nextLine();
			for(String word : str.split(" ")) {
				List<Tuple> tupleListForCurrentWord = index.get(word);
				positionInFile++;
				if(tupleListForCurrentWord == null) {
					tupleListForCurrentWord = new LinkedList<Tuple>();
					index.put(word, tupleListForCurrentWord);
				}
				tupleListForCurrentWord.add(new Tuple(file.getName(), positionInFile));
			}
		}
		
		System.out.println(file.getName() + " was indexed.");
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		List<File> files = new ArrayList<>();
		
		File currentDir = new File("");
		String currentPath = currentDir.getAbsolutePath();
		
		File file1 = new File(currentPath + "\\src\\docs\\doc1.txt ");
		File file2 = new File(currentPath + "\\src\\docs\\doc2.txt ");
		File file3 = new File(currentPath + "\\src\\docs\\doc3.txt ");
		File file4 = new File(currentPath + "\\src\\docs\\doc4.txt ");
		File file5 = new File(currentPath + "\\src\\docs\\doc5.txt ");
		File file6 = new File(currentPath + "\\src\\docs\\doc6.txt ");
		File file7 = new File(currentPath + "\\src\\docs\\doc7.txt ");
		File file8 = new File(currentPath + "\\src\\docs\\doc8.txt ");
		File file9 = new File(currentPath + "\\src\\docs\\doc9.txt ");
		File file10= new File(currentPath + "\\src\\docs\\doc10.txt ");
		
		files.add(file1);
		files.add(file2);
		files.add(file3);
		files.add(file4);
		files.add(file5);
		files.add(file6);
		files.add(file7);
		files.add(file8);
		files.add(file9);
		files.add(file10);
		
		InvertedIndex idx = new InvertedIndex();
		
		for(File file : files) {
			idx.indexFile(file);
		}
		scanner.close();
		System.out.println();
		
		List<String> wordForSearch = new ArrayList<>(Arrays.asList("cat", "123", "car", "стыда", "map"));
		idx.search(wordForSearch);
	}

}

class ExtendedTuple {
	private String word;
	List<Tuple> list;
	
	public ExtendedTuple(String word, List<Tuple> list) {
		this.word = word;
		this.list = list;
	}
	
	public void print() {
		System.out.println("Word '" + this.word + "' was founded in:");
		if(list != null) {
			for(int i = 0 ; i < list.size(); ++i) {
				list.get(i).printTuple();
			}
			System.out.println();
		} else {
			System.out.println("    Nothing found");
			System.out.println();
		}
	}
}

class Tuple {
	private String fileName;
	private int wordNumber;
	
	public Tuple(String fileName, int wordNumber) {
		this.fileName = fileName;
		this.wordNumber = wordNumber;
	}
	
	public void printTuple() {
		System.out.println("    in " + this.fileName + " as a '" + this.wordNumber + "' word");
	}
}
