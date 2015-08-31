<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Import results</title>

  <link rel="stylesheet" type="text/css" href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/WebToxPi/External/font-awesome/css/font-awesome.min.css" />

  <script type="text/javascript" src="/WebToxPi/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>
  
</head>
<body>

		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Modal Header</h4>
		        </div>
		        <div class="modal-body">
					<form action="rest/file/upload" method="post" enctype="multipart/form-data">
						<p>
							Select a file :<input type="file" name="file" size="45" />
						</p>
						<input type="submit" value="Upload It" />
					</form>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>

<script type="text/javascript">

</script>
</body>
</html>