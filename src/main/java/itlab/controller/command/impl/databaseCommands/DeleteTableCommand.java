package itlab.controller.command.impl.databaseCommands;

import itlab.controller.command.CommandFactory;
import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.service.controllers.DatabaseControllerDirect;

/**
 * Created by mafio on 10/22/2017.
 */
public class DeleteTableCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        DatabaseControllerDirect.getInstance().removeTable(req.getParameter("database"),req.getParameter("table"));
        DatabaseControllerDirect.getInstance().saveDatabase(req.getParameter("database"));
        return CommandFactory.DATABASE.getCommand().execute(req);
    }
}
