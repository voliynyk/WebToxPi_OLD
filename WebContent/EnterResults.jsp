<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="edu.tamu.webtoxpi.Client.DataManager.DataManager" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Weights" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Groups" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Types" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Componentsources" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Components" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Sources" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Chemicals" %>
<%@ page import="edu.tamu.webtoxpi.Server.Models.Classes.Casregistrynumbers" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WebToxPi - Enter Results</title>

<link id="themecss" rel="stylesheet" type="text/css" href="//www.shieldui.com/shared/components/latest/css/dark-bootstrap/all.min.css" />
<script type="text/javascript" src="/WebToxPi/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
<link rel="stylesheet" href="css/enterresults.css" />

<script>
var dropDownWidth = 200;
var dropDownHeight = 200;
var inputWidth = 200;
var inputHeight = 25;

function checkInput(radio) {
	
	radio.checked = true;
     document.getElementById("inputForm").action='NewResultsServlet?TypeOfInput=' + radio.value ;
     
}
    </script>

</head>
<body style="overflow:hidden;">

        
        
<ul class="slides">
<!-----------------------------------------------------------------------------------------------SLIDE 1---------------------------------------------------------------------------------------------------------------->

    <input class="radioinput" type="radio" name="radio-btn" id="sld-1" checked/>
    <li class="slide-container">
		<div class="slide">
  <div class="outerDiv">
    <label >Select how you want to input results:</label>
    <div class="rb-container">
        <input type="radio" id="radio1" name="option" value="ByChemichal" onclick="checkInput('ByChemichal');"/>
        <br />
        <input type="radio" id="radio2" name="option" value="ByComponent" onclick="checkInput('ByComponent');"/>        
    </div>
 </div>
        </div>
		<div class="nav">
			
			<label class="next" onclick="NextSlide()">&#x203a;</label>
		</div>
    </li>
<!-----------------------------------------------------------------------------------------------SLIDE 3---------------------------------------------------------------------------------------------------------------->

    <input class="radioinput" type="radio" name="radio-btn" id="sld-3" />
    <li class="slide-container">
    
     <form action="NewResultsServlet" role="form" method="post" id="inputForm">
     
        <div class="slide">
          <div class="outerDiv">
            <label for="chemicalsource">Select Chemical Source:</label>
            <br />
            <input id="chemicalsource" name="chemicalsource" />
            <br />
            <label for="casrn">Select CAS Number:</label>
            <br />
            <input id="casrn" name="casrn"/>
            <br />
            <label for="chemical">Select Chemical:</label>
            <br />
            <input id="chemical" name="chemical"/>
            
    </div>
         
        </div>
		<div class="nav">
			<label for="sld-1" class="prev" >&#x2039;</label>
 			<label class="next" style="top: 60%;">Submit
<button  type="submit" class="btn btn-default" onclick="SubmitResults()"></button>
</label>
		</div>
		 
    </form>
    </li>
<!-----------------------------------------------------------------------------------------------SLIDE 2---------------------------------------------------------------------------------------------------------------->

    <input class="radioinput" type="radio" name="radio-btn" id="sld-2" />
    <li class="slide-container">
    
     <form action="NewResultsServlet" role="form" method="post" id="inputForm">
     
        <div class="slide">
          <div class="outerDiv">
            <label for="weight">Select Weight:</label>
            <br />
            <input id="weight" name="weight" />
            <br />
            <label for="sliceName">Select Slice:</label>
            <br />
            <input id="sliceName" name="sliceName"/>
            <br />
            <label for="sliceType">Select Slice Type:</label>
            <br />
            <input id="sliceType" name="sliceType"/>
            <br />
            <label for="componentSourceID">Select Component Source:</label>
            <br />
            <input id="componentSourceID" name="componentSourceID"/>
            <br />
            <label for="componentName">Select Component:</label>
            <br />
            <input id="componentName" name="componentName" style="width: 10px;"/>
            
    </div>
         
        </div>
		<div class="nav">
			<label for="sld-1" class="prev" position: fixed; left: 90px;>&#x2039;</label>
 			<label class="next" style="top: 60%;">Submit
<button  type="submit" class="btn btn-default" onclick="SubmitResults()"></button>
</label>
		</div>
		 
    </form>
    </li>
</ul>



   <script type="text/javascript">
   //-----------------------------------------------------------------------------------------------BY CHEMICAL----------------------------------------------------------------------------------------------------------------
   <% 
   ArrayList<String> lChSource = new ArrayList<String>();
       for (Sources chSource : DataManager.getChSources())
   	{
    	   lChSource.add(chSource.getCode());
   	}
       String arrChSource = lChSource.toString();
   %>
   var dataChSource = '<%= arrChSource %>';
   dataChSource = dataChSource.slice(1, dataChSource.length - 1);
   var inputChSource = dataChSource.split(", ");  
   
   <% 
   ArrayList<String> lChemicals = new ArrayList<String>();
       for (Chemicals chemical : DataManager.getChemicals())
   	{
    	   lChemicals.add(chemical.getCode());
   	}
       String arrChemicals = lChemicals.toString();
   %>
   var dataChemicals = '<%= arrChemicals %>';
   dataChemicals = dataChemicals.slice(1, dataChemicals.length - 1);
   var inputChemicals = dataChemicals.split(", ");  
   
   <% 
   ArrayList<String> lCasregistrynumbers = new ArrayList<String>();
       for (Casregistrynumbers casregistrynumber : DataManager.getCasregistrynumbers())
   	{
    	   lCasregistrynumbers.add(casregistrynumber.getCode());
   	}
       String arrCasregistrynumbers = lCasregistrynumbers.toString();
   %>
   var dataCasregistrynumbers = '<%= arrCasregistrynumbers %>';
   dataCasregistrynumbers = dataCasregistrynumbers.slice(1, dataCasregistrynumbers.length - 1);
   var inputCasrn = dataCasregistrynumbers.split(", ");  
       
 //-----------------------------------------------------------------------------------------------BY COMPONENT----------------------------------------------------------------------------------------------------------------
   <% 
   ArrayList<String> weights = new ArrayList<String>();
       for (Weights weight : DataManager.getWeights())
   	{
       	weights.add(weight.getCode());
   	}
       String arrWeights = weights.toString();
   %>
   var dataWeights = '<%= arrWeights %>';
   dataWeights = dataWeights.slice(1, dataWeights.length - 1);
   var inputWeights = dataWeights.split(", ");
   
   <% 
   ArrayList<String> groups = new ArrayList<String>();
       for (Groups group : DataManager.getGroups())
   	{
        groups.add(group.getCode());
   	}
       String arrGroups = groups.toString();
   %>
   var dataGroups = '<%= arrGroups %>';
   dataGroups = dataGroups.slice(1, dataGroups.length - 1);
   var inputGroups = dataGroups.split(", ");
   
   <% 
   ArrayList<String> types = new ArrayList<String>();
       for (Types type : DataManager.getTypes())
   	{
    	   types.add(type.getCode());
   	}
       String arrTypes = types.toString();
   %>
   var dataTypes = '<%= arrTypes %>';
   dataTypes = dataTypes.slice(1, dataTypes.length - 1);
   var inputTypes = dataTypes.split(", ");
   
   <% 
   ArrayList<String> componentsources = new ArrayList<String>();
       for (Componentsources componentsource : DataManager.getComponentSources())
   	{
    	   componentsources.add(componentsource.getCode());
   	}
       String arrComponentsources = componentsources.toString();
   %>
   var dataComponentsources = '<%= arrComponentsources %>';
   dataComponentsources = dataComponentsources.slice(1, dataComponentsources.length - 1);
   var inputComponentsources = dataComponentsources.split(", ");
   
   <% 
   ArrayList<String> components = new ArrayList<String>();
       for (Components component : DataManager.getComponents())
   	{
    	   components.add(component.getCode());
   	}
       String arrComponents = components.toString();
   %>
   var dataComponents = '<%= arrComponents %>';
   dataComponents = dataComponents.slice(1, dataComponents.length - 1);
   var inputComponents = dataComponents.split(", ");
   
       jQuery(function ($) {
           $("#casrn").shieldComboBox({
               dataSource: {
                   data: inputCasrn
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#chemicalsource").shieldComboBox({
               dataSource: {
                   data: inputChSource
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#chemical").shieldComboBox({
               dataSource: {
                   data: inputChemicals
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#weight").shieldComboBox({
               dataSource: {
                   data: inputWeights
               },
               autoComplete: {
                   enabled: true
               },
               events: {
                   change: function(e) {
                	   weightChanged();
                   }
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#sliceName").shieldComboBox({
               dataSource: {
                   data: inputGroups
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#sliceType").shieldComboBox({
               dataSource: {
                   data: inputTypes
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#componentSourceID").shieldComboBox({
               dataSource: {
                   data: inputComponentsources
               },
               autoComplete: {
                   enabled: true
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
           $("#componentName").shieldComboBox({
               dataSource: {
                   data: inputComponents
               },
               autoComplete: {
                   enabled: true
               },
               events: {
                   change: function(e) {
                       alert("change event");
                   }
               },
               width: inputWidth,
               height: inputHeight,
               dropDownHeight: dropDownHeight,
                dropDownWidth: dropDownWidth
           });
//            $("#submit").shieldButton({
//                events: {
//                    click: function () {
//                        var technology = $("#comboBoxTech").swidget().value();
//                        var years = $("#comboBoxYears").swidget().value();
//                        alert("You selected: \n Technology: " + technology + "\n Years: " + years);
//                    }
//                }
//            });

           $("#radio1").shieldRadioButton({
               label: "By Chemichal",
               checked: true,
               events: {
                   click: function(e) {
                	   checkInput($('#radio1')[0]);
                   }
               }
               
           });
           $("#radio2").shieldRadioButton({
               label: "By Component",
               enabled: true,
               checked: false,
               events: {
                   click: function(e) {
                	   checkInput($('#radio2')[0]);
                   }
               }
           });
           
       });
   
   function SubmitResults(){
	   
	   top.location.href = "GeneralPage.jsp";
   }
   function NextSlide(){
	   if ($('#radio1')[0].checked)
		  {
		    $('#sld-1')[0].checked = false;
		    $('#sld-3')[0].checked = true;
		  }		  
	   if ($('#radio2')[0].checked)
		  {
		    $('#sld-1')[0].checked = false;
		    $('#sld-2')[0].checked = true;
		  }	
	   
   }
   function weightChanged(){
	   if ($("#weight").val() !== null && $("#weight").val() !== undefined)
	   {
		   var weight = $("#weight").val();
		   var groupsByWeight = [];
		   var sGroupsByWeight;
		   $.ajax({
		        url: "/WebToxPi/rest/results/getgroups/" + weight
		    }).then(function(griddata)
		    {
		    	groupsByWeight = griddata;		        
		    })
			sGroupsByWeight = groupsByWeight.toString();
		   
		    inputGroupsByWeight = sGroupsByWeight.split(",");
		   
		    var combo = $("#sliceName").swidget(),
		    options = combo.initialOptions;
		    options.dataSource.data = inputGroupsByWeight;
		    combo.refresh(options);
	   }
   }
   </script>
</body>
</html>