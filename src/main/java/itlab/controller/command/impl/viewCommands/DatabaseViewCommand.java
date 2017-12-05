package itlab.controller.command.impl.viewCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.service.controllers.DatabaseControllerDirect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mafio on 17.10.2017.
 */
public class DatabaseViewCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        DatabaseControllerDirect.getInstance().loadDatabase(req.getParameter("database"));
        List<String> tables=new ArrayList<>();
        tables= DatabaseControllerDirect.getInstance().getAllTables(req.getParameter("database"));
        req.setAttribute("tables",tables);
        req.setAttribute("database",req.getParameter("database"));
        return ViewJsp.Database.DATABASE;
    }
}
