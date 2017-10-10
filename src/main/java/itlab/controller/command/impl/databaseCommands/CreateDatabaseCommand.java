package itlab.controller.command.impl.databaseCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.ViewJsp;
import itlab.service.controllers.DatabaseController;
import itlab.service.controllers.DatabaseControllerDirect;

/**
 * Created by mafio on 09.10.2017.
 */
public class CreateDatabaseCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
            DatabaseControllerDirect.getInstance().createDatabase(req.getParameter("name"));

        return ViewJsp.Database.DATABASE;
    }
}
