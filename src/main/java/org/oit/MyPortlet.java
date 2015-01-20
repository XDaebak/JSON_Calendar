package org.oit;

import java.io.IOException;              		
import javax.portlet.PortletConfig;      		
import javax.portlet.GenericPortlet;     		
import javax.portlet.PortletException;   		
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences; 		
import javax.portlet.PortletRequestDispatcher;  
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;             
import javax.portlet.RenderResponse;            
import javax.portlet.ActionRequest;             
import javax.portlet.ActionResponse;            
import javax.portlet.WindowState;

public class MyPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/normal.jsp";
    private static final String MAXIMIZED_VIEW = "/maximized.jsp";
    private static final String HELP_VIEW = "/help.jsp";
	private static final String EDIT_MODE = "/edit.jsp";

    private PortletRequestDispatcher normalDispatcher;
    private PortletRequestDispatcher maximizedDispatcher;
    private PortletRequestDispatcher helpDispatcher;
	private PortletRequestDispatcher editDispatcher;
	

    public void doView(RenderRequest request, RenderResponse response)
        throws PortletException, IOException 
	{

        if (WindowState.MINIMIZED.equals(request.getWindowState())) 
		{
            return;
        }

        if (WindowState.NORMAL.equals(request.getWindowState())) 
		{
            normalDispatcher.include(request, response);
        } 
		
		else 
		{
            maximizedDispatcher.include(request, response);
        }
    }

    protected void doHelp(RenderRequest request, RenderResponse response)
        throws PortletException, IOException 
	{
        helpDispatcher.include(request, response);
    }
	
	protected void doEdit(RenderRequest request, RenderResponse response)
		throws PortletException, IOException
	{
		editDispatcher.include(request, response);
	}
	
	public void processAction(ActionRequest request, ActionResponse response) 
		throws PortletException, IOException 
	{
		// do shit
		response.setPortletMode(PortletMode.VIEW);
	}

    public void init(PortletConfig config) throws PortletException 
	{
        super.init(config);
        normalDispatcher = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW);
        maximizedDispatcher = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW);
        helpDispatcher = config.getPortletContext().getRequestDispatcher(HELP_VIEW);
		editDispatcher = config.getPortletContext().getRequestDispatcher(EDIT_MODE);
    }

    public void destroy() 
	{
        normalDispatcher = null;
        maximizedDispatcher = null;
        helpDispatcher = null;
		editDispatcher = null;
		
        super.destroy();
    }

}
