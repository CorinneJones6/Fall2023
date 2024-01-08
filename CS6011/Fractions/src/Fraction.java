//Corinne Jones
//Fractions
public class Fraction implements Comparable<Fraction> {
//    public static void main(String[] args) {
    private long numerator_, denominator_;

    private long findGCD() {
        long gcd=numerator_;
        long remainder = denominator_;
        while (remainder != 0) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }

    private void reduce () {
        long gcd_= findGCD();
        numerator_/= (gcd_);
        denominator_/= (gcd_);
    }
    //This is a constructor when no given values.
    public Fraction(){
        numerator_=0;
        denominator_=1;
    }
    //This is a constructor when values are given.
    public Fraction(long n, long d){
        if (d==0){
            throw new ArithmeticException("division by 0 invalid");
        }
          long absvaln = Math.abs(n);
          long absvald = Math.abs(d);
          if (n < 0 && d < 0) {
              numerator_ = absvaln;
              denominator_ = absvald;
          } else if (n < 0 || d < 0) {
              numerator_ = (absvaln * (-1));
              denominator_ = absvald;
          } else {
              numerator_ = n;
              denominator_ = d;
          }
          reduce();
    }
    public Fraction plus(Fraction rhs){
        long resultNum, resultDen;
        if(denominator_==rhs.denominator_){
            resultDen=denominator_;
            resultNum=numerator_+rhs.numerator_;
        }
        else{
            resultDen=denominator_*rhs.denominator_;
            resultNum=(numerator_*rhs.denominator_)+(rhs.numerator_*denominator_);
        }
        return new Fraction(resultNum, resultDen);
    }
    public Fraction minus (Fraction rhs) {
        long resultNum, resultDen;
        if(denominator_==rhs.denominator_){
            resultDen=denominator_;
            resultNum=numerator_-rhs.numerator_;
        }
        else{
            resultDen=denominator_*rhs.denominator_;
            resultNum=(numerator_*rhs.denominator_)-(rhs.numerator_*denominator_);
        }
        return new Fraction(resultNum, resultDen);
    }
    public Fraction times (Fraction rhs){
        long resultNum, resultDen;
        resultDen=denominator_*rhs.denominator_;
        resultNum=numerator_*rhs.numerator_;
        return new Fraction(resultNum, resultDen);
    }
    public Fraction dividedBy (Fraction rhs){
        long resultNum, resultDen;
        if (rhs.numerator_==0){
            throw new ArithmeticException("division by 0 invalid");
        }
        Fraction r=new Fraction(rhs.denominator_,rhs.numerator_);
        resultDen=denominator_*r.denominator_;
        resultNum=numerator_*r.numerator_;

        return new Fraction(resultNum, resultDen);
    }
    public Fraction reciprocal(){
        if (numerator_==0){
            throw new ArithmeticException("division by 0 invalid");
        }
        Fraction r=new Fraction(denominator_, numerator_);
        return r;
    }

    public String toString() {
        String n=Long.toString(numerator_);
        String d=Long.toString(denominator_);
        return n+"/"+d;
    }
    double toDouble (){
        double n =numerator_;
        double d =denominator_;
       return n/d;
    }

    @Override
    public int compareTo(Fraction rhs) {

        if ((minus(rhs)).toDouble()>=0){
            return 1;
        }
        else if((minus(rhs)).toDouble()<=0){
            return -1;
        }
        return 0;
    }
}




