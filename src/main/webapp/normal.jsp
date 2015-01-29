<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/> 

<portlet:renderURL portletMode="edit" var="editURL"/>
<portlet:renderURL portletMode="help" var="helpURL"/>

<!-- 
<link type="text/css" rel="stylesheet" src="<%=request.getContextPath()%>/resources/js/skins/lightgray/content.min.css" />
<div id="tinymce" class="mce-content-body">

${content}

</div>
-->

<!-- 
<script type="text/javascript" src="<%=request.getContextPath()%>\resources\js\tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",  
	plugins: "noneditable",
	noneditable_leave_contenteditable: true
});
</script>
-->

<style id="mceDefaultStyles" type="text/css">.mce-content-body div.mce-resizehandle {position: absolute;border: 1px solid black;background: #FFF;width: 5px;height: 5px;z-index: 10000}.mce-content-body .mce-resizehandle:hover {background: #000}.mce-content-body img[data-mce-selected], hr[data-mce-selected] {outline: 1px solid black;resize: none}.mce-content-body .mce-clonedresizable {position: absolute;outline: 1px dashed black;opacity: .5;filter: alpha(opacity=50);z-index: 10000}.mce-content-body .mce-resize-helper {background: #555;background: rgba(0,0,0,0.75);border-radius: 3px;border: 1px;color: white;display: none;font-family: sans-serif;font-size: 12px;white-space: nowrap;line-height: 14px;margin: 5px 10px;padding: 5px;position: absolute;z-index: 10001}
</style>   <!-- dont know if I need this rn.  -->


<html>

<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/skins/lightgray/content.min.css"/>
</head>

<body>
${content}
</body>

</html>

<!-- 
<div id="tinymce" class="mce-content-body" data-id="content" contenteditable="false">
${content}
</div>
-->

<p>This button will probably be restricted to the admins. <a href=${editURL}>Edit</a></p>
<p>Need help? Click <a href=${helpURL}>here.</a></p>