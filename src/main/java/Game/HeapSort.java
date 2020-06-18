package Game;

import java.lang.reflect.Array;

public class HeapSort {

    static void makeHeap(int[] preHeapArray, int length, int sortingint) {

        int leftChild = 2*sortingint+1;
        int rightChild = 2*sortingint+2;
        int largest = sortingint;


        if (leftChild < length && preHeapArray[leftChild] > preHeapArray[largest]) {
            largest = leftChild;
        }


        if (rightChild < length && preHeapArray[rightChild] > preHeapArray[largest]) {
            largest = rightChild;
        }

        if (largest != sortingint) {
            int temp = preHeapArray[sortingint];
            preHeapArray[sortingint] = preHeapArray[largest];
            preHeapArray[largest] = temp;
            makeHeap(preHeapArray, length, largest);
        }
    }
    public static int[] heapSort(int[] heapArray) {

        if (heapArray.length == 0) return heapArray;


        int length = heapArray.length;


        for (int i = length / 2-1; i >= 0; i--)
            makeHeap(heapArray, length, i);

        for (int i = length-1; i >= 0; i--) {
            int temp = heapArray[0];
            heapArray[0] = heapArray[i];
            heapArray[i] = temp;

            makeHeap(heapArray, i, 0);
        }
        return heapArray;
    }

    public static int[] convToIntArray(String[] strArray)
    {
        int[] integerArray = new int[strArray.length];

        try
        {
            for (int i = 0 ; i<strArray.length;i++) {
                integerArray[i] = Integer.parseInt(strArray[i]);
            }
        }
        catch(IndexOutOfBoundsException ioobe){

            System.out.println("ERROR! IndexOutOfBoundsException");
            ioobe.printStackTrace();
        }

        return integerArray;
    }
}
