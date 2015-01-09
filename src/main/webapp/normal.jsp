<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editUrl"/>
<portlet:renderURL portletMode="help" var="helpUrl"/>

<p>
<h4>Welcome to the normal.jsp page. Jiu Jitsu is pretty cool. So is Judo.</h4>
</p>

<p>Welcome to Calvin's Jiu Jitsu Dojo yo. Your username is currently ${username} </p>

<p>Current mode: <%= renderRequest.getPortletMode() %> </p>

<p>If you would like to change your username, please click <a href=${editUrl}>edit</a> </p>

<p>If you would like some help, please click <a href=${helpUrl}>help</a> </p> 