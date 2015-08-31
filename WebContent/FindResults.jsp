<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="/WebToxPi/External/jquerysteps/normalize.css">
<link rel="stylesheet" href="/WebToxPi/External/jquerysteps/main.css">
<link rel="stylesheet" href="/WebToxPi/External/jquerysteps/jquery.steps.css">
<link href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" rel="stylesheet" />

<script src="/WebToxPi/External/jquerysteps/modernizr-2.6.2.min.js"></script>
<script type="text/javascript"	src="/WebToxPi/External/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/WebToxPi/External/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="/WebToxPi/External/jquerysteps/jquery.steps.min.js"></script>

<script src="/WebToxPi/External/dual-list-box/dual-list-box.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="example-vertical">
		<h3>Sources</h3>
		<section>
		<div class="container">
			<div id="dual-list-box-source" class="form-group row">
				<select multiple="multiple" data-title="Sources" data-source="/WebToxPi/rest/loadsetupdata/getallsources" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>CAS Registry Numbers</h3>
		<section>
		<div class="container">
			<div id="dual-list-box-casrn" class="form-group row">
				<select multiple="multiple" data-title="CASRNs" data-source="/WebToxPi/rest/loadsetupdata/getallcasrns" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>Chemicals</h3>
		<section>
		<div class="container">
			<div id="dual-list-box-chem" class="form-group row">
				<select multiple="multiple" data-title="Chemicals" data-source="/WebToxPi/rest/loadsetupdata/getallchemicals" data-value="chemicalId" data-text="chemicalCode"></select>
			</div>
		</div>
		</section>
		
		<h3>Pager</h3>
		<section>
		<p>The next and previous buttons help you to navigate through your
			content.</p>
		</section>
	</div>


	<script type="text/javascript">
		$("#example-vertical").steps({
			headerTag : "h3",
			bodyTag : "section",
			transitionEffect : "slideLeft"
				//,stepsOrientation : "vertical"
		});
	</script>
	<script type="text/javascript">
		$('select').DualListBox();
	</script>
</body>
</html>