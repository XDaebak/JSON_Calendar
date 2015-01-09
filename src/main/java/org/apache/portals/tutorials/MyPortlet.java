package org.apache.portals.tutorials;

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

//import com.google.inject.Singleton;   
//import com.google.inject.Guice;
//import com.google.inject.Injector;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

// Since service objects will be created through injector classes, 
// this annotation is provided to let them know that the service 
// classes should be singleton objects.
// @Singleton
public class MyPortlet extends GenericPortlet {

	
//	private AmazonService amazonService = new AmazonService("http://amazone.com/lksaldf?key=9843jikoierjf"); 
	
///	private Injector injector = Guice.createInjector(new DataBaseModule());
	
///	DataBaseService dbservice = injector.getInstance(DataBaseService.class);
	
    private static final String NORMAL_VIEW = "/normal.jsp";
    private static final String MAXIMIZED_VIEW = "/maximized.jsp";
    private static final String HELP_VIEW = "/help.jsp";
	private static final String EDIT_MODE = "/edit.jsp";
	

    private PortletRequestDispatcher normalDispatcher;
    private PortletRequestDispatcher maximizedDispatcher;
    private PortletRequestDispatcher helpDispatcher;
	private PortletRequestDispatcher editDispatcher;
	
//	private static final Logger logger = LogManager.getLogger(MyPortlet.class);

	//@RenderMode(name="VIEW")
	@Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		
		String username = fetch(request.getPortletSession(), "username");
		request.setAttribute("username", username == null ? "" : username);
    
//		mysql.fetch("username");
//		mysql.store("username", username);
		
//		logger.trace("Inside the doView method!");	
		
		if (WindowState.MINIMIZED.equals(request.getWindowState())) {
//			logger.trace("Minimized state");
            return; // Basically don't do anything because it is minimized. No JSP to show at all. 
        }
        if (WindowState.NORMAL.equals(request.getWindowState())) {
//        	logger.trace("Normal state. Dispatching to normal.jsp");
			normalDispatcher.include( request, response );
        } else {
//        	logger.trace("Maximized state. Dispatching to maximized.jsp");
			maximizedDispatcher.include( request, response );
        }
    }
	
	@Override
	public void doEdit(RenderRequest request, RenderResponse response)
        throws PortletException, IOException {		
		String username = fetch(request.getPortletSession(), "username");
		request.setAttribute("username", username);
		request.setAttribute("mode", request.getPortletMode());
		request.setAttribute("actionUrl", response.createActionURL());
		editDispatcher.include( request, response );
	}
	
	//@RenderMode(name="HELP")
	@Override
    protected void doHelp(RenderRequest request, RenderResponse response)
        throws PortletException, IOException {
        helpDispatcher.include(request, response);  // We do not have a help button in the portal right now so this doesn't show up
    }
	
	// @ProcessAction(name="saveUsername")
	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		String username = request.getParameter("username");                      
		store(request.getPortletSession(), "username", username);
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
