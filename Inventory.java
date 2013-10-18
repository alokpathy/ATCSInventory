import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * A representation of an inventory. Each item has an I.D number and a count number. 
 * I.D numbers and count numbers are inputed via a text file, and the revised inventory is also 
 * outputed on another text file.
 * 
 * @author Alok Tripathy, Cody Yang
 *
 */
public class Inventory {

	private TreeMap<Integer, Integer> inventory;
	
	/**
	 * Creates Inventory and reads file as input upon initialization. 
	 */
    public Inventory() {

    	inventory = new TreeMap<Integer, Integer>();
        try {
        	readFile();
        } catch (IOException e) {
        	System.out.println("ERROR");
            e.printStackTrace();
        }
     }

    /**
     * Reads text file for I.D number and count number inputs.
     * BufferedReader is used over FileReader and Scanner for efficiency purposes. 
     * @throws IOException
     */
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
        
        in.close();
    }
    
    /**
     * Prints revised inventory onto a separate text file.
     */
    public void display() {
    	
    	//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\MyStuff\\AndroidProgramming\\InventoryOutput.txt")));
    	try {
    	
    		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Tripathy\\Documents\\Eclipse\\ATCS\\ATCS\\src\\InventoryOutput.txt")));
    		out.println("Inventory ID=Count");
    		Iterator<Entry<Integer, Integer>> entries = inventory.entrySet().iterator();
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

    public void update() {
    	display();
    }
        
    /**
     * Adds new item to inventory.
     * @param id      - I.D number for inventory item.
     * @param count   - Count number for inventory item.
     */
    public void addNew(int id, int count){
    	inventory.put(id, count);
    }
     
    /**
     * Edits an existing entry in inventory. 
     * If the requested entry does not exist, a new one is created with the same I.D and count numbers.
     * @param id       - I.D number for inventory item.
     * @param count    - Count number for inventory item.
     */
    public void edit(int id, int count){
    	inventory.put(id, count);
    }
        
    /**
     * Gets the count number for an existing entry.
     * If the requested entry does not exist, the lowest integer is returned to indicate it.
     * @param id       - I.D number for requested entry.
     * @return         - Count number for I.D or smallest integer.
     */
    public int findCount(int id){
    	return (inventory.get(id) != null ? inventory.get(id) : Integer.MIN_VALUE);
    }
    
    /**
     * Removes an existing entry.
     * If the request entry does not exist, nothing happens.
     * @param id       - I.D number for requested entry.
     */
    public void removeItem(int id) {
    	if(inventory.get(id) != null) {
    		inventory.remove(id);
    	}
    }
    
    /**
     * Gets all items with highest count number.
     * @return          - All inventory items with the highest count number.
     */
    public ArrayList<InventoryItem> getMaxCountItems() {
                
    	ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
    	int maxCount = 0;
        Iterator<Entry<Integer, Integer>> entries = inventory.entrySet().iterator();
        while(entries.hasNext()) {
            int count = entries.next().getValue();
        	if(count > maxCount) {
            	maxCount = count;
            }
        }
                
        Iterator<Entry<Integer, Integer>> moarEntries = inventory.entrySet().iterator();
        while(moarEntries.hasNext()) {
        	Entry<Integer, Integer> entry = moarEntries.next();
        	int id = entry.getKey();
        	int count = entry.getValue();
        	if(count == maxCount) {
        		items.add(new InventoryItem(id, count));
        	}
        }
        
        return items;
    }
}
