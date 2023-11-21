package Tests;

public class Precision {
    public static void main(String[] args) {
        float a = 1f; 
        float b = 0f; 
        for (int i = 0; i < 10; i++) {
            b += 0.1f; 
        }
        System.out.println(a + " and " + b); 
    }
}