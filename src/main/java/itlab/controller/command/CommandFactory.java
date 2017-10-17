package itlab.controller.command;

import itlab.controller.command.impl.databaseCommands.CreateDatabaseCommand;
import itlab.controller.command.impl.viewCommands.AllDatabasesViewCommand;
import itlab.controller.command.impl.viewCommands.CreateDatabaseViewCommand;
import itlab.controller.command.impl.viewCommands.DatabaseViewCommand;

/**
 * Created by mafio on 09.10.2017.
 */
public enum CommandFactory {
    CREATEDATABASE(new CreateDatabaseCommand()),
    CREATEDATABASEVIEW(new CreateDatabaseViewCommand()),
    ALLDATABASES(new AllDatabasesViewCommand()),
    DATABASE(new DatabaseViewCommand());


    private ICommand command;
    CommandFactory(ICommand instance) {
        this.command = instance;
    }
    public ICommand getCommand() {
        return command;
    }
}
