public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int count=0; count<100; count++){
            System.out.print("Hello from thread #" + Thread.currentThread().threadId()+": "+count+" ");

            if ((count + 1) % 10 == 0) {
                // Print a new line every 10 times
                System.out.println();
            }
        }
    }
}
