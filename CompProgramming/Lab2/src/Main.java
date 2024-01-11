// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Hello world!\n");

        ArrayList<Integer>aL=new ArrayList<Integer>();

for (int i=1; i<=10; i++){
    aL.add(i);
}
int count =0;
  Scanner sc = new Scanner(System.in);


        for (int i=0; i<aL.size(); i++) {
            System.out.println( aL.get(i) );
        }

        for (int i=0; i<aL.size(); i++) {
            count+=aL.get(i);
        }

        System.out.println("Sum of the array: "+count);

System.out.print("Please enter your name: ");
String userName =sc.next();

System.out.print("Please enter your age: ");
int intAge = sc.nextInt();

if (intAge<18){
    System.out.println(userName+" is not voting age.");
}
else  {
    System.out.println(userName + " is voting age!!!");
}

if(intAge>=75) {
    System.out.println(userName + " is a part of the greatest generation!");
}
 else if (intAge>55 && intAge<=74){
     System.out.print(userName + " is a part of the boomer generation!");
 }
 else if (intAge>40 && intAge<=55){
     System.out.println(userName + " is a part of the gen X generation!");
 }
else if (intAge>25 && intAge<=40) {
    System.out.println(userName+" is a part of the millennial generation!");
}
else if (intAge<=25) {
    System.out.println(userName+" is a part of the iGen generation!");
}


    }
}