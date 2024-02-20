/*
 * author: Haonan Peng
 * email: peng.haon@northeastern.edu
 *
 * This file contains a class constructor and all getter and setter methods for
 * WeightedGrades class
 */
public class WeightedGrades {
    private int totalPoint = 0;
    private int earnedPoint = 0;
    private int weight = 0;

    private double grade = 0.0;

    /*
     * Class constructor
     * @params: int totalPoint
     *          int earnedPoint
     *          double weight
     */
    public WeightedGrades(int totalPoint, int earnedPoint, int weight){
        this.totalPoint = totalPoint;
        this.earnedPoint = earnedPoint;
        this.weight = weight;
        this.grade = (double) this.earnedPoint / this.totalPoint * this.weight;
    }

    /*
     * @brief : method to get totalPoint
     *
     * @return: int totalPoint
     */
    public int getTotalPoint(){
        return this.totalPoint;
    }

    /*
     * @brief: method to get earnedPoint
     *
     * @return: int earnedPoint
     */
    public int getEarnedPoint(){
        return this.earnedPoint;
    }

    /*
     * @brief: method to get weight
     *
     * @return: return double weight
     */
    public double getWeight(){
        return this.weight;
    }

    /*
     * @brief: method to get weighted grade
     *
     * @return: return double totalGrade
     */
    public double getGrade(){
        this.grade = (double) this.earnedPoint / this.totalPoint * this.weight;
        return grade;
    }

    /*
     * @brief: method to set totalPoint
     *
     * @params: int totalPoint
     */
    public void setTotalPoint(int totalPoint){
        this.totalPoint = totalPoint;
    }

    /*
     * @brief: method to set earnedPoint
     *
     * @param: int earnedPoint
     */
    public void setEarnedPoint(int earnedPoint){
        this.earnedPoint = earnedPoint;
    }

    /*
     * @brief: method to set weight
     *
     * @params: double weight
     */
    public void setWeight(int weight){
        this.weight =  weight;
    }
}