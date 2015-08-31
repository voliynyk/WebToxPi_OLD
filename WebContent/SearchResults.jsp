<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

    <link rel="stylesheet" type="text/css" href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/WebToxPi/External/font-awesome/css/font-awesome.min.css" />

    <script type="text/javascript" src="/WebToxPi/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>


</head>
<body>

<div class="container">
  <form action="SearchResultsServlet" role="form">

    <legend>Search results:</legend>

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

    <div class="form-group">
    	<button type="submit" class="btn btn-default">Search</button>
    </div>

  </form>
</div>
</body>
</html>