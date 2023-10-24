public class MyRunnableTwo implements Runnable{

    public int threadIndex;
    public int numThreads;

    public int maxValue;

    MyRunnableTwo(int numThreads, int i, int maxValue){
        this.numThreads=numThreads;
        this.threadIndex=i;
        this.maxValue=maxValue;
    }
    @Override
    public void run() {
        int start = threadIndex * maxValue / numThreads;
        int end = Math.min((threadIndex+1)*maxValue/numThreads, maxValue);
        for(int i = start; i < end; i++){
         Main.answer+=i;
        }
    }
}
