package org.oit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.json.*;

public class MyPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/normal.jsp";
    private static final String MAXIMIZED_VIEW = "/maximized.jsp";
    private static final String HELP_VIEW = "/help.jsp";
    private static final String EDIT_MODE = "/edit.jsp";

    private PortletRequestDispatcher normalDispatcher;
    private PortletRequestDispatcher maximizedDispatcher;
    private PortletRequestDispatcher helpDispatcher;
    private PortletRequestDispatcher editDispatcher;

    @Override
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
 //   	PortletPreferences prefs = request.getPreferences();
        //   	String content = prefs.getValue("content", "Click edit to edit content."); 
        //   	prefs.setValue("content", content);  

 //   	request.setAttribute("content", content);
        //   	request.setAttribute("mode", request.getPortletMode());
        if (WindowState.MINIMIZED.equals(request.getWindowState())) {
            return; // Don't do anything because minimized does not need JSP
        }

        if (WindowState.NORMAL.equals(request.getWindowState())) {
            normalDispatcher.include(request, response);
        } else {
            maximizedDispatcher.include(request, response);
        }
    }

    @Override
    protected void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        helpDispatcher.include(request, response);
    }

    @Override
    protected void doEdit(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
//		PortletPreferences prefs = request.getPreferences();
//		String content = prefs.getValue("content", "Edit content and click submit. Will add cancel button later");
//		request.setAttribute("content", content);
//		request.setAttribute("mode", request.getPortletMode());
//		request.setAttribute("actionURL", response.createActionURL());	
        editDispatcher.include(request, response);
    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {
//		String content = request.getParameter("content");
//		PortletPreferences prefs = request.getPreferences();
//		prefs.setValue("content", content);
//		prefs.store();
        response.setPortletMode(PortletMode.VIEW);
    }

    public void init(PortletConfig config) throws PortletException {
        super.init(config);
        normalDispatcher = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW);
        maximizedDispatcher = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW);
        helpDispatcher = config.getPortletContext().getRequestDispatcher(HELP_VIEW);
        editDispatcher = config.getPortletContext().getRequestDispatcher(EDIT_MODE);
    }

    public void destroy() {
        normalDispatcher = null;
        maximizedDispatcher = null;
        helpDispatcher = null;
        editDispatcher = null;

        super.destroy();
    }

}
