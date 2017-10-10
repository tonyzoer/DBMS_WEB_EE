package itlab.controller.command;


import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;

public interface ICommand {
    String execute(RequestWrapper req) throws RequestAttributeNotPermittedException;
}
