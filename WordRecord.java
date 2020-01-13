/* *****************************************************************************
 *  Name: Eli Ji
 *  Description: WordRecord Class.
 **************************************************************************** */

import java.util.ArrayList;

public class WordRecord {

    private String word;
    private ArrayList<Occurrence> occurrences;

    public WordRecord(String word, ArrayList<Occurrence> occurrenceList){
        this.word = word;
        this.occurrences = occurrenceList;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(ArrayList<Occurrence> occurrenceList) {
        this.occurrences = occurrenceList;
    }
}
