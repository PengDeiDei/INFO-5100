/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This is the tester file for WeightedGrades class
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter the total point as an integer.");
        int totPoint = myObj.nextInt();

        System.out.println("Please enter the earned point as an integer");
        int earnPoint = myObj.nextInt();

        System.out.println("Please enter the percentage weight as an integer");
        int weight = myObj.nextInt();

        WeightedGrades myGrade = new WeightedGrades(totPoint, earnPoint, weight);

        System.out.println("Total grade is: "+myGrade.getTotalPoint());
        System.out.println("Earned grade is: "+myGrade.getEarnedPoint());
        System.out.println("Percentage weight is: "+myGrade.getWeight()+"%");
        System.out.printf("Total grade is: %.3f", myGrade.getGrade());

    }
}