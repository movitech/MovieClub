<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>公共模型</title>
	<meta name="decorator" content="default"/>

    <script type="text/javascript">jwplayer.key="ABCDEFGHIJKLMOPQ";</script>
</head>
<body>
	请在左侧“栏目列表”中选择（非公共模型）栏目。
    <div id="myElement">Loading the player...</div>

    <script type="text/javascript">
        jwplayer("myElement").setup({
            file: "/uploads/myVideo.mp4",
            image: "/uploads/myPoster.jpg"
        });
    </script>
</body>
</html>