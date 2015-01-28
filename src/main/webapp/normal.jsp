<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editURL"/>
<portlet:renderURL portletMode="help" var="helpURL"/>

<p>Current Mode is right now ${mode}</p>
<p>Content: ${content}</p>
<p>Click <a href=${editURL}>Edit</a> to edit</p>
<p>Need help? Click <a href=${helpURL}>here.</a></p>