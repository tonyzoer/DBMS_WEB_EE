package itlab.controller.command;




import itlab.controller.exceptions.NotFoundException;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.RequestWrapper;

import java.util.logging.Logger;

public class DefaultCommandDispatcher implements ICommandDispatcher {
//    private final static Logger logger = Logger.getLogger(String.valueOf(DefaultCommandDispatcher.class));
    private static final DefaultCommandDispatcher instance = new DefaultCommandDispatcher();
    private DefaultCommandDispatcher() {
    }

    public static DefaultCommandDispatcher getInstance() {
        return instance;
    }


    @Override
    public String executeRequest(RequestWrapper requestWrapper) throws RequestAttributeNotPermittedException, NotFoundException {
        String commandName = requestWrapper.getParameter("command");
        try {
            return CommandFactory.valueOf(commandName).getCommand().execute(requestWrapper);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new NotFoundException();
        } catch (Exception exception) {
            throw new NotFoundException();
        }
    }

}
