/* *****************************************************************************
 *  Name: Eli Ji
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SonnetConcordance {

    private HashTable HT;

    public SonnetConcordance(){
        HT = new HashTable(100000);
        readAndTokenizeSonnets();
    }

    public ArrayList<Occurrence> getOccurences(String keyWord){
        int index = HT.search(keyWord);
        if(index == -1){
            return null;
        }
        return HT.getTable()[index].getOccurrences();
    }

    public void readAndTokenizeSonnets(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("sonnets.txt"));
            int currentLine = 1;
            while(true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                //remove all punctuation
                line = line.replaceAll("[^a-zA-Z ]", "");

                //ignore in between lines
                if (!(currentLine % 17 >= 1 && currentLine % 17 <= 3)) {
                    //tokenizes each string
                    StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                        int sonnetNum = ((currentLine - 1) / 17) + 1;
                        int lineNum = (currentLine - 3) % 17;
                        ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
                        occurrenceList.add(new Occurrence(sonnetNum, lineNum));
                        WordRecord wr = new WordRecord(st.nextToken(), occurrenceList);
                        HT.insert(wr);
                    }
                }
            currentLine++;
            }
            br.close();
        }catch(IOException ex){
            System.out.println("Error in reading file.");
        }
    }
}
