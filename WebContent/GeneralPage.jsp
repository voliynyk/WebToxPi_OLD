<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" type="text/css" href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/WebToxPi/External/font-awesome/css/font-awesome.min.css" />

  <script type="text/javascript" src="/WebToxPi/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">WebToxPi</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
		<li>
			<a href="#" data-toggle="modal" data-target="#ImportModal">Import</a>
			<div class="modal fade" id="ImportModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Import results from csv file</h4>
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
		      
		    </div>
		  </div>
		</li>
		
        <li><a href="#">Export</a></li>
        
        <li>
        	<a href="#" data-toggle="modal" data-target="#SearchResultsModal">Search results</a>
        	<div class="modal fade" id="SearchResultsModal" role="dialog">
		    <div class="modal-dialog modal-lg">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Search results:</h4>
		        </div>
				<div class="container">
				  <form action="SearchResultsServlet" role="form">
				    <div class="form-group">
				      <label for="casrn" >CAS Registry Number</label>
				      <div class="form-group">
				        <input type="text" class="form-control" id="casrn" name="casrn" placeholder="CAS Registry Number">
				      </div>
				    </div>
				
				    <div class="form-group">
				      <label for="chemical">Chemical Name</label>
				      <div class="form-group">
				        <input type="text" class="form-control" id="chemical" name="chemical" placeholder="Chemical Name">
				      </div>
				    </div>
				
				    <div class="form-group">
				      <label for="component">Component Name</label>
				      <div class="form-group">
				        <input type="text" class="form-control" id="component" name="component" placeholder="Component Name">
				      </div>
				    </div>
					<div class="modal-footer">
			        	<button type="submit" class="btn btn-default btn-primary">Search</button>
			        	<button type="button" class="btn" data-dismiss="modal">Close</button>
		        	</div>
				  </form>
				</div>
		      </div>
		      
		    </div>
		  </div>
        </li>
        <li><a href="/WebToxPi/ViewResults.jsp">View Results</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-about"></span> About</a></li>
        <li>
        	<a href="#" data-toggle="modal" data-target="#login-modal" class="glyphicon glyphicon-log-in">Login</a>
        	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
	    	  <div class="modal-dialog">
					<div class="loginmodal-container">
						<h1>Login to Your Account</h1><br>
					  <form action="LoginServlet" method="post">
						<input type="text" name="username" placeholder="Username">
						<input type="password" name="password" placeholder="Password">
						<input type="submit" name="login" class="login loginmodal-submit" value="Login">
					  </form>
						
					  <div class="login-help">
						<a href="#">Register</a> - <a href="#">Forgot Password</a>
					  </div>
					</div>
				</div>
			  </div>
        </li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container">
  <div class="jumbotron">
    <h1>!</h1>      
    <p></p>      
  </div>
</div>

</body>
</html>