<%@ page import="com.example.Servlet.ApartmentServlet" %>
<%@ page import="com.example.Entity.Community" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px}
        #container{height:100%}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=HZGOs77eiZOPkoG96XWZcbjW1nehZAK4">
        //v3.0版本的引用方式：src="http://api.map.baidu.com/api?v=3.0&ak=HZGOs77eiZOPkoG96XWZcbjW1nehZAK4"
    </script>

</head>
<body>

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

                <p> tester </p>

            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <dd><a href="main.jsp">首页</a></dd>
                    <dd class="selected"><a href="search_map.jsp">房源查询</a></dd>
                    <dd><a href="manage.jsp">房源管理</a></dd>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->

        <button onclick="display_apartment()">按条件查询房源</button>
        <button onclick="display_apartment_bypoi()">按周边查询房源</button>
        <button onclick="display_poi()">查询房源周边设施</button>

        <form id="id_search_apartment_form" action="${pageContext.request.contextPath}/ApartmentServlet">
            <div id="id_apartment_div" style="display: none">
                <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="select_district">
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
                <input type="text" name="type" placeholder="请输入户型">
                <input type="text" name="price" placeholder="请输入总价">
                <input type="text" name="avgprice" placeholder="请输入均价">
                <input type="text" name="jarea" placeholder="请输入建筑面积">
                <input type="text" name="tarea" placeholder="请输入套内面积">
                <input type="text" name="face" placeholder="请输入朝向">
                <input type="text" name="community_name" placeholder="请输入小区名称">
                <input type="text" name="time" placeholder="请输入时间">
                <button id="id_apartment_submit">查询</button>
            </div>
        </form>

        <form id="id_search_apartment_bypoi_form" action="${pageContext.request.contextPath}/ApartmentByPOIServlet">
            <div id="id_apartment_bypoi_div" style="display: none">
                <input type="text" name="poi_name" placeholder="请输入周边配套名称">
                <button id="id_apartment_bypoi_submit">查询</button>
            </div>
        </form>

        <form id="id_search_poi_form" action="${pageContext.request.contextPath}/POIServlet">
            <div id="id_poi_div" style="display: none">
                <input type="text" name="c_name" placeholder="请输入小区名称">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select name="c_type">
                            <option value="住宅">请选择小区类型</option>
                            <option value="住宅">住宅</option>
                            <option value="别墅">别墅</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select name="select_poi_type">
                            <option value="">请选择周边配套类型</option>
                            <option value="1">交通设施</option>
                            <option value="3">公园广场</option>
                            <option value="4">学校教育</option>
                            <option value="5">医院诊所</option>
                            <option value="8">商店购物</option>
                        </select>
                    </div>
                </div>
                <button id="id_poi_submit">查询</button>
            </div>
        </form>

        <div id="container"></div>
        <div class="layui-footer">
        <!-- 底部固定区域 -->
        时间
        </div>
    </div>
</div>
<script>
    function display_apartment(){
        document.getElementById("id_apartment_div").style.display = '';
        document.getElementById("id_apartment_bypoi_div").style.display = 'none';
        document.getElementById("id_poi_div").style.display = 'none';
    }
    function display_apartment_bypoi(){
        document.getElementById("id_apartment_div").style.display = 'none';
        document.getElementById("id_apartment_bypoi_div").style.display = '';
        document.getElementById("id_poi_div").style.display = 'none';
    }
    function display_poi(){
        document.getElementById("id_apartment_div").style.display = 'none';
        document.getElementById("id_apartment_bypoi_div").style.display = 'none';
        document.getElementById("id_poi_div").style.display = '';
    }
</script>
<script>
    //2011-7-25
    (function(){        //闭包
        function load_script(xyUrl, callback){
            var head = document.getElementsByTagName('head')[0];
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = xyUrl;
            //借鉴了jQuery的script跨域方法
            script.onload = script.onreadystatechange = function(){
                if((!this.readyState || this.readyState === "loaded" || this.readyState === "complete")){
                    callback && callback();
                    // Handle memory leak in IE
                    script.onload = script.onreadystatechange = null;
                    if ( head && script.parentNode ) {
                        head.removeChild( script );
                    }
                }
            };
            // Use insertBefore instead of appendChild  to circumvent an IE6 bug.
            head.insertBefore( script, head.firstChild );
        }
        function translate(point,type,callback,content){
            var callbackName = 'cbk_' + Math.round(Math.random() * 10000);    //随机函数名
            var xyUrl = "http://api.map.baidu.com/ag/coord/convert?from="+ type + "&to=4&x=" + point.lng + "&y=" + point.lat + "&callback=BMap.Convertor." + callbackName;
            //动态创建script标签
            load_script(xyUrl);
            BMap.Convertor[callbackName] = function(xyResult){
                delete BMap.Convertor[callbackName];    //调用完需要删除改函数
                var point = new BMap.Point(xyResult.x, xyResult.y);
                callback && callback(point,content);
            }
        }

        window.BMap = window.BMap || {};
        BMap.Convertor = {};
        BMap.Convertor.translate = translate;
    })();
</script>
<script type="text/javascript">

    var map = new BMap.Map("container");
    map.addControl(new BMap.NavigationControl());
    map.enableScrollWheelZoom(); //启用滚轮放大缩小
    point = new BMap.Point(114.32,30.55)
    map.centerAndZoom(point, 12.5);
    map.setMapStyleV2({
        styleId: 'e0afbfc3c06a46e0ec88e4d4812867e0'
    });

    var cobjs;
    var flag = <%= request.getAttribute("flag")%> ;
    var cstr = '<%= request.getAttribute("clist")%>' ;
    cobjs = JSON.parse(cstr);

    //坐标转换完之后的回调函数
    translateCallback = function (point, content){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        addIfoWindow(marker,content)
    }

    if(flag===1) {
        for (var i = 0; i < cobjs.length; i++) {
            var community_point = new BMap.Point(cobjs[i].x, cobjs[i].y);
            BMap.Convertor.translate(community_point, 0, translateCallback, doContent(cobjs[i].name, cobjs[i].type, i));
            if (i === cobjs.length - 1) {
                map.centerAndZoom(community_point, 13.5);
            }
        }
    }
    else{
        for (var i = 0; i < cobjs.length; i++) {
            var poi_point = new BMap.Point(cobjs[i].x, cobjs[i].y);
            BMap.Convertor.translate(poi_point, 0, translateCallback, poiContent(cobjs[i].name, cobjs[i].type));
            if (i === cobjs.length - 1) {
                map.centerAndZoom(poi_point, 16);
            }
        }
    }
    //自定义一个函数用于拼接显示内容
    function poiContent(name, type){
        var str = "<div class='infoBoxContent'><div><strong><p>"+
            name+ "</p></strong><span class='text'>"+
            type+ "</span></div><div class='list'></div></div>";
        return str;
    }
    function doContent(name, type, i) {
        var str = "<div class='infoBoxContent'><div><strong>"+
        name+ "</strong><span class='text'>"+
        type+ "</span></div><div class='list'><ul>";
        var aobjs = cobjs[i].alist;
        for(var n = 0; n<aobjs.length; n++) {
            str += "<li><div class='left'><p>" +
                aobjs[n].type+" "+aobjs[n].jarea+"m²  "+aobjs[n].price+"万  "+"朝"+aobjs[n].face+
                "</p></div></li>";
        }
        str += "</ul></div></div>";

        return str;
    }

    // 编写自定义函数,创建标注
    function addIfoWindow(marker, content){
        var infoWindow = new BMap.InfoWindow(content);
        marker.addEventListener("click", function(){
            this.openInfoWindow(infoWindow);
        });
    }
</script>

</body>
</html>
