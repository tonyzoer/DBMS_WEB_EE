package itlab.controller.command.impl.databaseCommands;

import itlab.controller.command.CommandFactory;
import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.model.exceptions.TableAlreadyExsists;
import itlab.service.controllers.DatabaseController;
import itlab.service.controllers.DatabaseControllerDirect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mafio on 17.10.2017.
 */
public class CreateTableCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        Map<String,String> scheme=new HashMap<>();
        for (int i = 0; i <Integer.parseInt(req.getParameter("count")); i++) {
            scheme.put(req.getParameter("columnName"+i),req.getParameter("type"+i).toUpperCase());
        }
        try {
            DatabaseControllerDirect.getInstance().addTable(req.getParameter("database"),req.getParameter("tableName"),scheme);
            DatabaseControllerDirect.getInstance().saveDatabase(req.getParameter("database"));
        } catch (TableAlreadyExsists tableAlreadyExsists) {
            tableAlreadyExsists.printStackTrace();
        }
//        req.addParameter("name",req.getParameter("curdb"));
//        req.setAttribute("name",req.getParameter("curdb"));
        return CommandFactory.DATABASE.getCommand().execute(req);
    }
}
