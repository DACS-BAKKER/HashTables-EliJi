/* *****************************************************************************
 *  Name: Eli Ji
 *  Description:
 **************************************************************************** */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConcordanceGraphics implements ActionListener {

    SonnetConcordance sc = new SonnetConcordance();

    private String searchWord = "";
    JTextField searchBox;
    JButton searchButton;
    JTextArea results;
    JScrollPane scroll;

    ConcordanceGraphics() {

        JFrame f = new JFrame();

        JLabel title1 = new JLabel("Shakespearean Sonnet");
        title1.setBounds(25,20,200,15);
        title1.setHorizontalAlignment(JLabel.CENTER);
        JLabel title2 = new JLabel("Concordance");
        title2.setBounds(25,40,200,15);
        title2.setHorizontalAlignment(JLabel.CENTER);

        searchBox = new JTextField();
        searchBox.setText("Enter Word");
        searchBox.setHorizontalAlignment(JTextField.CENTER);
        searchBox.setBounds(50,80,150,30);

        searchButton = new JButton("Go!");
        searchButton.setBounds(75,120,100,30);
        searchButton.addActionListener(this);

        results = new JTextArea();
        results.setEditable(false);

        scroll = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(250,10,240,160);

        f.add(searchBox); f.add(searchButton); f.add(scroll); f.add(title1);f.add(title2);
        f.setSize(500,200);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public String getSearchWord() {
        return searchWord;
    }

    public static void main(String[] args) {
        new ConcordanceGraphics();
    }

    public void actionPerformed(ActionEvent e) {
        results.setText(""); //clear results
        String searchWord = searchBox.getText();
        if(searchWord == null || searchWord.equals("")) return;
        ArrayList<Occurrence> occurrenceList = sc.getOccurences(searchWord);
        if(occurrenceList == null) results.append(" The word '" + searchWord + "' appeared 0 times: \n");
        else {
            results.append(" The word '" + searchWord + "' appeared " + occurrenceList.size() + " times: \n");
            for (int i = 0; i < occurrenceList.size(); i++) {
                results.append(occurrenceList.get(i).toString() + "\n");
            }
        }
    }
}
