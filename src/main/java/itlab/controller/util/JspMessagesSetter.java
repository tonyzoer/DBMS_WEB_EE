package itlab.controller.util;

import itlab.controller.exceptions.RequestAttributeNotPermittedException;

public class JspMessagesSetter {

    public enum JspError {
        WRONG_LOGIN,
        INSUFFICIENT_PERMISSIONS,
        UNKNOWN_ID,
        UNKNOWN_COMMAND,
        LOGIN_ALREADY_EXIST,
        FIELD_EMPTY_REQUIRED,
        FIELD_WRONG_DATA,
        WRONG_EMAIL,
        WRONG_LOGIN_LENGTH,
        WRONG_PASSWORD,
        COULDNT_REMOVE_ID;
    }

    public enum JspResult {
        SESSION_IS_OVVERED;
    }

    public static void setOutputError(RequestWrapper req, JspError error) throws RequestAttributeNotPermittedException {
        req.setAttribute("error", error.toString());
    }

    public static void setOutputError(RequestWrapper req, JspError error, String message) throws RequestAttributeNotPermittedException {
        req.setAttribute("error", error.toString());
        req.setAttribute("error_message", message);
    }

    public static void setOutputMessage(RequestWrapper req, JspResult result) throws RequestAttributeNotPermittedException {
        req.setAttribute("result", result.toString());
    }

    public static void setOutputMessage(RequestWrapper req, JspResult result, String message) throws RequestAttributeNotPermittedException {
        req.setAttribute("result", result.toString());
        req.setAttribute("result_message", message);
    }


}
