package assignment07;

public class MediocreHashFunctor implements HashFunctor {
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
