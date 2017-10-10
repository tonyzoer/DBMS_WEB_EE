package itlab.service.helpers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper {
    public static List<String> getAllSavedDatabases(){
        List<String> dbs=new LinkedList<>();
        File folder=new File("database");
        for (File fileEntry:folder.listFiles()
             ) {
            if (fileEntry.isFile()){
                if (fileEntry.getName().split(".")[fileEntry.getName().split(".").length-1]=="db"){
                    dbs.add(fileEntry.getName());
                }
            }
        }
        return dbs;
    }


}
