/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This file contains a class constructor and all getter and setter methods for
 * WeightedGrades class
 */

public class WeightedGrades {
    private int[] totalPoints; // int array to store total points of assignments
    private double[] earnedPoints; // double array to store earned points of assignments
    private int[] weights; // int array to store weights of all assignments
    private int totalNum; // total number of assignments
    private int totalIdx = 0; // index to track adding elements into totalPoints array
    private int earnedIdx = 0; // index to track adding elements into earnedPoints array
    private int weightIdx = 0; // index to track adding element into weights array
    private double grade;

    /*
     * @brief: class constructor - get the total number of assignments and
     * initialize three array lists to store total points, total earned points,
     * and assignment weights
     *
     * @param: int size
     */
    public WeightedGrades(int totalNum){
        this.totalNum = totalNum;
        this.totalPoints = new int[totalNum];
        this.earnedPoints = new double[totalNum];
        this.weights = new int[totalNum];
    }

    /*
     * @brief: getter method to calculate the grade in double
     */
    private void calGrade(){
        double current = 0;
        for(int i = 0; i < totalNum; i++){
            current += totalPoints[i]*earnedPoints[i]/weights[i];
        }
        this.grade = current;
    }

    /*
     * @brief: getter method to return final total grade
     *
     * @return: double grade
     */
    public double getGrades(){
        calGrade();
        return this.grade;
    }
    /*
     * @brief: getter method to return final letter grade by
     * comparing weighted grade to the range of grade division
     */
    public char getLetters(){
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
     * @brief: getter method to return the integer array list of total points
     *
     * @return ArrayList<Integer> totalPoints
     */
    public int[] getTotalPoints(){return this.totalPoints;}

    /*
     * @brief: getter method to return the double array list of earned points
     *
     * @return: ArrayList<Integer> earnedPoints
     */
    public double[] getEarnedPoints(){return this.earnedPoints;}

    /*
     * @brief: getter method to return the integer array list of weights
     *
     * @return: ArrayList<Integer> weights
     */
    public int[] getWeights(){return this.weights;}

    /*
     * @brief: setter method to set the total points of assignments by
     * adding one element to the array list per time.
     *
     * @param: int point
     */
    public void setTotalPoints(int point){
        if (this.totalIdx!=this.totalNum) this.totalPoints[this.totalIdx++] = point;
    }

    /*
     * @brief: overloading setter method to set the point of the specific assignment
     * by its entering order, e.g. if user want to change the point of 6th assignment
     * out of 8 assignments, call method such as: setTotalPoints(point, 6);.
     *
     * @ param: int point
     *          int order
     */
    public void setTotalPoints(int point, int order){this.totalPoints[order-1] = point;}

    /*
     * @brief: setter method to set the weights of assignments by adding
     * one element to the array list per time.
     *
     * @param: int weight
     */
    public void setWeights(int weight){
        if(this.weightIdx!=this.totalNum) this.weights[weightIdx++] = weight;
    }

    /*
     * @brief: overloading setter method to set the weight of a specific assignment
     * by its entering order, e.g. if user want to change the weight of 6th assignment
     * out of 8 assignments, call method such as: setWeights(weight, 6);.
     *
     * @ param: int weight
     *          int order
     */
    public void setWeights(int weight, int order){this.weights[order-1] = weight;}

    /*
     * @brief: setter method to set the earned points of assignments by
     * adding one element to the array list per time.
     *
     * @param: double earned
     */
    public void setEarnedPoints(double earned){if(earnedIdx != totalNum) this.earnedPoints[earnedIdx++] = earned;}

    /*
     * @brief: overloading setter method to set the earned point of the specific assignment
     * by its entering order, e.g. if user want to change the earned point of 6th assignment
     * out of 8 assignments, call method such as: setEarnedPoints(earned, 6);.
     *
     * @ param: double earned
     *          int order
     */
    public void setEarnedPoints(double earned, int order){this.earnedPoints[order-1] = earned;}

    /*
     * @brief: method to check if weights of all assignments can be summed up to 100
     *
     * @return: true, if sum = 100;
     *          false, otherwise.
     */
    public boolean checkWeights(){
        int sum = 0;

        for(int weight: this.weights){
            sum += weight;
        }

        return sum == 100;
    }

    /*
     * @brief: setter method to re-initialize the total points array and its index counter
     */
    public void resetTotalPoints(){
        this.totalPoints = new int[this.totalNum];
        this.totalIdx = 0;
    }

    /*
     * @brief: setter method to re-initialize the earned points array and its index counter
     */
    public void resetEarnedPoints(){
        this.earnedPoints = new double[this.totalNum];
        this.earnedIdx = 0;
    }

    /*
     * @brief: setter method to re-initialize the weights array and its index counter
     */
    public void resetWeights(){
        this.weights = new int[this.totalNum];
        this.weightIdx = 0;
    }
}