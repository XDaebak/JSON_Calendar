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
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import net.fortuna.ical4j.data.CalendarParser;
import net.fortuna.ical4j.data.CalendarParserFactory;
import net.fortuna.ical4j.data.UnfoldingReader;

import org.oit.XMLHandler;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.*;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.*;

public class MyPortlet extends GenericPortlet 
{

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
        throws PortletException, IOException 
	{
 //   	PortletPreferences prefs = request.getPreferences();
 //   	String content = prefs.getValue("content", "Click edit to edit content."); 
 //   	prefs.setValue("content", content);  
    	
 //   	request.setAttribute("content", content);
 //   	request.setAttribute("mode", request.getPortletMode());

        if (WindowState.MINIMIZED.equals(request.getWindowState())) 
		{
            return; // Don't do anything because minimized does not need JSP
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
	
	@Override
    protected void doHelp(RenderRequest request, RenderResponse response)
        throws PortletException, IOException 
	{
        helpDispatcher.include(request, response);
    }
	
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
		throws PortletException, IOException
	{
//		PortletPreferences prefs = request.getPreferences();
//		String content = prefs.getValue("content", "Edit content and click submit. Will add cancel button later");
//		request.setAttribute("content", content);
//		request.setAttribute("mode", request.getPortletMode());
//		request.setAttribute("actionURL", response.createActionURL());	
		editDispatcher.include(request, response);
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response) 
		throws PortletException, IOException 
	{
//		String content = request.getParameter("content");
//		PortletPreferences prefs = request.getPreferences();
//		prefs.setValue("content", content);
//		prefs.store();
		response.setPortletMode(PortletMode.VIEW);
	}
	
	public void setUp() 
		throws IOException, GeneralSecurityException 
	{
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	    // The clientId and clientSecret can be found in Google Developers Console
	    String clientId = "925906760721-bc6npb7mhpfbooimc9q3tsar905l2bfv.apps.googleusercontent.com";
	    String clientSecret = "VQ2FWD3WFkPV7OLtjMoidy45";  // notasecret

	    // Or your redirect URL for web based applications.
	    String redirectUrl = "https://github.com/XDaebak"; // urn:ietf:wg:oauth:2.0:oob
	    String scope = "https://www.googleapis.com/auth/calendar";

	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow(
	        httpTransport, jsonFactory, clientId, clientSecret, Collections.singleton(scope));
	    // Step 1: Authorize
	    String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUrl).build();

	    // Point or redirect your user to the authorizationUrl.
	    System.out.println("Go to the following link in your browser:");
	    System.out.println(authorizationUrl);

	    // Read the authorization code from the standard input stream.
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("What is the authorization code?");
	    String code = in.readLine();
	    // End of Step 1

	    // Step 2: Exchange
	    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUrl)
	        .execute();
	    // End of Step 2

	    Credential credential = new GoogleCredential.Builder()
	        .setTransport(httpTransport)
	        .setJsonFactory(jsonFactory)
	        .setClientSecrets(clientId, clientSecret)
	        .build().setFromTokenResponse(response);

	    Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credential)
	        .setApplicationName("GoogleCalendar").build();
	    
	    CalendarList clist = service.calendarList();
	}

    public void init(PortletConfig config) throws PortletException 
	{
        super.init(config);
        normalDispatcher = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW);
        maximizedDispatcher = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW);
        helpDispatcher = config.getPortletContext().getRequestDispatcher(HELP_VIEW);
		editDispatcher = config.getPortletContext().getRequestDispatcher(EDIT_MODE);
		
		/*
		// Testing iCal to XML parser
		String ICALSTREAM = "BEGIN:VCALENDAR\\r\\nPRODID:-//Sun/Sample//EN\\r\\nVERSION:2.0\\r\\n" + 
	            "BEGIN:VEVENT\\r\\nUID:1\\r\\nDTSTAMP:20070313T082041Z\\r\\nDTSTART;VALUE=DATE:20081212\\r\\n" +
	            "SUMMARY:wrapped \\r\\n" +
	            " summary\\r\\n" +
	            "END:VEVENT\\r\\nEND:VCALENDAR";
		
		Reader reader = new UnfoldingReader(new StringReader(ICALSTREAM));
        
        CalendarParser parser = CalendarParserFactory.getInstance().createParser();
        
        StringWriter writer = new StringWriter();
        try 
        {
	        XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
	        XMLHandler handler = new XMLHandler(xmlWriter);
	        xmlWriter.writeStartDocument();
	        parser.parse(reader, handler);
	        xmlWriter.writeEndDocument();
	        xmlWriter.close();
        }
        catch (Exception e)
        {
        	System.out.println("AHAHAHAHA ERROR");
        }
        
        System.out.println("xml representation:" + writer.toString());
        */
		
		// Google Calendar OAuth stuff
//		try {
//			setUp();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
