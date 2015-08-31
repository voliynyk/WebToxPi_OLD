<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
<title>View Results</title>

    
    <link id="themecss" rel="stylesheet" type="text/css" href="//www.shieldui.com/shared/components/latest/css/light/all.min.css" />
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>

</head>
<body class="theme-light">
<div id="outerContainer">
    <div id="innerContainer">
        <div>
            <span class="paneheader">Horizontal splitter pane 1</span>
        </div>
        <div>
            <span class="paneheader">Horizontal splitter pane 2</span>
        </div>
        <div>
            <span class="paneheader">Horizontal splitter pane 3</span>
        </div>
    </div>
    <div>
        <span class="paneheader">Vertical splitter pane 2</span>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        var verticalSplitter = $("#outerContainer").shieldSplitter({
            orientation: "vertical"
        });
        var horizontalSplitter = $("#innerContainer").shieldSplitter({
            orientation: "horizontal",
            panes: [
				{ size: '280px', collapsible: true },
				{ size: '350px' },
				{ collapsible: true }
            ]
        });
    });
</script>
<style>
    #outerContainer {
        height: 300px;
    }
    .paneheader {
        margin-left: 2px;
        font-weight: bold;
        font-size: 14px;
        font-family: Arial;
    }
</style> 
</body>
</html>