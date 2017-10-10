package itlab.controller.util;

import itlab.controller.exceptions.RequestAttributeNotPermittedException;

public interface RequestWrapper {
    void setAttribute(String attributeName, Object value) throws RequestAttributeNotPermittedException;
    String getParameter(String attributeName) throws RequestAttributeNotPermittedException;
    SessionWrapper getSessionWrapper(boolean toCreate);
    public  void addParameter(String name, String value);
    default SessionWrapper getSessionWrapper() {
        return getSessionWrapper(false);
    }
}
