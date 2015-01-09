<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletURL" %> 
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects />

<p>Welcome to edit.jsp!</p>
<br>
<form method="post" action="${actionUrl}">
	new username: <input type="text" name="username" value="${username}">
	Current mode: ${mode}
	<input type="submit">
</form>