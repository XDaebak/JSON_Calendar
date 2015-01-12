package org.example;

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

	@Override
    public void doView( RenderRequest request, RenderResponse response )
        throws PortletException, IOException {
		
		//String username = fetch(request.getPortletSession(), "username");
		//request.setAttribute("username", username == null ? "" : username);
		
		PortletPreferences prefs = request.getPreferences();
		String username = prefs.getValue("username", "Calvin");  // Get username value. Default is Calvin if null. 
		prefs.setValue("username", username);  // set the value
		//prefs.store();
		
		request.setAttribute("username", username);

        if(WindowState.MINIMIZED.equals(request.getWindowState()))
		{
            return; // Basically don't do anything because it is minimized. No JSP to show at all. 
        }

        if(WindowState.NORMAL.equals(request.getWindowState())) 
		{
            normalDispatcher.include( request, response );
        } 
		
		else 
		{
            maximizedDispatcher.include( request, response );
        }
    }
	
	@Override
	public void doEdit(RenderRequest request, RenderResponse response)
        throws PortletException, IOException {		
		
		//String username = fetch(request.getPortletSession(), "username");
		//request.setAttribute("username", username);
		PortletPreferences prefs = request.getPreferences();
		String username = prefs.getValue("username", "Calvin");  // Get username value. Default is Calvin if null. 
		//prefs.setValue("username", username);
		//prefs.store();
		request.setAttribute("username", username);
		
		request.setAttribute("mode", request.getPortletMode());
		request.setAttribute("actionUrl", response.createActionURL());
		editDispatcher.include(request, response);
	}

	@Override
    protected void doHelp( RenderRequest request, RenderResponse response )
        throws PortletException, IOException {
		
        helpDispatcher.include(request, response); 
    }
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response) 
		throws PortletException, IOException {
		
		String username = request.getParameter("username");                      
		//store(request.getPortletSession(), "username", username);

		PortletPreferences prefs = request.getPreferences();
		//String username = prefs.getValue("username", "Calvin");
		prefs.setValue("username", username);
		prefs.store();
		response.setPortletMode(PortletMode.VIEW);
	}
	
    public void init( PortletConfig config ) throws PortletException {
        super.init( config );
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

	// store key, value pair
	private void store(PortletSession data, String key, String value){
    	data.setAttribute(key, value);    	
    }
    
    // fetch value given key
    private String fetch(PortletSession data, String key){
    	return data.getAttribute(key).toString();    	
    }
}
