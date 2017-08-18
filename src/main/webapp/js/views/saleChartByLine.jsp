<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>Highcharts Example</title>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/style/jquery.js"></script>
    <script type="text/javascript" src="/style/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/style/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/style/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/style/highcharts.js"></script>

    <style type="text/css">
        ${demo.css}
    </style>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '销售报表',
                    x: -20 //center
                },
                xAxis: {

                    categories: ['2017-07-01', '2017-07-17']
                },
                yAxis: {
                    title: {
                        text: '销售金额(¥)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '销售金额',
                    data: [13588, 14990]
                }]
            });
        });
    </script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 380px; margin: 0 auto"></div>
</body>
</html>
