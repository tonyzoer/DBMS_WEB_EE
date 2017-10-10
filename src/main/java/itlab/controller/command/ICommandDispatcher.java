package itlab.controller.command;


import itlab.controller.exceptions.NotFoundException;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;

public interface ICommandDispatcher {
    String executeRequest(RequestWrapper requestWrapper) throws RequestAttributeNotPermittedException, NotFoundException;
}
