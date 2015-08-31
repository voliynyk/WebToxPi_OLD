<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="edu.tamu.webtoxpi.Client.DataManager.DataManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>WebToxPi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" type="text/css" href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" />
     <link rel="stylesheet" type="text/css" href="/WebToxPi/External/font-awesome/css/font-awesome.min.css" />

    <script type="text/javascript" src="/WebToxPi/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>
</head>

<head>

<title>WebToxPi</title>

<script>
	var results = [];

	function get_json(url) {
		results = $.getJSON(url);
	}
	function get_jsonold(url, callback) {
		$.getJSON(url, function(jsonObject) {
			callback(jsonObject);
		});
	}

</script>

</head>
<body>
<!-- 	<div>
		<h1>Load csv file.</h1>
		<form action="rest/file/upload" method="post" enctype="multipart/form-data">
			<p>
				Select a file :<input type="file" name="file" size="45" />
			</p>
			<input type="submit" value="Upload It" />
		</form>
	</div> -->
	<div>
		<%-- 		<table id="results" class="table table-striped table-bordered table-condensed" style="clear: both">
			<thead>
				<tr>
					<th><u>ID</u></th>
					<th><u>Numeric Result</u></th>
					<th><u>Component</u></th>
					<th><u>CASRN</u></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (edu.tamu.webtoxpi.Server.Models.Classes.Results res : edu.tamu.webtoxpi.Client.DataManager.DataManager.getResultsByComponentCode("ACEA_IC50"))
					{
				%>
				<tr>
					<td><%=res.getId()%></td>
					<td><%=res.getNumresult()%></td>
					<td><%=res.getComponents().getCode()%></td>
					<td><%=res.getOrders().getCasregistrynumbers().getCode()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table> --%>
	</div>

<div class="container">

<div class="page-header">
    <h1>WebToxPi</small></h1>
</div>

        <div class="panel-body text-center">
            <div id="grid"></div>
        </div>

<link rel="stylesheet" type="text/css" href="/WebToxPi/js/all.min.css" />
<script type="text/javascript" src="/WebToxPi/js/shieldui-lite-all.min.js"></script>
<script type="text/javascript" src="/WebToxPi/js/shortGridData.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").shieldGrid({
            dataSource: {
                data: gridData,
                schema: {
                    fields: {
                        id: { path: "id", type: Number },
                        name: { path: "name", type: String },
                        company: { path: "company", type: String },
                        month: { path: "month", type: Date },
                        isActive: { path: "isActive", type: Boolean },
                        email: { path: "email", type: String },
                        transport: { path: "transport", type: String }
                    }
                }
            },
            sorting: {
                multiple: true
            },
            rowHover: false,
            columns: [
                { field: "name", title: "Person Name", width: "120px" },
                { field: "age", title: "Age", width: "80px" },
                { field: "company", title: "Company Name" },
                { field: "month", title: "Date of Birth", format: "{0:MM/dd/yyyy}", width: "120px" },
                { field: "isActive", title: "Active" },
                { field: "email", title: "Email Address", width: "250px" },
                { field: "transport", title: "Custom Editor", width: "120px", editor: myCustomEditor }
            ],
            editing: {
                enabled: true,
                event: "click",
                type: "cell"
            },
            events:
            {
                getCustomEditorValue: function (e) {
                    e.value = $("#dropdown").swidget().value();
                    $("#dropdown").swidget().destroy();
                }
            }
        });

        function myCustomEditor(cell, item) {
            $('<div id="dropdown"/>')
                .appendTo(cell)
                .shieldDropDown({
                    dataSource: {
                        data: ["motorbike", "car", "truck"]
                    },
                    value: !item["transport"] ? null : item["transport"].toString()
                }).swidget().focus();
        }
    });
</script>

<style>
    .sui-button-cell
    {
        text-align: center;
    }

    .sui-checkbox
    {
        font-size: 17px !important;
        padding-bottom: 4px !important;
    }

    .deleteButton img
    {
        margin-right: 3px;
        vertical-align: bottom;
    }

    .bigicon
    {
        color: #5CB85C;
        font-size: 20px;
    }
</style>
</div>

</body>
</html>