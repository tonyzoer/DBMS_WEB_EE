package itlab.controller.command.impl.viewCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.model.exceptions.NonExistingTable;
import itlab.service.controllers.DatabaseController;
import itlab.service.controllers.DatabaseControllerDirect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mafio on 18.10.2017.
 */
public class TableViewCommand implements ICommand{

    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
//        Map<String,Map<String,String>> table=new HashMap<>();
        Map<String,String> scheme =new HashMap<>();
        String tableStr=req.getParameter("table");
        String databaseStr=req.getParameter("database");
//        try {
//            table=DatabaseControllerDirect.getInstance().getTableRowsAsMap(databaseStr,tableStr);
//        } catch (NonExistingTable nonExistingTable) {
//            nonExistingTable.printStackTrace();
//        }
        try {
            scheme=DatabaseControllerDirect.getInstance().getTableScheme(databaseStr,tableStr);
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
//        Map<String,String> rows=new HashMap<>();
//        try {
//          rows=DatabaseControllerDirect.getInstance().getTableRows(databaseStr,tableStr);
//        } catch (NonExistingTable nonExistingTable) {
//            nonExistingTable.printStackTrace();
//        }
//        String table= null;
//        try {
//            table = DatabaseControllerDirect.getInstance().tableToString(databaseStr,tableStr);
//        } catch (NonExistingTable nonExistingTable) {
//            nonExistingTable.printStackTrace();
//        }
//        req.setAttribute("table",table);
//        req.setAttribute("rows",rows);
        req.setAttribute("scheme",scheme);
        req.setAttribute("databaseStr",databaseStr);
        req.setAttribute("tableStr",tableStr);
        req.setAttribute("time",System.currentTimeMillis());
        return ViewJsp.Database.TABLE;
    }
}
