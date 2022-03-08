package util;

import java.util.Scanner;

public class Input {
    private Scanner in;

    public Input(){
        this.in = new Scanner(System.in);
    }

    public String getString(){
        return in.nextLine();
    }

    public boolean yesNo(){
        System.out.print("y/n: ");
        String userInput = this.getString();
        if(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("YES")){
            return true;
        } else {
            return false;
        }
    }

    // getInt() with parameters
    public int getInt(int min, int max){
        System.out.print("Enter number between " + min + " and " + max + ": ");
        int userInput = 0;
        try {
            userInput = Integer.parseInt(this.getString());
            if(!(userInput >= min && userInput <= max)){
                System.out.println("Out of bounds(" + min + " - " + max + "). Try again.");
                userInput = getInt(min, max);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
            userInput = getInt(min, max);
        }
        return userInput;
    }

    // getInt() without parameters
    public int getInt(){
        System.out.print("Enter whole number: ");
        int userInput = 0;
        try {
            userInput = Integer.parseInt(this.getString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
            userInput = getInt();
        }
        return userInput;
    }

    // getDouble with parameters
    public double getDouble(double min, double max){
        System.out.print("Enter number between " + min + " and " + max + ": ");
        double userInput = 0.0;
        try {
            userInput = Double.parseDouble(this.getString());
            if(!(userInput >= min && userInput <= max)){
                System.out.println("Out of bounds(" + min + " - " + max + "). Try again.");
                userInput = getDouble(min, max);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Try again.");
            userInput = getDouble(min, max);
        }
        return userInput;
    }

    // getDouble without parameters
    public double getDouble(){
        System.out.print("Enter number: ");
        double userInput = 0.0;
        try {
            userInput = Double.parseDouble(this.getString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
            userInput = getDouble();
        }
        return userInput;
    }

    // Accepts binary number in String format and returns equivalent base 10 value
    // Example:
    // IN -> 1010
    // OUT -> 10
    public int getBinary(){
        System.out.print("Enter binary number: ");
        int userInput = 0;
        try {
            userInput = Integer.parseInt(this.getString(), 2);
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Try again.");
            userInput = getBinary();
        }
        return userInput;
    }

    // Accepts hexadecimal number in String format and returns equivalent base 10 value
    // Example:
    // IN -> 10
    // OUT -> 16
    public int getHex(){
        System.out.print("Enter hexadecimal number: ");
        int userInput = 0;
        try {
            userInput = Integer.parseInt(this.getString(), 16);
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Try again.");
            userInput = getBinary();
        }
        return userInput;
    }
}