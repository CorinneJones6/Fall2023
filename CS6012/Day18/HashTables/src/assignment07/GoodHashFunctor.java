package assignment07;

public class GoodHashFunctor implements HashFunctor{
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
