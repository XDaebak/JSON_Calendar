<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editURL"/>
<portlet:renderURL portletMode="help" var="helpURL"/>

<%
out.println("Hello there!");
JSONObject event = new JSONObject();
event.put("name", "New Member Education");
event.put("start_date", "04162015");
event.put("num", 55);
event.put("group", "Triangle Fraternity");
out.println("The event:");
out.println(event);
%>



<p>Click to go to edit.jsp: <a href=${editURL}>Edit</a></p>
<p>Need help? Click <a href=${helpURL}>here.</a></p>