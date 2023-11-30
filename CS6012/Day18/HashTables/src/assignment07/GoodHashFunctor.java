package assignment07;

public class GoodHashFunctor implements HashFunctor {
    /**
     * This "Good" Hash Functor follows the formula for djb2 by Dan Bernstein
     *
     * @param item - String to hash
     * @return - the hashcode
     */
    @Override
    public int hash(String item) {

        long hash = 5381;

        for (int i = 0; i < item.length(); i++) {
            char c = item.charAt(i);
            hash = ((hash << 5) + hash) + c; // hash * 33 + c
        }

        return (int) hash;
    }
}
