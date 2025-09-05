<%--
  Created by IntelliJ IDEA.
  User: gd
  Date: 25. 9. 5.
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Chat Page</h1>
    <div>
        <input type="text" id="msg">
        <button id="send">Send</button>
    </div>
    <script>
        const send = document.getElementById('send')
        const msg = document.getElementById('msg');
        // WebSocket 연결
        const socket = new WebSocket('ws://192.168.1.4/chat')
        socket.addEventListener('open', (e) => {
            console.log('Socket Connected')
        })
        socket.addEventListener('message', (e) => {
            console.log("Message Recieved")
            console.log(e.data)
        })
        socket.addEventListener('close', (e) => {
            console.log('Connection closed')
        })
        socket.addEventListener('error', (e) => {
            console.log('Error')
        })

        send.addEventListener('click', (e) => {
            let m = msg.value
            socket.send(m)
        })
    </script>
</body>
</html>
