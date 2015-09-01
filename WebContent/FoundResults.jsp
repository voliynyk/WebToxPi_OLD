<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="edu.tamu.webtoxpi.Server.Outbound.OutData" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Results</title>

<script type="text/javascript" src="/WebToxPi/External/shieldui/js/jquery-1.11.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="/WebToxPi/External/bootstrap3/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/WebToxPi/External/font-awesome/css/font-awesome.min.css" />
<script type="text/javascript" src="/WebToxPi/External/bootstrap3/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="/WebToxPi/js/all.min.css" />

<script type="text/javascript" src="/WebToxPi/External/shieldui/js/shieldui-all.min.js"></script>

<script type="text/javascript" src="/WebToxPi/js/TableExport/tableExport.min.js"></script>
<script type="text/javascript" src="/WebToxPi/js/TableExport/FileSaver.min.js"></script>
<script type="text/javascript" src="/WebToxPi/js/TableExport/html2canvas.min.js"></script>
<script type="text/javascript" src="/WebToxPi/js/TableExport/jspdf.min.js"></script>
<script type="text/javascript" src="/WebToxPi/js/TableExport/jspdf.plugin.autotable.js"></script>
<script src="/WebToxPi/js/TableExport/bootstrap-table-export.js"></script> 

</head>
<body class="theme-light">
<button id="button1" onclick="exportToPDF()"><img src="/WebToxPi/img/pdf.png" />Export To PDF</button>

<div id="filterbox">
    Search as you type:
    <input type="text" />
    <a><img src="/WebToxPi/img/search-icon.png" /></a>
</div>

<script>
var currentEditableRow;
</script>


<script>
function exportToPDF() {
	$('#grid').tableExport({type:'pdf',
	    jspdf: {orientation: 'p',
	            margins: {left:20, top:10},
	            autotable: false}
	   });
}
</script>


<script>
function getShieldGridFields(obj)
{
	var resultStr = "var shieldGridFields = {";
    if (obj !== undefined)
    {
    	$.each(obj[0], function(k, v)
                {
            		resultStr += '"' + k + '"' + ': { path: "' + k + '", type : String }, ';
            	}
            	);
    	resultStr = resultStr.substr(0, resultStr.length-2); 
    	resultStr += "}"
    }
    
    return resultStr;
}

function getShieldGridColumns(obj)
{
	var resultStr = "var shieldGridColumns = [";
    if (obj !== undefined)
    {
    	$.each(obj[0], function(k, v)
                {
            		if (k == "reservedFieldSource")
                	{
            			resultStr += '{ field: ' + '"' + k + '", width: "200px", title: "Source", format: function(value) {return "<strong>" + value + "</strong>";} }, ';
                    }
            		else if (k == "reservedFieldCASRN")
                	{
            			resultStr += '{ field: ' + '"' + k + '", width: "200px", title: "CASRN", format: function(value) {return "<strong>" + value + "</strong>";} }, ';
                    }
            		else if (k == "reservedFieldChemical")
                	{
            			resultStr += '{ field: ' + '"' + k + '", width: "200px", title: "Chemical", format: function(value) {return "<strong>" + value + "</strong>";} }, ';
                    }
            		else if (k !== "id")
               		{
           				resultStr += '{ field: ' + '"' + k + '", width: "100px", title: ' + '"' + k + '" }, ';
               		}
            	}
            	);
    	resultStr = resultStr.substr(0, resultStr.length-2); 
    	resultStr += "]"
    }
    
    return resultStr;
}
</script>

<div id="grid" class="sui-grid sui-grid-core"></div>
<script>
<%String data = (String)request.getAttribute("outdata");%>
var obj = JSON.parse('<%= data %>');

eval(getShieldGridFields(obj));
eval(getShieldGridColumns(obj));

$(document).ready(function () {
	document.getElementById("grid").style.height = (window.innerHeight - 150)+ "px";
    $("#grid").shieldGrid({
        dataSource: {
            data: obj,

            
            schema: {
                fields: shieldGridFields
            }
        },
        sorting:{
            multiple: false,
            allowUnsort: false
        },
        scrolling:true,
        resizing: true,
        paging: {
            pageSize: 15,
            showBoundaryLinks: true,
            pageLinksCount: 10
        },
        columns: shieldGridColumns,
        editing: {
            enabled: true,
            event: "click",
            type: "cell"
        },
        events:
        {
            save: function(e)
            {
                var editors = e.target._editing._editors;
                var component = Object.keys(editors)[0];
                var newValue = editors[component].oldValue;
                var oldValue = editors[component].options["value"];
                if (oldValue !== newValue)
                {
                	
                    var source = currentEditableRow[0].childNodes[0].innerText;
                    var casrn = currentEditableRow[0].childNodes[1].innerText;
                    var chemical = currentEditableRow[0].childNodes[2].innerText;
                    $.ajax({
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        url: "/WebToxPi/rest/results/update",
                        dataType: JSON,
                        data: JSON.stringify('{ "source":"'+source+'","casrn":"'+casrn+'","chemical":"'+chemical+'","component":"'+component+'","newValue":"'+newValue+'"}'),
/*                         error: function (e) {
                            alert("error" + e)
                        } */
                    });

                }
            },
            edit: function(e)
            {
            	//var selectedItemID = $("#grid").swidget().contentTable;
            	currentEditableRow = e.row;
            },
	    	command: function (e) {
	            if (e.commandName == "edit")
		        {
	            	if (e.cell.cellIndex < 3)
	               	{
	            		e.cancel = true;
	               	}
	            }
	    	}
        }     
    });
    var dataSource = $("#grid").swidget().dataSource,
    input = $("#filterbox input"),
    timeout,
    value;
	input.on("keydown", function () {
    clearTimeout(timeout);
    timeout = setTimeout(function () {
        value = input.val();
        if (value) {
            dataSource.filter = {
                or: [
                    { path: "reservedFieldSource", filter: "contains", value: value },
                    { path: "reservedFieldCASRN", filter: "contains", value: value },
                    { path: "reservedFieldChemical", filter: "contains", value: value }
                ]
            };
        }
        else {
            dataSource.filter = null;
        }
        dataSource.read();
    }, 300);
});
});

</script>


<style scoped>
        #filterbox {
            text-align: right;
            margin-bottom: 20px;
        }
        #filterbox input {
            border: 1px solid #C4C4C4;
            padding: 8px;
            width: 260px;
        }
        #filterbox a {
            display: inline-block;
            *display: inline;
            width: 26px;
            height: 26px;
            vertical-align: middle;
        }
        #filterbox img {
            line-height: 0;
        }
</style>

</body>
</html>