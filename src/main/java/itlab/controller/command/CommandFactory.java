package itlab.controller.command;

import itlab.controller.command.impl.databaseCommands.*;
import itlab.controller.command.impl.viewCommands.*;

/**
 * Created by mafio on 09.10.2017.
 */
public enum CommandFactory {
    CREATEDATABASE(new CreateDatabaseCommand()),
    CREATEDATABASEVIEW(new CreateDatabaseViewCommand()),
    ALLDATABASES(new AllDatabasesViewCommand()),
    DATABASE(new DatabaseViewCommand()),
    CREATETABLEVIEW(new CreateTableViewCommand()),
    CREATETABLE(new CreateTableCommand()),
    INTERSECT(new IntersectTablesCommand()),
    SUBSTRACT(new SubstractTablesCommand()),
    TABLEVIEW(new TableViewCommand()),
    DELETEDATABASE(new DeleteTableCommand());


    private ICommand command;
    CommandFactory(ICommand instance) {
        this.command = instance;
    }
    public ICommand getCommand() {
        return command;
    }
}
