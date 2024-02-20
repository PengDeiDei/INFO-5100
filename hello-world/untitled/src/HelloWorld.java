/*
 * Name: Haonan Peng
 * Email: peng.haon@northeastern.edu
 *
 * This program allows person to create any greeting message by using the setter method,
 * and also get access to the current greeting message by the getter method.
 */
public class HelloWorld{
    private String greeting = "Hello, World!";
    public void greet(){
        System.out.println(greeting);
    }
    public void setGreet(String msg){
        this.greeting = msg;
    }
    public String getGreet(){
        return this.greeting;
    }
    public static void main(String args[]){
        HelloWorld helloworld;
        helloworld = new HelloWorld();

        String message = helloworld.getGreet();
        System.out.println(message);

        helloworld.setGreet("Hello, northeastern!");
        helloworld.greet();
    }
}