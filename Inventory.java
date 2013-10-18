import java.util.*;
import java.io.*;

public class Inventory {

	private TreeMap<Integer, Integer> inventory;
	
    public Inventory() {

    	inventory = new TreeMap<Integer, Integer>();
        try {
        	readFile();
        } catch (IOException e) {
        	System.out.println("ERROR");
            e.printStackTrace();
        }
     }

        
    private void readFile() throws IOException {
                
    	//BufferedReader in = new BufferedReader(new FileReader("C:\\MyStuff\\AndroidProgramming\\InventoryInput.txt"));
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Tripathy\\Documents\\Eclipse\\ATCS\\ATCS\\src\\InventoryInput.txt"));
        
    	String sCurrentLine = "";
        while((sCurrentLine = in.readLine()) != null) {
        	StringTokenizer tokens = new StringTokenizer(sCurrentLine);
            String id = tokens.nextToken();
            String count = tokens.nextToken();
            inventory.put(Integer.parseInt(id), Integer.parseInt(count));
        }
    }
        
    public void display() {
    	
    	//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\MyStuff\\AndroidProgramming\\InventoryOutput.txt")));
    	try {
    	
    		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Tripathy\\Documents\\Eclipse\\ATCS\\ATCS\\src\\InventoryOutput.txt")));
    		out.println("Inventory ID=Count");
    		Iterator entries = inventory.entrySet().iterator();
    		for(int i = 0; i < inventory.size(); i++) {
    			out.println(entries.next().toString());
    		}
                
    		out.flush();
    		out.close();
    	}
    	catch(IOException e) {
    		System.out.println("IO Error");
    		e.printStackTrace();
    	}
    }
        
    //same thing as display()
    public void update() {
    
    	//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\MyStuff\\AndroidProgramming\\InventoryOutput.txt")));
    	try {
    		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Tripathy\\Documents\\Eclipse\\ATCS\\ATCS\\src\\InventoryOutput.txt")));

    		out.println("Inventory ID=Count");
    		Iterator entries = inventory.entrySet().iterator();
    		for(int i = 0; i < inventory.size(); i++) {
    			out.println(entries.next().toString());
    		}
            
    		out.flush();
    		out.close();
    	}
    	catch(IOException e) {
    		System.out.println("IO Error");
    		e.printStackTrace();
    	}
    }
        
    public void addNew(int id, int count){
    	inventory.put(id, count);
    }
        
    public void edit(int id, int count){
    	inventory.put(id, count);
    }
        
    public int findCount(int id){
    	
    	if(inventory.get(id) != null) {
    		return inventory.get(id);
    	}
    	else {
    		return Integer.MIN_VALUE;
    	}
    	
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
        		System.out.println("ID: " + id + " Count: " + count);
        	}
        }
    }
}