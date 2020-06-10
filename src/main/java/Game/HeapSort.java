package Game;

import java.lang.reflect.Array;

public class HeapSort {

    static void makeHeap(int[] array, int length, int i) {

        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largest = i;


        if (leftChild < length && array[leftChild] > array[largest]) {
            largest = leftChild;
        }


        if (rightChild < length && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            makeHeap(array, length, largest);
        }
    }
    public static int[] heapSort(int[] array) {

        if (array.length == 0) return array;


        int length = array.length;


        for (int i = length / 2-1; i >= 0; i--)
            makeHeap(array, length, i);

        for (int i = length-1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            makeHeap(array, i, 0);
        }
        return array;
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
