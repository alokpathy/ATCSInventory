import java.util.*;
import java.io.*;

public class Inventory {

	private TreeMap<Integer, Integer> inventory;
	public Inventory() {
		//inventory = new InventoryItem[Integer.MAX_VALUE];
		inventory = new TreeMap<Integer, Integer>();
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	private void readFile() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("H:\\ATCompSci\\InventoryTestInput.txt"));
		
		String sCurrentLine = "";
		while((sCurrentLine = in.readLine()) != null) {
			StringTokenizer tokens = new StringTokenizer(sCurrentLine);
			String id = tokens.nextToken();
			String count = tokens.nextToken();
			inventory.put(Integer.parseInt(id), Integer.parseInt(count));
		}
	}
	
	public void display() throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("H:\\ATCompSci\\InventoryOutput.txt")));
		
		out.println("Inventory ID=Count");
		Iterator entries = inventory.entrySet().iterator();
		for(int i = 0; i < inventory.size(); i++) {
			out.println(entries.next().toString());
		}
		
		out.flush();
		out.close();
	}
	
	public void addNew(int id, int count){
		inventory.put(id, count);
	}
	public int findCount(int id){
		return inventory.get(id);
	}
	
	public void removeItem(int id) {
		inventory.remove(id);
	}
	
	private int getCount(String myString){
		int index = myString.indexOf("=");
		String count = myString.substring(index + 1);
		return Integer.parseInt(count);
	}
	
	private int getID(String myString){
		int index = myString.indexOf("=");
		String id = myString.substring(0, index);
		return Integer.parseInt(id);
	}
	
	public void printMax() {
		
		int maxCount = 0;
		Iterator entries = inventory.entrySet().iterator();
		while(entries.hasNext()) {
			int count = getCount(entries.next().toString());
			if(count > maxCount) {
				maxCount = count;
			}
		}
		
		Iterator moarEntries = inventory.entrySet().iterator();
		while(moarEntries.hasNext()) {
			String entry = moarEntries.next().toString();
			int id = getID(entry);
			int count = getCount(entry);
			if(count == maxCount) {
				System.out.println(id + " = " + count);
			}
		}
	}
	
	public static void main(String[] args) {
		new Inventory();
	}
}
