package itlab;


import itlab.model.Database;
import itlab.model.Scheme;
import itlab.model.Table;
import itlab.model.exceptions.NonExistingTable;
import itlab.model.exceptions.TableAlreadyExsists;
import itlab.model.exceptions.UnsupportedValueException;
import itlab.model.types.Types;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws UnsupportedValueException, TableAlreadyExsists, NonExistingTable {
        load();
    }
    private static void load(){
        Database database = new Database("TestDB");
        database.load();
        Map<String,Table> tables =database.getTables();
        Table tb=tables.get("first table");
        System.out.println(tb.getRows());
    }
    private static void save() throws TableAlreadyExsists, UnsupportedValueException, NonExistingTable {
        Database database=new Database("TestDB");
        HashMap<String,Types> schemeMap=new HashMap<>();
        schemeMap.put("id",Types.INTEGER);
        schemeMap.put("user",Types.STRING);
        String currtableUuid= database.createTable("first table",new Scheme(schemeMap));
        Table t= database.getTable(currtableUuid);
        HashMap<String,String> columnValue=new HashMap<>();
        columnValue.put("id","1");
        columnValue.put("user","SUPERUSER");
        String rowUuid = t.addRow(columnValue);
        System.out.println(t.getRow(rowUuid));
        database.save();
    }
    private static void delete(){
        Database database = new Database("TestDB");
        database.delete();
    }
}
