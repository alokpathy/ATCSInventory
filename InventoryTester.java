import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Generates random input for testing purposes. 
 *
 */
public class InventoryTester {

        public InventoryTester() throws IOException{
                
                //BufferedReader in = new BufferedReader(new FileReader("H:\\ATCompSci\\InventoryOutput.txt"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Tripathy\\Documents\\Eclipse\\ATCS\\ATCS\\src\\InventoryInput.txt")));
                
                
                Random rand = new Random();
                for(int i = 0; i < 10; i++) {
                        out.println(rand.nextInt(99999) + " " + rand.nextInt(99999));
                }
                
                out.flush();
                out.close();
                
                //new Inventory();
                

        }
        
        public static void main(String[] args) throws IOException {
                
                InventoryTester inventory = new InventoryTester();
                
        }
}
