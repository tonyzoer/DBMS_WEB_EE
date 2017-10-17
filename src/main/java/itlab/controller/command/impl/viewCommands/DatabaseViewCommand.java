package itlab.controller.command.impl.viewCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.model.Database;
import itlab.service.controllers.DatabaseControllerDirect;
import itlab.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mafio on 17.10.2017.
 */
public class DatabaseViewCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        List<String> tables=new ArrayList<>();
        tables= DatabaseControllerDirect.getInstance().getAllTables(req.getParameter("name"));
        req.setAttribute("tables",tables);
        return ViewJsp.Database.DATABASE;
    }
}
