public class HelloWorld{
    private String greeting = "Hello, World!";
    public void greet(String msg){
        greeting = msg;
        System.out.println(greeting);
    }

    public static void main(String args[]){
        HelloWorld helloworld;
        helloworld = new HelloWorld();
        helloworld.greet("Hello, there!") ;
    }
}