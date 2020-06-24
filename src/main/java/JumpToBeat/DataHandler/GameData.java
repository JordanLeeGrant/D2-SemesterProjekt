package JumpToBeat.DataHandler;

public class GameData {

    String gDtype,gDcontent,gDDate;

    public GameData(String type,String content,String date){
        gDcontent = content;
        gDtype = type;
        gDDate = date;
    }
    public GameData(String type,String content){
        gDcontent = content;
        gDtype = type;
    }
}
