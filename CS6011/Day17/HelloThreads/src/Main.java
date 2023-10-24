import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.




public class Main {

    static int answer = 0;
    private static void sayHello() {
//This function does not run everything in order. It would if you were to add the join method within the for loop.
        MyRunnable runnable = new MyRunnable();
        ArrayList<Thread> al = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
            al.add(t);
        }

        for (Thread thread : al) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.print("ERROR");
            }
        }
    }

    private static void badSum() throws InterruptedException {
        int numThreads=10;
        int maxValue = 100;

        ArrayList<Thread> al = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            MyRunnableTwo runnable = new MyRunnableTwo(numThreads, i, maxValue);
            Thread t = new Thread(runnable);
            t.start();
            al.add(t);
            t.join();

        }
        System.out.println(answer);
        System.out.println((maxValue * (maxValue - 1) / 2));
    }

    public static void main(String[] args) throws InterruptedException {

        sayHello();
        badSum();

    }
}