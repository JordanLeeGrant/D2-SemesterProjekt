package JumpToBeat.DataHandler;

import com.sun.javafx.beans.event.AbstractNotifyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.*;


public class DataHandler {
    List<GameData> DataList = new ArrayList<GameData>();
    int[] highscoreTable;
    String path = "src\\main\\resources\\assets\\data\\Save_Data.txt";

    public DataHandler() {
        highscoreTable = new int[10];
        updateHighscore();
    }

    public void updateHighscore() {
        try {
            File highscore = new File(path);

            Scanner Reader = new Scanner(highscore);

            // getting data and split it to work with later
            while (Reader.hasNextLine()) {

                String data = Reader.nextLine();
                if (data.isEmpty() == false) {
                    String[] gameDataList = data.split("\\|");
                    GameData alphabet = new GameData(gameDataList[0], gameDataList[1]);
                    this.DataList.add(alphabet);
                }
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: file not found!");
            e.printStackTrace();
        }
        try {

            String[] temparr = new String[DataList.size()];
            int count = 0;
            //getting the Highscores from th Data Table
            for (GameData highscore : DataList) {
                if (highscore.gDtype.contains("HiSc")) {
                    temparr[count] = highscore.gDcontent;
                    count++;
                }

            }


            int[] updateScoreArray = HeapSort.convToIntArray(temparr);
            HeapSort.heapSort(updateScoreArray);
            updateScoreArray = Reverse(updateScoreArray);
            if (highscoreTable[0] == 0) {
                for (int i = 0; i < highscoreTable.length; i++) {
                    highscoreTable[i] = updateScoreArray[i];
                }
            }
            if (DetectChange(highscoreTable, 0, highscoreTable.length - 1, updateScoreArray[highscoreTable.length - 1]) == -1) {
                return;
            } else {

                for (int i = 0; i < highscoreTable.length; i++) {
                    highscoreTable[i] = updateScoreArray[i];
                }
            }
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR!");
            ioobe.printStackTrace();
        }
    }

    public void addnewData(String dataType, String datacontent) {
        DataList.add(new GameData(dataType, datacontent));
    }

    public void SaveData() {
        try {

            FileWriter myWriter = new FileWriter(path);

            for (GameData item : DataList) {
                myWriter.write(item.gDtype + "|" + item.gDcontent + "\n");
            }
            myWriter.close();

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }

    public int[] getHighscoreTable() {
        return highscoreTable;
    }

    public int[] Reverse(int[] Heaparray) {
        int[] reverseArray = new int[Heaparray.length];
        for (int i = 0; i < Heaparray.length; i++) {
            reverseArray[i] = Heaparray[Heaparray.length - (i + 1)];
        }
        return reverseArray;
    }

    public int DetectChange(int[] arrayToBeChecked, int leftrange, int rightrange, int searchobj) {
        int checker = rightrange / 2;
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
