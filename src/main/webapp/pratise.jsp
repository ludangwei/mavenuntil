<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="js/jquery-1.10.2.min.js"></script>

<script>
    var xmlrequests;
    $(function () {
        $("#run").bind("click", function () {
          var  xmlrequest=createXMLRequest();
          if(!xmlrequest){
              alert("创建xmlrequest出错！")
              return  false;
          }
          xmlrequest.open("POST","test/demo1.do",true);
          xmlrequests=xmlrequest;
          xmlrequest.onreadystatechange=someMethod;
          xmlrequest.send();
        })
    })

    function someMethod(){
        if(xmlrequests.readyState==4){
            if(xmlrequests.status==200){
               alert(xmlrequests.responseText)
            }
        }
    }
    function createXMLRequest() {
        var xmlrequest;
        if (window.XMLHttpRequest) {
            xmlrequest = new XMLHttpRequest();
        }
        if (window.ActiveXObject) {
            try {
                xmlrequest = new ActiveXObject("Microsoft.XMLHTTP")
            } catch (e) {
                try {
                    xmlrequest = new ActiveXObject("msxml2.XMLHTTP")
                } catch (e) {
                }
            }
        }
        return xmlrequest;
    }
</script>
<body onunload="exitMethod()">
<button id="run">Run</button>
<div style="position: relative;border: 1px solid pink;width: 100%;height: 500px">
    <div id="aaaa" style="background: red;width: 20px;height: 20px"></div>
    <div id="bbbb" style="background: green;width: 20px;height: 20px;position: absolute;top: 0px;"></div>
</div>
</body>
</html>
