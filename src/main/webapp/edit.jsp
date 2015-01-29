<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<script type="text/javascript" src="<%=request.getContextPath()%>\resources\js\tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
	theme: "modern", 
	skin: "lightgray",  
	browser_spellcheck: true,  
	object_resizing: "img",    
	toolbar: [
		"undo redo | styleselect | bold italic | link image",
		"alignleft aligncenter alignright"
	]
});
</script>

<form method="post" action=${actionURL}>  
    <textarea name="content" style="width:100%">
	${content} 
	</textarea>
	<input type="submit"/>
</form>
