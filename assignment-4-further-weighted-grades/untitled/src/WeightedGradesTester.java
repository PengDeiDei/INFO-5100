/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This is the tester file for WeightedGrades class
 */
import java.util.Scanner;
import java.util.Arrays;
public class WeightedGradesTester {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // get total number of assignments to create new weightedGrades
        // object
        System.out.println("How many assignments in total?");
        int totalNum = console.nextInt();
        WeightedGrades myGrades = new WeightedGrades(totalNum);

        // set total points of all assignments
        System.out.println("Enter the total points seperated by enter");
        for(int i = 0; i < totalNum; i++){
            myGrades.setTotalPoints(console.nextInt());
        }
        System.out.println("The total points are: " + Arrays.toString(myGrades.getTotalPoints()) );

        // set earned points of all assignments
        System.out.println("Enter the earned points seperated by enter");
        for(int i = 0; i < totalNum; i++){
            myGrades.setEarnedPoints(console.nextDouble());
        }
        // display the array of earned points
        System.out.println("The earned points are: " + Arrays.toString(myGrades.getEarnedPoints()) );


        // set weights of all assignments
        System.out.println("Enter the weights seperated by enter");
        for(int i = 0; i < totalNum; i++){
            myGrades.setWeights(console.nextInt());
        }
        System.out.println("The weights are: " + Arrays.toString(myGrades.getWeights()) );

        // check if the weights can be summed up to 100,
        // if not, ask user to whether reset the weights
        while(!myGrades.checkWeights()){
            System.out.println("The weights you entered can't be summed up to 100.");
            System.out.println("Enter Y to reset the weights or enter N to manipulate the current weights");

            // check if user want to reset the whole weights array or manipulate specific weight of assignment
            if(console.next().equals("Y")){
                myGrades.resetWeights();

                System.out.println("Enter the weights seperated by enter");
                for(int i = 0; i < totalNum; i++){
                    myGrades.setWeights(console.nextInt());
                }
                System.out.println("The weights are: " + Arrays.toString(myGrades.getWeights()) );
            }else{
                boolean exit = false;
                // using while loop to iteratively ask user to manipulate specific weight by its
                // entering order until user satisfied
                while(!exit){
                    System.out.println("Enter the weight and its entering order to manipulate" +
                            " seperated by enter");
                    int weight = console.nextInt();
                    int loc = console.nextInt();
                    myGrades.setWeights(weight,loc);
                    System.out.println("Need to change more? Y\\N");
                    if(console.next().equals("Y")){
                        exit = true;
                    }
                }
            }
        }

        System.out.println("The final grade is: " + myGrades.getGrades());
        System.out.println("The final letter grade is: " + myGrades.getLetters());
    }
}