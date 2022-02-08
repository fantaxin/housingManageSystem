<%@ page import="com.example.Entity.Apartment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房源管理系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <style>

        .ll{position:relative;display:inline-block;*display:inline;*zoom:1;vertical-align:middle;line-height:60px}
        .ll{display:block;padding:0 20px;color:#fff;color:rgba(255,255,255,.7);transition:all .3s;-webkit-transition:all .3s}
        .selected{background-color:#009688}
    </style>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>

        function delete_apartment(id)
        {
            $.ajax({//发起ajax请求
                type : "get",//"post"
                url : "${pageContext.request.contextPath}/DeleteApartment",
                data : {"id" : id },
                cache : false,//true
                async : true,//false
                success : function (data)
                {
                    //success code
                    return data;
                },
                error : function(XMLHttpReques , textStatus, errorThrown){
                    //error code
                    //alert(errorThrown);
                }
            });
        }

        function update_apartment(id,price)
        {
            $.ajax({//发起ajax请求
                type : "post",//"post"
                url : "${pageContext.request.contextPath}/UpdateApartment",
                data : {"id" : id , "price" : price},
                cache : false,//true
                async : true,//false
                datatype : "text",
                success : function (data)
                {
                    //success code
                    return data;
                },
                error : function(XMLHttpReques , textStatus, errorThrown){
                    //error code
                    //alert(errorThrown);
                }
            });
        }

        function insert_apartment(community_name,community_type,type,price,jarea,tarea,face,allfloor,time,floor)
        {
            var res = "1";
            $.ajax({//发起ajax请求
                type : "get",//"post"
                url : "${pageContext.request.contextPath}/InsertApartment",
                data : {"community_name" : community_name ,
                    "community_type" : community_type ,
                    "type" : type ,
                    "price" : price ,
                    "jarea" : jarea ,
                    "tarea" : tarea ,
                    "face" : face ,
                    "allfloor" : allfloor ,
                    "time" : time ,
                    "floor" : floor ,},
                cache : false,//true
                async : true,//false
                datatype : "text",
                success : function (data)
                {
                    //success code
                    alert(data)
                    return data;
                },
                error : function(XMLHttpReques , textStatus, errorThrown){
                    //error code
                    //alert(errorThrown);
                }
            });
            return res;
        }
    </script>
</head>
<body>
<form class="layui-form"
      action="${pageContext.request.contextPath}/ManageServlet"
      id="id_manage_search_apartment_form"
      name="manage_search_apartment_form"
>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">房源管理系统</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>


            <p class="ll">欢迎，${user.account}</p>


        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="ll">

                <p></p>

            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <dd><a href="main.jsp">首页</a></dd>
                    <dd><a href="search_map.jsp">房源查询</a></dd>
                    <dd class="selected"><a href="manage.jsp" >房源管理</a></dd>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="select_district" id="select_district">
                    <option value="">请选择行政区</option>
                    <option value="洪山区">洪山区</option>
                    <option value="武昌区">武昌区</option>
                    <option value="江夏区">江夏区</option>
                    <option value="黄陂区">黄陂区</option>
                    <option value="汉阳区">汉阳区</option>
                    <option value="汉南区">汉南区</option>
                    <option value="新洲区">新洲区</option>
                    <option value="江岸区">江岸区</option>
                    <option value="硚口区">硚口区</option>
                    <option value="青山区">青山区</option>
                    <option value="江汉区">江汉区</option>
                    <option value="蔡甸区">蔡甸区</option>
                    <option value="东西湖区">东西湖区</option>
                </select>
            </div>
        </div>

        <input type="text" id="type1" name="type" placeholder="请输入户型">
        <input type="text" id="price1" name="price" placeholder="请输入总价">
        <input type="text" id="avgprice1" name="avgprice" placeholder="请输入均价">
        <input type="text" id="jarea1" name="jarea" placeholder="请输入建筑面积">
        <input type="text" id="tarea1" name="tarea" placeholder="请输入套内面积">
        <input type="text" id="face1" name="face" placeholder="请输入朝向">
        <input type="text" id="community_name1" name="community_name" placeholder="请输入小区名称">
        <div class="layui-input-inline">
        <select name="select_type" id="select_type">
            <option value="">请选择小区类型</option>
            <option value="住宅">住宅</option>
            <option value="别墅">别墅</option>
        </select></div>
        <div class="layui-input-inline">
            <select name="select_floor" id="select_floor">
                <option value="">请选择所在楼层</option>
                <option value="低楼层">低楼层</option>
                <option value="中楼层">中楼层</option>
                <option value="高楼层">高楼层</option>
            </select></div>
        <input type="text" id="allfloor1" name="allfloor" placeholder="请输入总楼层">
        <input type="text" id="time1" name="time" placeholder="请输入建造时间">

        <button id="id_submit">查询</button>
        <button id="id_insert_apartment" type="button" onclick="insertRow()">插入</button>

        <table class="layui-table" border = "1" id="table">
            <tr>
                <td>小区名称</td>
                <td>小区类型</td>
                <td>房源户型</td>
                <td>总价</td>
                <td>均价</td>
                <td>建筑面积</td>
                <td>朝向</td>
                <td>所在楼层</td>
                <td>总楼层</td>
                <td>建造时间</td>
            </tr>
            <%
                List<Apartment> list = (List<Apartment>) request.getAttribute("apartment_list");
                if(list!=null){
                for(Apartment t:list){%>
            <tr>
                <td id="community_name"><%=t.getCommunity_name() %></td>
                <td id="community_type"><%=t.getCommunity_type() %></td>
                <td id="type"><%=t.getType() %></td>
                <td id="price"><%=t.getPrice() %></td>
                <td id="avgprice"><%=t.getAvgprice() %></td>
                <td id="jarea"><%=t.getJarea() %></td>
                <td id="face"><%=t.getFace() %></td>
                <td id="floor"><%=t.getFloor() %></td>
                <td id="allfloor"><%=t.getAllfloor() %></td>
                <td id="time"><%=t.getTime() %></td>
                <td id="id" style="display: none"><%=t.getId() %></td>
                <td><button type="button" id="table_delete" onclick="deleteRow(this)">删除</button>
                    <input type="button" value="修改" id="updata" onclick="updateRow(this)"></td>
            </tr>
            <%}}
            %>
        </table>
    </div>


    <div class="layui-footer">
        <!-- 底部固定区域 -->
        底部固定区域
    </div>
</div>
</form>
<script src="./layui/layui.js"></script>
<script>


    $("#id_submit").click(function (){
        $("#id_manage_search_apartment_form").submit();
    })

    function insertRow(){
        var community_name = document.getElementById("community_name1").value;
        var community_type = document.getElementById("select_type").value;
        var type = document.getElementById("type1").value;
        var price = document.getElementById("price1").value;
        var jarea = document.getElementById("jarea1").value;
        var tarea = document.getElementById("tarea1").value;
        var face = document.getElementById("face1").value;
        var allfloor = document.getElementById("allfloor1").value;
        var time = document.getElementById("time1").value;
        var floor = document.getElementById("select_floor").value;
        insert_apartment(community_name,community_type,type,price,jarea,tarea,face,allfloor,time,floor);
        var res="1";
        if(res==="1") {
            var table = document.getElementById("table");
            var row = table.insertRow(1);
            row.insertCell(0).innerText = community_name;
            row.insertCell(1).innerText = community_type;
            row.insertCell(2).innerText = type;
            row.insertCell(3).innerText = price;
            row.insertCell(4).innerText = "0";
            row.insertCell(5).innerText = jarea;
            row.insertCell(6).innerText = face;
            row.insertCell(7).innerText = floor;
            row.insertCell(8).innerText = allfloor;
            row.insertCell(9).innerText = time;
            row.cells[4].innerText = ((parseInt(price)) * 10000 / (parseInt(row.cells[5].innerText))).toFixed(1);
        }
        else{
        }
    }

    function updateRow(obj){
        var price = prompt("修改后的总价为：");
        if(price==null||price==="")
            return;
        var trObj =obj.parentNode;
        var rowObj = trObj.parentElement;
        rowObj.cells[3].innerText=price;
        rowObj.cells[4].innerText=((parseInt(price)) * 10000 / (parseInt(rowObj.cells[5].innerText))).toFixed(1);
        update_apartment(rowObj.cells[10].innerHTML,price);
    }

    function deleteRow(obj){
        /*var currentRow=$(this).closest("tr");*/
        /*var index = $(this).parentNode.index();*/
        var trObj =obj.parentNode;
        var rowObj = trObj.parentElement;
        var index = rowObj.rowIndex;
        if(window.confirm('是否删除该条房源信息？'+rowObj.cells[10].innerHTML)){
            var table = document.getElementById("table");
            table.deleteRow(index);
            delete_apartment(rowObj.cells[10].innerHTML);
            return true;
        }
        else{
            return false;
        }
    }
</script>

</body>
</html>
