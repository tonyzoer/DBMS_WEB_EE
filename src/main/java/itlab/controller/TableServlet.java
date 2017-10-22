package itlab.controller;


import com.google.gson.Gson;
import itlab.model.exceptions.NonExistingRow;
import itlab.model.exceptions.NonExistingTable;
import itlab.model.exceptions.UnsupportedValueException;
import itlab.service.controllers.DatabaseControllerDirect;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mafio on 19.10.2017.
 */
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String table = req.getParameter("table");
        String database = req.getParameter("database");
        Map<String,Map<String,String>> tableMap =new HashMap<>();
        try {
             tableMap= DatabaseControllerDirect.getInstance().getTableRowsAsMap(database, table);
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
        String json=new Gson().toJson(tableMap);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String table = req.getParameter("table");
        String database = req.getParameter("database");
        Map<String, String> scheme = new HashMap<>();
        try {
            scheme = DatabaseControllerDirect.getInstance().getTableScheme(database, table);
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
        Map<String,String> newRowMap=new HashMap<>();
        for (Map.Entry<String, String> col : scheme.entrySet()) {
            String colVal= req.getParameter(col.getKey());
            newRowMap.put(col.getKey(),colVal);
        }
        String UUIDrow="";
        try {
            UUIDrow= DatabaseControllerDirect.getInstance().addRowToTable(database,table,newRowMap);
        } catch (UnsupportedValueException e) {
            e.printStackTrace();
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
        DatabaseControllerDirect.getInstance().saveDatabase(database);
        Map<String,String> row=new HashMap<>();
        try {
           row= DatabaseControllerDirect.getInstance().getRow(database,table,UUIDrow);
        } catch (NonExistingRow nonExistingRow) {
            nonExistingRow.printStackTrace();
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
        row.put("UUID",UUIDrow);
        String json=new Gson().toJson(row);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
