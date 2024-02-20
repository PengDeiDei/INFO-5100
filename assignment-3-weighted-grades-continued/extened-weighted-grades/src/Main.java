/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This is the tester file for WeightedGrades class
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("How many assignments in total?");
        int len = myObj.nextInt();

        int[] totalP = new int[len]; // [20, 40, 10, 50, 200, 300, 40, 80]
        double[] earnP = new double[len]; // [18, 30, 8, 45, 190, 230, 35, 65]
        double[] ws = new double[len]; // [5, 10, 5, 15, 20, 30, 5, 10]

        System.out.println("Please enter the total points seperated by enter");
        for(int i = 0; i < len; i++){
            totalP[i] = myObj.nextInt();
        }

        System.out.println("Please enter the earned point seperated by enter");
        for(int i = 0; i < len; i++){
            earnP[i] = myObj.nextInt();}

        System.out.println("Please enter the percentage weight seperated by enter");
        for(int i = 0; i < len; i++){
            ws[i] = myObj.nextInt();
        }

        WeightedGrades myGrade = new WeightedGrades(totalP, earnP, ws);

        System.out.println("Total points are: " + Arrays.toString(myGrade.getTotalPoints()));
        System.out.println("Earned points are: " + Arrays.toString(myGrade.getEarnedPoints()));
        System.out.println("Percentage weight is: " + Arrays.toString(myGrade.getWeights()));
        System.out.printf("Total grade is: %.2f %n", myGrade.getGrade());
        System.out.println("The letter grade is: " + myGrade.getLetter()+"\n");

        int[] totals = {20, 30, 40, 50, 60, 100, 200, 300};
        double[] weights = {10, 10, 10, 10, 10, 10, 15, 25};
        double[] earneds = {18, 27, 36, 40, 58, 95,180,295};

        myGrade.setTotalPoints(totals);
        System.out.println("Total points are: " + Arrays.toString(myGrade.getTotalPoints()));
        System.out.printf("Total grade is: %.2f %n", myGrade.getGrade());
        System.out.println("The letter grade is: " + myGrade.getLetter()+"\n");

        myGrade.setEarnedPoints(earneds);
        System.out.println("Earned points are: " + Arrays.toString(myGrade.getEarnedPoints()));
        System.out.printf("Total grade is: %.2f %n", myGrade.getGrade());
        System.out.println("The letter grade is: " + myGrade.getLetter()+"\n");

        myGrade.setWeights(weights);
        System.out.println("Percentage weight is: " + Arrays.toString(myGrade.getWeights()));
        System.out.printf("Total grade is: %.2f %n", myGrade.getGrade());
        System.out.println("The letter grade is: " + myGrade.getLetter());
    }


}