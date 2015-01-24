<script type="text/javascript" src="C:\OITDevEnv\Files\tinymce\js\tinymce\tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
/*
    selector: "textarea",
	theme: "modern",    // default
	skin: "lightgray",  // default
	width: 1000,
	height: 1000,
	browser_spellcheck: true,  
	object_resizing: "img"    
	toolbar: [
		"undo redo | styleselect | bold italic | link image",
		"alignleft aligncenter alignright"
	],
*/

	selector : "content",
    theme : "advanced",
    theme_advanced_buttons1 : "mybutton,bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
    theme_advanced_buttons2 : "",
    theme_advanced_buttons3 : "",
    theme_advanced_toolbar_location : "top",
    theme_advanced_toolbar_align : "left",
    theme_advanced_statusbar_location : "bottom",
    plugins : 'inlinepopups',
    setup : function(ed) {
        // Add a custom button   
        ed.addButton('mybutton', {
            title : 'My button',
            image : 'img/example.gif',
            onclick : function() {
                // Add you own code to execute something on click
                ed.focus();
                ed.selection.setContent("Brazilian Jiu Jitsu");
            }
        });
    }	
 });
</script>

<!-- Need to add some renderURL's for this -->

<form method="post">  <!-- action="someurl" -->
    <textarea name="content" style="width:100%">
	Brazilian Jiu Jitsu + Judo Randori sounds very fun. 
	</textarea>
</form>
