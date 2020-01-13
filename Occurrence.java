/* *****************************************************************************
 *  Name: Eli Ji
 *  Description: Occurence class, used in WordRecord.
 **************************************************************************** */

public class Occurrence {

    private int sonnet;
    private int line;

    public Occurrence(int sonnet, int line){
        this.sonnet = sonnet;
        this.line = line;
    }

    public int getSonnet() {
        return sonnet;
    }

    public int getLine() {
        return line;
    }

    public String toString(){
        return (" Sonnet: " + sonnet + " Line: " + line);
    }
}
