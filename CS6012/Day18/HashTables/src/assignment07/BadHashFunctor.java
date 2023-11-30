package assignment07;

import java.util.Random;

public class BadHashFunctor implements HashFunctor{
    @Override
    public int hash(String item) {
        Random random = new Random();

        return random.nextInt(10) + 1;
    }
}
