package lab05;

import java.util.Arrays;
import java.util.Comparator;

public class MedianArray<E> {

   public static <E extends Comparable<? super E>> E findMedian(E[]arr){
        Arrays.sort(arr);
        int mid=(arr.length-1)/2;
        return arr[mid];
    }

    public static <E> E findMedianWithComparator(E[] arr, Comparator<E> comparator) {
        Arrays.sort(arr, comparator);
        int mid= (arr.length-1) / 2;
        return arr[mid];
    }



}
