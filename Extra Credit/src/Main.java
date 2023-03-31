public class Main {
    public static void main(String[] args) {
        RaceEvaluate myEvaluate = new RaceEvaluate();
        myEvaluate.readFile("test.txt");
        myEvaluate.getResult();
    }
}