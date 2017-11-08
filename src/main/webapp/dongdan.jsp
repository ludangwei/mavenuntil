<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>websocket client</title>
</head>
<script src="js/jquery-1.10.2.min.js"></script>
<% String name= request.getParameter("username");
    request.setAttribute("username",name);
%>
<script>
    var con;
    con=new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}/${pageContext.request.contextPath}/movemessage/${username}");
    function moveMessage(){
        con.onmessage=function(msg){
            var mes=JSON.parse(msg.data);
            $("#frametalk").append(mes.date+"<br>"+mes.username+":"+mes.content+"<br>");
            $("#frametalk").scrollTop($("#frametalk")[0].scrollHeight)
        }
    }

    function sendMes(){
       var mes= $("#divText").val();
       con.send(JSON.stringify({content:mes,username:"${username}"}))
    }

</script>
<body>
    <input type="button" value="连接" onclick="moveMessage()"/>
    <div id="frametalk">
    </div>
    <div id="inputDiv">
        <textarea id="divText"></textarea>
    </div>
    <button onclick="sendMes()">发送</button>
</body>
</html>
