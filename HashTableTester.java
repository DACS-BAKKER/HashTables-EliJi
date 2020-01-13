/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date:    12-13-19
 *
 *  Description:  Tester for my hash table
 **************************************************************************** */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class HashTableTester {

    static HashTable HT = new HashTable(2000);

    public static void main(String[] args) {
        testHashTable();
        WordRecord[] table = HT.getTable();
        for(int i=0; i<table.length; i++){
            if(table[i] == null){
                System.out.print("empty ,");
            }
            else {
                System.out.print(table[i].getWord() + ", ");
            }
        }
        double averageProbes = (double) HT.getTotalProbes() / 1000;
        System.out.println("");
        System.out.println("Average probes: " + averageProbes);
    }

    private static void testHashTable(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("1000WordDictionary.txt"));
            while(true) {
                String word = br.readLine();
                if(word == null){
                    break;
                }
                ArrayList<Occurrence> list = new ArrayList<Occurrence>();
                HT.insert(new WordRecord(word, list));
            }
        } catch(IOException ex){
           System.out.println("Error in reading file.");
        }
    }
}
