package Tests;

public class Overflow {
    public static void main(String[] args) {
    byte myByte = 127; 
    System.out.println("My byte is " + myByte);
    
    myByte = -128; 
    System.out.println("My byte is " + myByte);
    myByte--;
    System.out.println("My byte is " + myByte); 
}
}
