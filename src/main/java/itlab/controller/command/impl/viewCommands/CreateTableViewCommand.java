package itlab.controller.command.impl.viewCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;

/**
 * Created by mafio on 17.10.2017.
 */
public class CreateTableViewCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        req.setAttribute("curdb",req.getParameter("curdb"));
        return ViewJsp.Database.CREATETABLE;
    }
}
