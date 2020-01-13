/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date:    12-13-19
 *
 *  Description:  My implementation of a hash table. Hash function is based on
 *                frequency of first and last letters. Data source used:
 *                https://norvig.com/mayzner.html
 **************************************************************************** */

import java.util.ArrayList;

public class HashTable {

    private WordRecord[] table;
    private int size;
    private int totalProbes = 0;

    //used in hash function, index corresponds with letter, frequency as percent
    private static double[] firstLetterFrequencies;
    private static double[] lastLetterFrequencies;
    //used in hash function, index corresponds with letter, position as a percent of total size
    private static double[] firstLetterPosition;
    private static double[] lastLetterPosition;

    public HashTable(int size){
        this.size = size;
        table = new WordRecord[size];

        firstLetterFrequencies = new double[] {11.682, 4.434, 5.238, 3.174, 2.799, 4.027, 1.642, 4.200, 7.294, 0.511, 0.456,
                                               2.415, 3.826, 2.284, 7.631, 4.319, 0.222, 2.826, 6.686, 15.978, 1.183, 0.824,
                                               5.497, 0.045, 0.763, 0.045};
        lastLetterFrequencies = new double[] {2.819, 0.123, 0.603, 9.981, 20.134, 0.561, 2.939, 2.712, 0.752, 0.022, 0.802,
                                              3.465, 1.656, 9.310, 4.177, 0.541, 0.013, 5.899, 12.903, 8.971, 0.389, 0.055,
                                              0.821, 0.165, 6.002, 0.033};
        firstLetterPosition = new double[26];
        lastLetterPosition = new double[26];

        firstLetterPosition[0] = 0.0;
        lastLetterPosition[0] = 0.0;

        //position is determined by sum of percentages before index
        for(int i=1; i<firstLetterPosition.length; i++){
            double sum = 0;
            for(int j = i-1; j>= 0; j--){
                sum = sum + firstLetterFrequencies[j];
            }
            firstLetterPosition[i] = sum;
        }

        for(int i=1; i<lastLetterPosition.length; i++){
            double sum = 0;
            for(int j = i-1; j>= 0; j--){
                sum = sum + lastLetterFrequencies[j];
            }
            lastLetterPosition[i] = sum;
        }
    }

    public void insert(WordRecord newWordRecord){
        //change word to lower case
        newWordRecord.setWord(newWordRecord.getWord().toLowerCase());
        int index = hash(newWordRecord.getWord());

        while(true) {

            totalProbes++;
            //if empty, insert
            if(table[index] == null){
                table[index] = newWordRecord;
                break;
            }

            //if word already exists in table, add onto the list of occurences
            else if(table[index].getWord().equals(newWordRecord.getWord())) {
                ArrayList<Occurrence> newList = table[index].getOccurrences();
                for (int i = 0; i < newWordRecord.getOccurrences().size(); i++) {
                    newList.add(newWordRecord.getOccurrences().get(i));
                }
                table[index].setOccurrences(newList);
                totalProbes++;
                break;
            }

            index = (index + 1) % size;
        }
    }

    // returns the index of the word if found, returns -1 if the search misses
    public int search(String keyWord){
        keyWord = keyWord.toLowerCase();
        int index = hash(keyWord);
        while(table[index]!=null) {
            if (table[index].getWord().equals(keyWord)) {
                return index;
            } index++;
        }
        return -1;
    }

    private int hash(String s){
        int firstCharNum = s.charAt(0) - 97;
        int lastCharNum = s.charAt(s.length()-1) - 97;

        int gapToNext = (int)((firstLetterFrequencies[firstCharNum] * size) / 100 );
        //for the extra space at the end (percentages do not add up to 100%)
        if(firstCharNum == 25) {
            gapToNext = (int) ((100 - firstLetterPosition[firstCharNum]) * size / 100);
        }
        int hashNum = (int)(((firstLetterPosition[firstCharNum] * size) + (lastLetterPosition[lastCharNum] * gapToNext)) / 100);
        return (hashNum % size);
    }

    public WordRecord[] getTable() {
        return table;
    }

    public int getTotalProbes() {
        return totalProbes;
    }

    public static void main(String[] args) {
        HashTable HT = new HashTable(1000);
        ArrayList<Occurrence> list = new ArrayList<Occurrence>();
        list.add(new Occurrence(10,10));
        list.add(new Occurrence(10,12));
        HT.insert(new WordRecord("bat", list));

        ArrayList<Occurrence> list2 = new ArrayList<Occurrence>();
        list2.add(new Occurrence(1,9));
        list2.add(new Occurrence(11,19));
        HT.insert(new WordRecord("bat", list2));

        ArrayList<Occurrence> bigList = HT.table[HT.search("bat")].getOccurrences();
        for(int i =0; i < bigList.size(); i++){
            int l = bigList.get(i).getLine();
            int s = bigList.get(i).getSonnet();
            System.out.print(" sonnet: " + s + " line: " + l);
        }
    }
}
