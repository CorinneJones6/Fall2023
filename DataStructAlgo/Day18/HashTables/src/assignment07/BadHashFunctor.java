package assignment07;

import java.util.Random;

public class BadHashFunctor implements HashFunctor {
    /**
     * This "Bad" Hash Functor gives the ascii value of the initial char
     *
     * @param item - String to hash
     * @return - the hashcode
     */
    @Override
    public int hash(String item) {
        return item.charAt(0);
    }
}
