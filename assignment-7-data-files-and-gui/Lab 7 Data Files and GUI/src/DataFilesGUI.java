/*
 * Haonan Peng
 *
 * This class contains the DataFilesGUI class to implement files read and write with GUI
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFilesGUI {
    private JLabel l1, l2;

    private JTextField tf1, tf2;

    private JTextArea ta1, ta2;

    private JButton b1, b2;

    private ArrayList<String> fileContent;

    // Constructor
    DataFilesGUI(){
        // initialize JFrame
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(new GridLayout(4,2,50,25));
        mainFrame.setTitle("Lab 7 Data Files and GUI");

        setLbls(); // set up labels, textField, textArea
        setReadButton(); // set up button to read file
        setWriteButton(); // set up button to write file

        // add labels, texts, buttons to frame
        mainFrame.add(l1); mainFrame.add(l2);
        mainFrame.add(tf1); mainFrame.add(tf2);
        mainFrame.add(b1); mainFrame.add(b2);
        mainFrame.add(ta1); mainFrame.add(ta2);

        // set up frame
        mainFrame.setSize(700,350);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /*
     * brief: set up labels, textFields and textArea
     */
    private void setLbls(){
        l1 = new JLabel("Reading File Name");
        l2 = new JLabel("File Name to Write");

        tf1 = new JTextField("Annual.csv");
        tf2 = new JTextField();

        ta1 = new JTextArea();
        ta2 = new JTextArea();
    }

    /*
     * brief: set up button and actionListener to read file
     */
    private void setReadButton(){
        b1 = new JButton("Click to read from file");

        // lambda expression of actionListener
        b1.addActionListener(e->{
            // get file name from textField
            String read = tf1.getText();
            readFiles(read);
        });
    }

    /*
     * brief: method to read file
     */
    private void readFiles(String file){
        // initialize ArrayList to store file content
        fileContent = new ArrayList<String>();

        // read file and catch exception if the file is not found
        try(Scanner reader = new Scanner(new File(file))){
            int i = 0; // initialize loop counter

            // read file
            while(reader.hasNextLine()){
                fileContent.add(reader.nextLine());

                // print the first five lines of content on textArea
                if(i < 5){
                    ta1.append(fileContent.get(i)+"\n");
                    i++;
                }
            }
        }catch (IOException ex){
            ta1.setText("File to Read Not Found!");
        }
    }

    /*
     * brief: set up button and actionListener to write to file
     */
    private void setWriteButton(){
        b2 = new JButton("Click to write to file");
        b2.addActionListener(e ->{
            String write = tf2.getText();
            writeFiles(write);
        });
    }

    /*
     * brief: method to write to file
     */
    private void writeFiles(String file){

        // write to file and catch exception if unable to write
        try(FileWriter writer = new FileWriter(file)){
            int i = 0; // loop counter

            // for-each loop to write each line of original to the new file
            for(String line: fileContent){
                // write content line by line to new file
                writer.write(line+"\n");

                // show first five lines in textArea
                if(i < 5){
                    ta2.append(line+"\n");
                    i++;
                }
            }
        }catch (IOException ex){
            ta2.setText("Unable to Write!");
        }
    }

}
