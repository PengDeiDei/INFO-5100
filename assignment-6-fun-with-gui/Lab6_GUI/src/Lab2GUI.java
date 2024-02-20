/*
 * author: Haonan Peng
 *
 * summary: this is a GUI class for lab 6 which re-implement the content in lab 2
 * with GUI.
 */
import javax.swing.*;
import java.awt.*;

public class Lab2GUI{
    private JPanel p1, p2, p3;
    private JLabel l1, l2, l3, l4;

    private JTextField tf1, tf2, tf3, tf4;
    private JButton button;

    // class constructor
    Lab2GUI(){
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Lab 6 - Fun with GUI");

        setLblNText(); // set labels and textFields

        setButton(); // set button

        setPanels(); // set panels

        // add panels to frame
        mainFrame.add(p1,"North");
        mainFrame.add(p2,"Center");
        mainFrame.add(p3,"South");

        mainFrame.setSize(400,350);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /*
     * brief: setter method to set up JLabels and JTextField
     */
    private void setLblNText(){
        // set labels
        l1 = new JLabel("Total Assignment Points");
        l2 = new JLabel("Earned Points");
        l3 = new JLabel("Percentage of Class");
        l4 = new JLabel("Weighted Score");

        // set textFields
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
    }

    /*
     * brief: setter method to set up JButton and ActionListener with actionPerform
     */
    private void setButton(){
        button = new JButton("Get Score");

        // adding ActionListener to button with actionPerform function
        // in lambda expression
        button.addActionListener(e -> {
            // get data from the entered text in textFields
            String s1 = tf1.getText();
            String s2 = tf2.getText();
            String s3 = tf3.getText();

            // convert string to integer
            int totals = Integer.parseInt(s1);
            int earned = Integer.parseInt(s2);
            int percent = Integer.parseInt(s3);

            double result = (double) earned / totals * percent;

            // scale the result into 3 decimal places
            double scale = Math.pow(10,3);
            result = Math.round(result*scale)/scale;

            // convert double to string and set it in the last textField
            tf4.setText(String.valueOf(result));
        });
    }

    /*
     * brief: setter method to set up JPanels
     */
    private void setPanels(){
        p1 = new JPanel();
        p1.setLayout(new GridLayout(3,2,10,25));
        p1.setPreferredSize(new Dimension(400, 200));

        // add first three labels and textFields to panel 1
        p1.add(l1); p1.add(tf1);
        p1.add(l2); p1.add(tf2);
        p1.add(l3); p1.add(tf3);

        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.setPreferredSize(new Dimension(400,80));

        // add button to panel 2
        p2.add(button,"Center");

        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(400,70));
        p3.setLayout(new GridLayout(1,2,10,10));

        // add last label and textFields to panel 3
        p3.add(l4); p3.add(tf4);
    }

}
