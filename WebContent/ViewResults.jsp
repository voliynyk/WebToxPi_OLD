<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Title</title>
<!--     <link id="themecss" rel="stylesheet" type="text/css" href="//www.shieldui.com/shared/components/latest/css/light/all.min.css" />
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script> -->
    
    <link id="themecss" rel="stylesheet" type="text/css" href="//www.shieldui.com/shared/components/latest/css/light/all.min.css" />
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
</head>
<body class="theme-light">
<div id="container">
    <div>
        <div id="mainGrid" class="sui-grid sui-grid-core"></div>
        <div id="childGrid" class="sui-grid sui-grid-core"></div>
    </div>
    <div>
        <div id="resultGrid" class="sui-grid sui-grid-core"></div>
    </div>
    <div>
        <div id="chart1"></div>
        <div id="chart2"></div>
        <div id="chart3"></div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        var horizontalSplitter = $("#container").shieldSplitter();
    });
</script>
<style>
    #container {
        height: 1200px;
    }
</style> 

<script type="text/javascript">

$("#mainGrid").shieldGrid({
    dataSource: {
        remote: {
            read: {
                url: "/WebToxPi/rest/results/getallorders",
                dataType: "json"
            }
        },
        schema: {
            total: function (result) {
                return result["odata.count"];
            },
            fields:
                window.orderFields =
                {
	                "orderId": { path: "orderId" },
	                "sourceCode": { path: "sourceCode" },
	                "casrnCode": { path: "casrnCode" },
	                "chemicalCode": { path: "chemicalCode" },
	                
            	}
        }
    },
    paging: true,
    sorting: true,
    selection: {
        type: "row",
        multiple: false
    },
    filtering: {
        enabled: true
    },
    grouping: {
        showGroupHeader: true,
        allowDragToGroup: true,
        message: "Drag a column header here to group by a column"
    },
    events: {
        selectionChanged: onSelectionChangedMainGrid
    },
    columns: [
        { field: "orderId", title: "ID", width: 50 },
        { field: "sourceCode", title: "Source", width: 200 },
        { field: "casrnCode", title: "CASRN", width: 200 },
        { field: "chemicalCode", title: "Chemical", width: 200 }
    ]
});

function onSelectionChangedMainGrid(e)
{
    var selectedItemID = $("#mainGrid").swidget().contentTable.find(".sui-selected").get(0).cells[0].innerHTML;
    var secondGrid = $("#childGrid").swidget();
    if (secondGrid)
    {
    	updateChildGridData(selectedItemID);
    	updateResultGridData(0, 0);
    }
    else
    {
        $("#childGrid").shieldGrid(
        {
            dataSource:
            {remote:{
             read:{
                        url: "/WebToxPi/rest/results/getcomponents/" + selectedItemID,
                        dataType: "json"
                    }
                },
                schema: {
                    total: function (result) {
                        return result["odata.count"];
                    },
                    fields:
                        window.orderFields =
                        {
        	                "componentId": { path: "componentId" },
        	                "unitCode": { path: "unitCode" },
        	                "componentCode": { path: "componentCode" },
        	                "typeCode": { path: "typeCode" },
        	                "groupCode": { path: "groupCode" },
        	                "weightCode": { path: "weightCode" },
                    	}
                }
            },
            paging: true,
            sorting: true,
            selection: {
                type: "row",
                multiple: false
            },
            filtering: {
                enabled: true
            },
            grouping: {
                showGroupHeader: true,
                allowDragToGroup: true,
                message: "Drag a column header here to group by a column"
            },
            events: {
                selectionChanged: onSelectionChangedChildGrid
            },
            columns: [
                { field: "componentId", width: 50, title: "ID" },
                { field: "unitCode", title: "Unit" },
                { field: "componentCode", title: "Component" },
                { field: "typeCode", title: "Type" },
                { field: "groupCode", title: "Group" },
                { field: "weightCode", title: "Weight" }
            ]
        });
    }
}

function onSelectionChangedChildGrid(e)
{
    var selectedOrderID = $("#mainGrid").swidget().contentTable.find(".sui-selected").get(0).cells[0].innerHTML;
    var selectedComponentrID = $("#childGrid").swidget().contentTable.find(".sui-selected").get(0).cells[0].innerHTML;

    var secondGrid = $("#resultGrid").swidget();
    if (secondGrid)
    {
        updateResultGridData(selectedOrderID, selectedComponentrID);
    }
    else
    {
        $("#resultGrid").shieldGrid(
        {
            dataSource:
            {
                remote:
                {
                    read:
                    {
                        url: "/WebToxPi/rest/results/getresult/" + selectedOrderID + "/" + selectedComponentrID,
                        dataType: "json"
                    }
                },
                schema: {
                    total: function (result) {
                        return result["odata.count"];
                    },
                    fields:
                        window.orderFields =
                        {
        	                "resultId": { path: "resultId" },
        	                "casrnCode": { path: "casrnCode" },
        	                "componentCode": { path: "componentCode" },
        	                "resultValue": { path: "resultValue" },
        	                "unitCode": { path: "unitCode" }
                }
            }
            },
            paging: true,
            sorting: true,
            selection: {
                type: "row",
                multiple: false
            },
            filtering: {
                enabled: true
            },
            columns: [
                      { field: "resultId", width: 50, title: "ID" },
                      { field: "casrnCode", title: "CASRN" },
                      { field: "componentCode", title: "Component" },
                      { field: "resultValue", title: "Value", format: function(value) {return "<strong>" + value + "</strong>";}  },
                      { field: "unitCode", title: "Unit" }
            ]
        });
    }
}

function updateChildGridData(selectedItemID)
{
    $.ajax({
        url: "/WebToxPi/rest/results/getcomponents/" + selectedItemID
    }).then(function(griddata)
    {
    	refreshGird("#childGrid", griddata);
    });
}

function updateResultGridData(selectedOrderID, selectedComponentrID)
{
    $.ajax({
        url: "/WebToxPi/rest/results/getresult/" + selectedOrderID + "/" + selectedComponentrID
    }).then(function(griddata)
    {
    	refreshGird("#resultGrid", griddata);
    });
}

function refreshGird(gridname, griddata)
{
    var grid = $(gridname).swidget(), initialOptions = grid.initialOptions;
    initialOptions.dataSource = { data: griddata};
    grid.refresh(initialOptions);
}
</script>



<script type="text/javascript">
        $(function () {
            $("#chart1").shieldChart({
                theme: "light",
                exportOptions: {
                    image: false,
                    print: false
                },
                primaryHeader: {
                    text: "Browsers Popularity amongst Users"
                },
                chartLegend: {
                    enabled: true
                },
                seriesSettings: {
                    pie: {
                        enablePointSelection: true        
                    }
                },
                dataSeries: [{
                    seriesType: "pie",
                    collectionAlias: "Usage",
                    data: [
                        ["IE", 9.0],                        
                        { collectionAlias: "Firefox", y: 26.8, selected: true },
                        ["Chrome", 55.8],
                        ["Safari", 3.8],
                        ["Opera", 1.9]
                    ]
                }]
            });
        });

        $(function () {
            $("#chart2").shieldChart({
                theme: "light",
                exportOptions: {
                    image: false,
                    print: false
                },
                axisX: {
                    categoricalValues: ['2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012']
                },
                axisY: {
                    axisTickText: {
                        format: "{text:c}"
                    },
                    title: {
                        text: "Price (EUR per kWh)"
                    }
                },
                tooltipSettings:{
                    chartBound:true
                }, 
                seriesSettings: {
                    line: {
                        enablePointSelection: true,
                        pointMark: {
                            activeSettings: {
                                pointSelectedState: {
                                    drawWidth: 4,
                                    drawRadius: 4
                                }
                            }
                        }
                    }
                },
                primaryHeader: {
                    text: "Electricity prices"
                },
                dataSeries: [{
                    seriesType: 'line',
                    collectionAlias: 'Households',
                    data: [0.164, 0.173, 0.184, 0.167, 0.177, 0.189, 0.180, 0.183, 0.188, 0.160, 0.176, 0.178]
                }, {
                    seriesType: 'line',
                    collectionAlias: 'Industry',
                    data: [0.103, 0.105, 0.112, 0.111, 0.102, 0.099, 0.110, 0.113, 0.117, 0.119, 0.123, 0.117]
                }]
            });
        });


        $(function () {
            $("#chart3").shieldChart({
                theme: "light",                
                axisX: {
                    categoricalValues: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"]
                },
                axisY: {
                    max: 2000000
                },
                primaryHeader: {
                    text: "Site usage statistics"
                },
                chartLegend: {
                    align: "center",
                    verticalAlign: "top"
                },
                dataSeries: [{
                    seriesType: "polarbar",
                    collectionAlias: "Total Visits",
                    data: [565000, 630400, 743000, 910200, 1170200, 1383000, 1333000, 1777000, 1355000, 1002000, 1456000, 1765000]
                }]
            });
        });
    </script>
</body>
</html>