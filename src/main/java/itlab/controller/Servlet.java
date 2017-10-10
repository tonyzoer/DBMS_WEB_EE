package itlab.controller;

import itlab.controller.command.DefaultCommandDispatcher;
import itlab.controller.command.ICommandDispatcher;
import itlab.controller.exceptions.InsufficientPermissionsException;
import itlab.controller.exceptions.NotFoundException;
import itlab.controller.exceptions.RequestAttributeNotPermittedException;
import itlab.controller.util.JspMessagesSetter;
import itlab.controller.util.RequestWrapper;
import itlab.controller.util.impl.RequestWrapperImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mafio on 09.10.2017.
 */
public class Servlet extends HttpServlet {
    private final transient ICommandDispatcher commandDispatcher = DefaultCommandDispatcher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);

    }

    private void parseRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspUrl = null;
        RequestWrapper requestWrapper = new RequestWrapperImpl(req);
        try {
            jspUrl = commandDispatcher.executeRequest(requestWrapper);
            req.getRequestDispatcher(jspUrl).forward(req, resp);
        } catch (NotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (RequestAttributeNotPermittedException e) {
            e.printStackTrace();
        }
    }
}
