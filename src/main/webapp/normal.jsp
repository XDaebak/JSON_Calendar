<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editURL"/>
<portlet:renderURL portletMode="help" var="helpURL"/>



<p>Click to go to edit.jsp: <a href=${editURL}>Edit</a></p>
<p>Need help? Click <a href=${helpURL}>here.</a></p>