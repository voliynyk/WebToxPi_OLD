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

<!-- <script src="/WebToxPi/External/dual-list-box/dual-list-box.min.js"></script> -->
<script src="/WebToxPi/External/dual-list-box/dual-list-box.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="example-vertical">
		<h3>Sources</h3>
		<section>
		<div class="container" id="dual-list-box-source2">
			<div class="form-group row" id="dual-list-box-source1">
				<select id="dual-list-box-source" multiple="multiple" data-title="Sources" data-source="/WebToxPi/rest/loadsetupdata/getallsources" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>CAS Registry Numbers</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-casrn" multiple="multiple" data-title="CASRNs" data-source="/WebToxPi/rest/loadsetupdata/getallcasrns" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>Chemicals</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-chem" multiple="multiple" data-title="Chemicals" data-source="/WebToxPi/rest/loadsetupdata/getallchemicals" data-value="chemicalId" data-text="chemicalCode"></select>
			</div>
		</div>
		</section>
		
		<h3>Weights</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-weight" multiple="multiple" data-title="Weights" data-source="/WebToxPi/rest/loadsetupdata/getallweights" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>Groups</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-groups" multiple="multiple" data-title="Groups" data-source="/WebToxPi/rest/loadsetupdata/getallgroups" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>Types</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-types" multiple="multiple" data-title="Types" data-source="/WebToxPi/rest/loadsetupdata/getalltypes" data-value="id" data-text="code"></select>
			</div>
		</div>
		</section>
		
		<h3>Components</h3>
		<section>
		<div class="container">
			<div class="form-group row">
				<select id="dual-list-box-component" multiple="multiple" data-title="Components" data-source="/WebToxPi/rest/loadsetupdata/getallcomponents" data-value="componentId" data-text="componentCode"></select>
			</div>
		</div>
		</section>
	</div>


	<script type="text/javascript">
		$("#example-vertical").steps({
			headerTag : "h3",
			bodyTag : "section",
			transitionEffect : "slideLeft",
			stepsOrientation : "vertical",
			onStepChanged:
				function (event, currentIndex, priorIndex)
				{
					var ddd = $('select').getSelectedItems(1);
					var ddd1 = $('select');

				}, 
			onFinished:
				function (event, currentIndex)
				{
				document.getElementById("source").value = $('select').getSelectedItems(1);
				document.getElementById("casrn").value = $('select').getSelectedItems(3);
				document.getElementById("chemical").value = $('select').getSelectedItems(5);
				document.getElementById("component").value = $('select').getSelectedItems(13);
					document.getElementById("inputForm").submit();
/* 			         $.ajax({
			             url: 'SearchResultsServlet',
			             dataType: 'json',
			             data: {
				             source: $('select').getSelectedItems(1),
				             casrn : $('select').getSelectedItems(3),
				             chemical : $('select').getSelectedItems(5),
				             component : $('select').getSelectedItems(13)
				             },
			             type: 'post',
			             cache: false,
			             success: function(response)
			             {
 			            	window.location.href = "FoundResults.jsp";
			             }
			             /*  		             	error: function(request, textStatus, errorThrown)
			             {
			               //alert("error:" + textStatus);
			             },
			             complete: function(request, textStatus)
			             {
			            	 window.location = "FoundResults.jsp";
			             } 
			           }); */
				},
		    labels: {
		        finish: "Search"
		    }
		});
	</script>
	<script type="text/javascript">
		$('select').DualListBox();
	</script>


	<form action="SearchResultsServlet" role="form" method="post" id="inputForm">
		<input type="hidden" id="source" name="source" />
		<input type="hidden" id="casrn" name="casrn" />
		<input type="hidden" id="chemical" name="chemical" />
		<input type="hidden" id="component" name="component" />
		<input type="hidden" id="searchById" name="searchById" value="true" />
	</form>
</body>
</html>