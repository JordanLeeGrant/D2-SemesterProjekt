import JumpToBeat.HeapSort;

public class Heaptest {
    public static void main(String[] args) {

        int[] integertestarray = {8,9,3,100,3,1};
        String[] stringtestarray = {"8","9","3","100","3","1"};

        HeapSort.heapSort(integertestarray);
        System.out.println("Test 1");
        for (int number: integertestarray) {

            System.out.println(number);
        }
        System.out.println("Test 2");

        for (int number: HeapSort.convToIntArray(stringtestarray)) {

            System.out.println(number);
        }
    }
}
