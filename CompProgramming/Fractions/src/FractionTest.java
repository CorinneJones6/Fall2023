import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class FractionTest {

@Test
public void runAllTests(){
    tryCatch();
    testReduce();
    testPlus();
    testMinus();
    testTimes();
    testDividedBy();
    testReciprocal();
    testToDouble();
    testCompareTo();


}

    @Test
    void testCompareTo(){
        ArrayList<Fraction> a1=new ArrayList<Fraction>();

        a1.add(new Fraction(2, 4));
        a1.add(new Fraction (1, 4));
        a1.add(new Fraction (3, 4));
        a1.add(new Fraction (0, 4));

        Collections.sort(a1);

        for (int i=0; i<a1.size(); i++){
            System.out.println(a1.get(i));
        }
    }

    @Test
    void tryCatch(){
    Fraction f1= new Fraction (0,4);
    Fraction f2= new Fraction(1,5);
    try {
        Fraction f3 = new Fraction(4, 0);
    } catch (ArithmeticException e){
        System.out.println(e.getMessage());
    }
    try {
        f1.reciprocal();
    } catch (ArithmeticException e){
        System.out.println(e.getMessage());

    }
    try {
        f2.dividedBy(f1);
    } catch (ArithmeticException e){
        System.out.println(e.getMessage());
    }

}

//This tests reduce and find GCD
    @Test
    void testReduce() {
   Fraction f1=new Fraction(12,30);
   Fraction f2=new Fraction(9,12);
   Fraction f3=new Fraction(-6,-18);
   Fraction f4=new Fraction(24, -108);

   Assertions.assertEquals(f1.toString(), "2/5");
   Assertions.assertEquals(f2.toString(), "3/4");
   Assertions.assertEquals(f3.toString(), "1/3");
   Assertions.assertEquals(f4.toString(), "-2/9");
    }

    @Test
    void testPlus() {
    Fraction f1=new Fraction(-3,4);
    Fraction f2= new Fraction(1,8);
    Fraction f3=f1.plus(f2);
    Fraction f4= new Fraction(1, -5);
    Fraction f5=f1.plus(f4);


    Assertions.assertEquals(f3.toString(), "-5/8");
    Assertions.assertEquals(f5.toString(), "-19/20");
    }

    @Test
    void testMinus() {
        Fraction f1=new Fraction(-3,4);
        Fraction f2= new Fraction(1,8);
        Fraction f3=f1.minus(f2);
        Fraction f4= new Fraction(1, -5);
        Fraction f5=f1.minus(f4);


        Assertions.assertEquals(f3.toString(), "-7/8");
        Assertions.assertEquals(f5.toString(), "-11/20");
    }

    @Test
    void testTimes() {
    Fraction f1= new Fraction (1,2);
    Fraction f2= new Fraction (1, 3);
    Fraction f3= f1.times(f2);

    Assertions.assertEquals(f3.toString(), "1/6");
    }

    @Test
    void testDividedBy() {
        Fraction f1=new Fraction(3,5);
        Fraction f2= new Fraction(1,-5);
        Fraction f3=f1.dividedBy(f2);
        Fraction f4= new Fraction(6, 7);
        Fraction f5=f1.dividedBy(f4);


        Assertions.assertEquals(f3.toString(), "-3/1");
        Assertions.assertEquals(f5.toString(), "7/10");
    }

    @Test
    void testReciprocal() {
        Fraction f1=new Fraction(3,5);
        Fraction f2= new Fraction(1,5);
        Fraction f3= new Fraction(6, 7);

        Assertions.assertEquals(f1.reciprocal().toString(), "5/3");
        Assertions.assertEquals(f2.reciprocal().toString(), "5/1");
        Assertions.assertEquals(f3.reciprocal().toString(), "7/6");
    }
//right now basically everything is testing this!
    @Test
    void testToString() {

    }

    @Test
    void testToDouble() {
        Fraction f1=new Fraction(6,3);
        Fraction f2= new Fraction(1,5);
        Fraction f3= new Fraction(6, 7);

        double test1=f1.toDouble();
        double test2=f2.toDouble();
        double test3=f3.toDouble();

        Assertions.assertEquals(test1, 2.0);
        Assertions.assertEquals(test2, 0.2);
        Assertions.assertEquals(test3, 0.8571428571428571);

    }
}