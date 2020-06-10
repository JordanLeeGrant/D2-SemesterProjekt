package Game;
import com.sun.javafx.scene.layout.region.Margins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;


public class DataHandler
{
    List<String> DataList;
    int[] highscoreTable;

    public DataHandler()
    {
        try
        {
            File highscore = new File("src\\data\\Highscores.txt");

            Scanner Reader = new Scanner(highscore);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                DataList.add(data);
            }
            Reader.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: file not found!");
            e.printStackTrace();
        }
    }
    public void addNewHighscore(String[] score)
    {
        try
        {
            String[] temparr =DataList.get(1).split("_");
            int[] updatescorearray = HeapSort.convToIntArray(temparr);
            updatescorearray = HeapSort.heapSort(updatescorearray);

            if(highscoreTable[highscoreTable.length-1] == updatescorearray[highscoreTable.length-1])
                return;

            for(int i =0 ; i< highscoreTable.length;i++)
            {
                highscoreTable[i] = updatescorearray[i];
            }
        }
        catch(IndexOutOfBoundsException ioobe)
        {
            System.out.println("ERROR: wrong datatype given!");
            ioobe.printStackTrace();
        }
    }
    public void SaveData()
    {
        try {

            FileWriter myWriter = new FileWriter("src\\data\\Highscores.txt");

            for(String item : DataList)
            {
                myWriter.write(item);
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
