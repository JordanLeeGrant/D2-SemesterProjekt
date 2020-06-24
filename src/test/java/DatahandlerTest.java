import JumpToBeat.DataHandler.DataHandler;
import JumpToBeat.DataHandler.HeapSort;

public class DatahandlerTest {

    private static DataHandler handler;
    private static JumpToBeat.DataHandler.HeapSort HeapSort;

    public static void main(String[] args) {
     handler = new DataHandler();
       // Test();
       // Test2();
       //Searchtest();
        hiscoretest();
    }
    public static void hiscoretest()
    {
        int[]testarray = handler.getHighscoreTable();
        for (int number:testarray)
        {
            System.out.println(number);
        }
    }
    public static void Test()
    {
        int[] integertestarray = {8,9,3,100,3,1};
        String[] stringtestarray = {"8","9","3","100","3","1"};
        HeapSort.heapSort(integertestarray);
        System.out.println("Test 1");
        for (int number: integertestarray) {
            System.out.println(number);
        }
        System.out.println("Test 2");

        for (int number: HeapSort.heapSort( HeapSort.convToIntArray(stringtestarray))) {
            System.out.println(number);
        }
    }
    public static void Test2()
    {
        System.out.println("Test 3");
        System.out.println("Getting Data");
        handler = new DataHandler();
        System.out.println("fetching Table");
        int[] highscores = handler.getHighscoreTable();
        for (int number: highscores) {

            System.out.println(number);
        }
        System.out.println("updating Table");
        handler.updateHighscore();
        System.out.println("printing new Table");
        for (int number: highscores) {

            System.out.println(number);
        }
    }
    public static void Searchtest()
    {
        int[] integertestarray = {8,9,3,100,3,1,6};

        integertestarray = HeapSort.heapSort(integertestarray);
        System.out.print("Array contet : ");
        for (int i=0;i<=integertestarray.length-1;i++){
            System.out.print(integertestarray[i]+" ");
        }
        System.out.println();
        System.out.println("searching for a number that is in the array on the right side");
        System.out.println(handler.DetectChange(integertestarray,0,integertestarray.length-1,1));
        System.out.println("searching for a number that is in the array on the left side");
        System.out.println(handler.DetectChange(integertestarray,0,integertestarray.length-1,8));
        System.out.println("searching for a number that is in the middle of the array");
        System.out.println(handler.DetectChange(integertestarray,0,integertestarray.length-1,100));
        System.out.println("searching for a number that is not in the array");
        System.out.println(handler.DetectChange(integertestarray,0,integertestarray.length-1,7));
    }
    public static int  DetectChange(int[] arrayToBeChecked,int leftrange,int rightrange,int searchobj)
    {
        int checker = rightrange/2;
        if (rightrange >= leftrange) {
            int mid = leftrange + (rightrange - leftrange) / 2;

            if (arrayToBeChecked[mid] == searchobj) {
                return mid;
            }

            if (arrayToBeChecked[mid] > searchobj)
                return DetectChange(arrayToBeChecked, leftrange, mid - 1, searchobj);

            return DetectChange(arrayToBeChecked, mid + 1, rightrange, searchobj);
        }
        return -1;
    }
}
