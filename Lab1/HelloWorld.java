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