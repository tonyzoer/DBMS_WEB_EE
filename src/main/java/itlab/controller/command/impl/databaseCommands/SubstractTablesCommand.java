package itlab.controller.command.impl.databaseCommands;

import itlab.controller.command.CommandFactory;
import itlab.controller.command.ICommand;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;
import itlab.model.exceptions.NonExistingTable;
import itlab.model.exceptions.TableAlreadyExsists;
import itlab.service.controllers.DatabaseControllerDirect;

/**
 * Created by mafio on 18.10.2017.
 */
public class SubstractTablesCommand implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws RequestAttributeNotPermittedException {
        try {
            DatabaseControllerDirect.getInstance().tableDifference(req.getParameter("database"),
                    req.getParameter("table0"),
                    req.getParameter("table1"),
                    req.getParameter("newtable"));
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        } catch (TableAlreadyExsists tableAlreadyExsists) {
            tableAlreadyExsists.printStackTrace();
        }
        DatabaseControllerDirect.getInstance().saveDatabase(req.getParameter("database"));
        return CommandFactory.DATABASE.getCommand().execute(req);
    }
}
