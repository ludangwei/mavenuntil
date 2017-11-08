<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'helloworld.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script src="js/echarts.min.js" type="text/javascript"></script>
</head>
<script>
    var a =
    ${aptDate}
    var b =
    ${aptData}
    var c =
    ${aptRealData}
    var d =
    ${aptlist}
    var proName = "${proName}" //预约图表名称
    var productIdSelect = ${productIdSelect} //默认选中的产品

// 生成预约报表

        $(function () {
            $("#appointCount").val(productIdSelect)
            createAppointView(a, b, c, proName);
        })
    function createAppointView(timelist, appointData, markData, proName) {
        var startTime;
        if (timelist.length >= 7) {
            startTime = timelist[timelist.length - 7].date;
        } else {
            startTime = timelist[0].date;
        }
        var myChart2 = echarts.init(document.getElementById('mainBar'));
        var option2 = {
            // 标题，每个图表最多仅有一个标题控件，每个标题控件可设主副标题
            title: {
                // 主标题文本，'\n'指定换行
                text: proName + '预约单统计',
                // 主标题文本超链接
                // link:
                // 'http://www.tqyb.com.cn/weatherLive/climateForecast/2014-01-26/157.html',
                // 副标题文本，'\n'指定换行
                // subtext: 'www.stepday.com',
                // 副标题文本超链接
                // sublink: 'http://www.stepday.com/myblog/?Echarts',
                // 水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
                x: 'left',
                // 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' |
                // {number}（y坐标，单位px）
                y: 'top'
            },
            // 提示框，鼠标悬浮交互时的信息提示
            tooltip: {
                // 触发类型，默认（'item'）数据触发，可选为：'item' | 'axis'
                trigger: 'axis'
            },
            dataZoom: [{
                startValue: startTime
            }, {
                type: 'inside'
            }],

            // 图例，每个图表最多仅有一个图例
            legend: {
                // 显示策略，可选为：true（显示） | false（隐藏），默认值为true
                show: true,
                // 水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right' |
                // {number}（x坐标，单位px）
                x: 'center',
                // 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' |
                // {number}（y坐标，单位px）
                y: 'bottom',
                // legend的data: 用于设置图例，data内的字符串数组需要与sereis数组内每一个series的name值对应
                // color:['#cccccc','red','blue'],
                data: ['预约金额', '实际签约金额']
            },
            color: ['#696f80', '#ee9ab9'],
            // 工具箱，每个图表最多仅有一个工具箱
            toolbox: {
                // 显示策略，可选为：true（显示） | false（隐藏），默认值为false
                show: true,
                // 启用功能，目前支持feature，工具箱自定义功能回调处理
                feature: {
                    // 辅助线标志
                    mark: {
                        show: true
                    },
                    // dataZoom，框选区域缩放，自动与存在的dataZoom控件同步，分别是启用，缩放后退
                    dataZoom: {
                        show: true,
                        title: {
                            dataZoom: '区域缩放',
                            dataZoomReset: '区域缩放后退'
                        }
                    },
                    // 数据视图，打开数据视图，可设置更多属性,readOnly
                    // 默认数据视图为只读(即值为true)，可指定readOnly为false打开编辑功能
                    dataView: {
                        show: true,
                        readOnly: true
                    },
                    // magicType，动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    // restore，还原，复位原始图表
                    restore: {
                        show: true
                    },
                    // saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'
                    saveAsImage: {
                        show: true
                    }
                }
            },
            // 是否启用拖拽重计算特性，默认关闭(即值为false)
            calculable: true,
            // 直角坐标系中横轴数组，数组中每一项代表一条横轴坐标轴，仅有一条时可省略数值
            // 横轴通常为类目型，但条形图时则横轴为数值型，散点图时则横纵均为数值型
            xAxis: [{
                // 显示策略，可选为：true（显示） | false（隐藏），默认值为true
                show: true,
                // 坐标轴类型，横轴默认为类目型'category'
                type: 'category',
                // 类目型坐标轴文本标签数组，指定label内容。 数组项通常为文本，'\n'指定换行
                data: timelist.map(function (item) {
                    return item.date
                })
            }],
            // 直角坐标系中纵轴数组，数组中每一项代表一条纵轴坐标轴，仅有一条时可省略数值
            // 纵轴通常为数值型，但条形图时则纵轴为类目型
            yAxis: [{
                // 显示策略，可选为：true（显示） | false（隐藏），默认值为true
                show: true,
                // 坐标轴类型，纵轴默认为数值型'value'
                type: 'value',
                // 分隔区域，默认不显示
                splitArea: {
                    show: true
                }
            }],

            // sereis的数据: 用于设置图表数据之用。series是一个对象嵌套的结构；对象内包含对象
            series: [{
                // 系列名称，如果启用legend，该值将被legend.data索引相关
                name: '预约金额',
                // 图表类型，必要参数！如为空或不支持类型，则该系列数据不被显示。
                type: 'line',
                barWidth: 30,
                // 系列中的数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。数组项通常为数值
                data: appointData.map(function (item) {
                    return item.temp
                }),
                // 系列中的数据标注内容
                markPoint: {
                    data: [{
                        type: 'max',
                        name: '最大值'
                    }, {
                        type: 'min',
                        name: '最小值'
                    }]
                },
                // 系列中的数据标线内容
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }
            }, {
                // 系列名称，如果启用legend，该值将被legend.data索引相关
                name: '实际签约金额',
                // 图表类型，必要参数！如为空或不支持类型，则该系列数据不被显示。
                type: 'line',
                barWidth: 30,
                // 系列中的数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。数组项通常为数值
                data: markData.map(function (item) {
                    return item.temp
                }),
                // 系列中的数据标注内容
                markPoint: {
                    data: [{
                        type: 'max',
                        name: '最大值'
                    }, {
                        type: 'min',
                        name: '最小值'
                    }]
                },
                // 系列中的数据标线内容
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }
            }]
        };
        // 为echarts对象加载数据

        myChart2.setOption(option2);
    }

    // 根据选择不同那个的产品生成不同的报表
    function changeProduct() {
        var partid = $("#appointCount").val()
        var proname = $("#appointCount [id=" + partid + "]").text();
        $.ajax({
            type: "POST",
            dataType: "text",
            async: false,
            url: "find/changeproduct.do",
            data: {
                partid: partid,
                proname: proname
            },// 你的formid
            success: function (data) {
                var map = eval("(" + data + ")");
                var timelist = map.timelist;
                var appointData = map.appointData;
                var markData = map.markData;
                var appointList = map.appointList;
                createAppointView(timelist, appointData, markData, map.proName)
            }
        })
    }
    
    function importExcel() {
        var proid=$("#appointCount").val()
       var flag= confirm("确认导出该产品的预约单详情？")
        if(flag){
            window.location="find/importData.do?proid="+proid;
        }
    }
</script>

<body>
<div id="appointDiv">
    查看<select id="appointCount" onchange="changeProduct()">
    <c:forEach var="stem" items="${onSaleProduct}">
        <option value="${stem.PRODUCT_ID}" id="${stem.PRODUCT_ID}">${stem.PRODUCT_NAME}</option>
    </c:forEach>
</select>预约单详情
    <button onclick="importExcel()">导出该产品预约单详情</button>

    <div id="mainBar"
         style="height:380px;border:1px solid #ccc;padding:10px; width:1430px;margin: 10px auto;"></div>
</div>
</body>
</html>
