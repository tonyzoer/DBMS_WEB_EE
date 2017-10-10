package itlab.controller.util.impl;


import itlab.controller.util.SessionWrapper;

import javax.servlet.http.HttpSession;

public class SessionWrapperImpl implements SessionWrapper {
    private HttpSession session;

    public SessionWrapperImpl(HttpSession session) {
        this.session = session;
    }

    @Override
    public void invalidate() {
        if(session!=null)
        session.invalidate();
    }

    @Override
    public void setLanguage(String lang) {
        session.setAttribute("lang", lang);
    }

}
