/*
 * Haonan Peng
 * INFO-5100 Extra Credit
 *
 * This is the class file for the extra credit. The class is to read file line by line
 * and extracts the top 3 players with the shortest times
 */
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class RaceEvaluate {
    // using the self-sorting feature of TreeMap to store scores and players' names
    private TreeMap<Integer,String> top3;

    RaceEvaluate(){
        top3 = new TreeMap<>();
    }

    public void readFile(String file){
        try(Scanner fileReader = new Scanner(new File(file))){
            while(fileReader.hasNextLine()){
                // read by line
                String line = fileReader.nextLine();

                // separate line into string by comma
                String[] items = line.split(",");
                String name = items[0];
                int score = Integer.parseInt(items[1]);

                // insert element into treemap
                top3.put(score,name);

                // drop the last entry with the longest time
                if(top3.size()>3){
                    top3.pollLastEntry();
                }
            }
        }catch(IOException e){
            System.out.println("File Not Found!");
        }
    }

    public void getResult(){
        // show the result
        System.out.println("The top 3 players and their times for race are:");
        for(Map.Entry<Integer,String> entry: top3.entrySet()){
            System.out.println(entry.getValue() +", "+ entry.getKey());
        }
    }

}
