public class HelloWorld{
    private String greeting = "Hello, World!";
    public void greet(){
        System.out.println(greeting);
    }

    public void getGreet(String msg){
        this.greeting = msg;
    }
    public static void main(String args[]){
        HelloWorld helloworld;
        helloworld = new HelloWorld();
        helloworld.getGreet("Hello, northeastern!");
        helloworld.greet() ;
    }
}