package assignment07;

public class MediocreHashFunctor implements HashFunctor {
    /**
     * This "Mediocre" Hash Functor follows the formula given by Jerry Coffin on Stack overflow
     * He/She/They probably got it from somewhere else, probably He/She/They did not come up with it themselves
     *
     * @param item - String to hash
     * @return - the hashcode
     */
    @Override
    public int hash(String item) {
        int result = 0x55555555;

        for (char c : item.toCharArray()) {
            result ^= c;
            result = rol(result);
        }

        return result;
    }

    private int rol(int value) {
        return (value << 5) | (value >>> (32 - 5));
    }
}
