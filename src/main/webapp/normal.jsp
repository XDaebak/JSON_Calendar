<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editURL"/>
<portlet:renderURL portletMode="help" var="helpURL"/>

<iframe src="https://www.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23cc66cc&amp;src=en.usa%23holiday%40group.v.calendar.google.com&amp;color=%2329527A&amp;ctz=America%2FLos_Angeles" 
		style=" border:solid 1px #777 " 
		width="800" 
		height="600" 
		frameborder="0" 
		scrolling="no">
</iframe>

<p>Click to go to edit.jsp: <a href=${editURL}>Edit</a></p>
<p>Need help? Click <a href=${helpURL}>here.</a></p>