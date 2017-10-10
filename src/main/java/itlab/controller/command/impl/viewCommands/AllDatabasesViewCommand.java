package itlab.controller.command.impl.viewCommands;

import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.service.controllers.DatabaseControllerDirect;

import java.util.List;

/**
 * Created by mafio on 10.10.2017.
 */
public class AllDatabasesViewCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        List<String> databases=DatabaseControllerDirect.getInstance().getAllDatabases();
        req.setAttribute("databases",databases);
        return null;
    }
}
