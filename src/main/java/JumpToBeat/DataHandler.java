package JumpToBeat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class DataHandler
{
    List<GameData> DataList;
    int[] highscoreTable;

    public DataHandler()
    {
        highscoreTable = new int[10];
        try
        {
            File highscore = new File("src\\data\\Highscores.txt");

            Scanner Reader = new Scanner(highscore);

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                String[] gameDataList = data.split("|");
                DataList.add(new GameData(gameDataList[0],gameDataList[1]));
            }
            Reader.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: file not found!");
            e.printStackTrace();
        }
    }
    public void updateHighscore(String[] score)
    {
        try
        {

            int poc = 0;
            String[] temparr= new String[highscoreTable.length];
            for (GameData highscore: DataList) {
                if (highscore.gDtype.contains("HiSc"))
                {
                    temparr[poc] = highscore.gDcontent;
                }
            }

            int[] updateScoreArray = HeapSort.convToIntArray(temparr);
            HeapSort.heapSort(updateScoreArray);

            if(highscoreTable[highscoreTable.length-1] == updateScoreArray[highscoreTable.length-1])
                return;

            for(int i =0 ; i< highscoreTable.length;i++)
            {
                highscoreTable[i] = updateScoreArray[i];
            }
        }
        catch(IndexOutOfBoundsException ioobe)
        {
            System.out.println("ERROR: wrong datatype given!");
            ioobe.printStackTrace();
        }
    }
    public void addnewData()
    {

    }
    public void SaveData()
    {
        try {

            FileWriter myWriter = new FileWriter("src\\data\\Save_Data.txt");

            for(GameData item : DataList)
            {
                myWriter.write(item.gDtype+"|"+item.gDcontent+"");
            }
            myWriter.close();

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }
    public int[] getHighscoreTable()
    {
            return highscoreTable;
    }
}
