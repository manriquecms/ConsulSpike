<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stratio Manager Service Detection</title>
</head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$( document ).ready(function() {
	 	$( "#button" ).click(function() {
			$.ajax({
				url: "/consul-ui-spike/api/hello"
			}).done(function(data) {
				$("#content").html("<div></div>");
			});
		});
	});
</script>

<div id='content'>Hello</div>
<div id='button'>Button</div>
</body>
</html>