/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This file contains a class constructor and all getter and setter methods for
 * WeightedGrades class
 */
public class WeightedGrades {
    private int[] totalPoints;
    private double[] earnedPoints;
    private double[] weights;
    private double grade;

    /*
     * Class constructor
     * @params: int totalPoint
     *          double earnedPoint
     *          double weight
     */
    public WeightedGrades(int[] totalPoints, double[] earnedPoints, double[] weights){
        this.totalPoints = totalPoints;
        this.earnedPoints = earnedPoints;
        this.weights = weights;

        calGrade();
    }

    /*
     * @brief: method to calculate the grade in double
     */
    private void calGrade(){
        double current = 0;
        for(int i = 0; i < totalPoints.length; i++){
            current += earnedPoints[i]/totalPoints[i]*weights[i];
        }
        this.grade = current;
    }

    /*
     * @brief : method to get totalPoints array
     *
     * @return: int[] totalPoints
     */
    public int[] getTotalPoints(){return this.totalPoints;}

    /*
     * @brief: method to get earnedPoints array
     *
     * @return: double[] earnedPoint
     */
    public double[] getEarnedPoints(){return this.earnedPoints;}

    /*
     * @brief: method to get weights array
     *
     * @return: return double[] weights
     */
    public double[] getWeights(){return this.weights;}

    /*
     * @brief: method to get weighted grade
     *
     * @return: return double grade
     */
    public double getGrade(){return grade;}

    /*
     * @brief: method to return final letter grade
     * by comparing weighted grade
     */
    public char getLetter(){
        // nested if-else statements to return letter grade
        if(this.grade >=90){
            return 'A';
        }else if(this.grade >= 80){
            return 'B';
        }else if(this.grade >= 70){
            return 'C';
        }else if(this.grade >= 60){
            return 'D';
        }else{
            return 'F';
        }
    }

    /*
     * @brief: method to set totalPoints array
     *
     * @params: int[] totalPoints
     */
    public void setTotalPoints(int[] totalPoints){
        this.totalPoints = totalPoints;
        // update the grade once the totalPoints array changed
        calGrade();
    }

    /*
     * @brief: method to set earnedPoints array
     *
     * @param: double[] earnedPoint
     */
    public void setEarnedPoints(double[] earnedPoints){
        this.earnedPoints = earnedPoints;
        // update the grade once the earnedPoints array changed
        calGrade();
    }

    /*
     * @brief: method to set weights array
     *
     * @params: double[] weight
     */
    public void setWeights(double[] weights){
        this.weights =  weights;
        // update the grade once the weights array changed
        calGrade();
    }
}