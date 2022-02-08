<%--
  Created by IntelliJ IDEA.
  User: 雁过无痕
  Date: 2021/6/28
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房源管理系统-用户登录</title>
    <link rel="shortcut icon" href="#"/>
    <style>
        #id_login_div{
            width: 300px;
            height: 250px;
            margin-left: -150px;
            margin-top: -250px;
            padding: 15px 30px 15px;
        }
        #id_register_div{
            width: 300px;
            height: 275px;
            margin-left: -150px;
            margin-top: -250px;
            padding: 5px 30px 15px;
        }

        #id_password2{
            margin-right: -2px;
        }
        #id_password3{
            margin-right: -63px;
        }

        .login_div {
            overflow: hidden;
            position: absolute;
            left: 50%;
            top: 50%;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            z-index: 1;
            box-sizing: border-box;
            text-align: center;
        }

        .login_div::before {
            content: "";
            position: absolute;
            top: 0px;
            left: 0px;
            right: 0px;
            bottom: 0px;
            z-index: -1;  /*-1 可以当背景*/
            filter: blur(6px);
            margin: -30px;
            /*消除边缘透明*/
            background-size: cover;
            /*位置固定*/
            background: fixed center top;
        }

        .btn{
            background-color: rgba(40, 129, 172,0.6); /* Green */
            width: 100%;
            border: none;
            color: white;
            padding: 8px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
        }

        .btn:hover{
            background-color: rgba(36, 107, 147, 0.8);
        }

        body{
            background: url(resource/4.png) no-repeat center center fixed;
            /*兼容浏览器版本*/
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        .a_div{
            margin-top: 15px;
        }
        .register{
            color: #236787;
            text-decoration: none;
        }


    </style>

</head>
<body >


<form action="${pageContext.request.contextPath}/LoginServlet" id="id_login_form" name="login_form">
    <div class="login_div" id="id_login_div">
        <h3>欢迎使用房源管理系统</h3>
        <p><label>用户名：</label><input class="login_text" type="text" name="account" id="id_account"></p>
        <p><label>密&nbsp&nbsp&nbsp码：</label><input class="login_text" type="password" name="password" id="id_password"></p>
        <button id="id_btn_login" class="btn">登录</button>
        <input name="flag" value="1" style="display: none">
        <div class="a_div">
            <a href="#" class="register" onclick="MyRegister()">没有账号? 注册一个吧</a>
        </div>
    </div>
</form>


<form action="${pageContext.request.contextPath}/LoginServlet" id="id_register_form">
    <div class="login_div" id="id_register_div" style="display: none">
        <h3>用户注册</h3>
        <p><label>用户名：</label><input class="login_text" type="text" name="account" id="id_account2"></p>
        <p><label>密&nbsp&nbsp&nbsp码：</label><input class="login_text" type="password" name="password" id="id_password2"></p>
        <p><input class="login_text" type="password" id="id_password3" placeholder="请再次输入密码"></p>
        <button id="id_btn_register" class="btn">注册</button>
        <input name="flag" value="2" style="display: none">
        <div class="a_div">
            <a href="##" class="register" onclick="MyLogin()">已有账号? 立即登录</a>
        </div>
    </div>
</form>


<script>
    function MyRegister(){
        document.getElementById("id_login_div").style.display='none';
        document.getElementById("id_register_div").style.display='';
    }

    function MyLogin(){
        document.getElementById("id_register_div").style.display='none';
        document.getElementById("id_login_div").style.display='';
    }
</script>
<script>
    var btn = document.getElementById("id_btn_login");
    var account = document.getElementById("id_account");
    var password = document.getElementById("id_password");

    function isEmpty(val) {
        var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
        if (str == '' || str == undefined || str == null) {
            return true;
        } else {
            return false;
        }
    }

    btn.onclick = function (){
        if(isEmpty(account.value)) {
            alert("账号不能为空");
            return;
        }
        else if(isEmpty(password.value)){
            alert("密码不能为空");
            return;
        }
        else{
            $("#id_login_form").submit();
        }

    }
</script>
<script>
    var btn2 = document.getElementById("id_btn_register");
    var account2 = document.getElementById("id_account2");
    var password2 = document.getElementById("id_password2");
    var password3 = document.getElementById("id_password3");

    btn2.onclick = function (){
        if(isEmpty(account2.value)) {
            alert("账号不能为空");
            return;
        }
        else if(isEmpty(password2.value)||isEmpty(password3.value)){
            alert("密码不能为空");
            return;
        }
        else if(password2.value !== password3.value){
            alert("两次密码输入不一致");
            return;
        }
        else{
            $("#id_register_form").submit();
        }

    }
</script>

</body>
</html>
