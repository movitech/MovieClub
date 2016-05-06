<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<section id="user_section">
	<header>
		<nav class="left">
			<a href="#" data-icon="previous" data-target="back">返回</a>
		</nav>
		<h1 class="title">视频测试</h1>
		<%--<script type="text/javascript" src="static/jwplayer/jwplayer.js"></script>--%>
		<%--<script type="text/javascript">jwplayer.key="zMAbk0zZSivlwa2lgB4PYbbycFL7ihnQVfBG0g==";</script>--%>
	</header>
	<article class="active">
		<div style="line-height:50px;padding:10px;">
			for Android:
			<div id="androidPlayer">Loading the player...</div>

			<script type="text/javascript">
				jwplayer("androidPlayer").setup({
					file: "rtsp://172.19.50.129:1935/vod/sample.mp4",
					image: "${ctxStatic}/jwplayer//player.jpg"
				});
			</script>
			<div id="iosPlayer">Loading the player...</div>
			<hr width="60%">
			for ios:
			<script type="text/javascript">
				jwplayer("iosPlayer").setup({
					file: "http://172.19.50.129:1935/vod/mp4:sample.mp4/playlist.m3u8",
					image: "${ctxStatic}/jwplayer//player.jpg"
				});
			</script>

			<div id='mediaspace'>EZ Stream TV FLV Player</div>
			<script type='text/javascript'>
				jwplayer('mediaspace').setup({
					'flashplayer': '"+ flvDirectory.getAbsolutePath() + "/player.swf',
					'file': '" + videofilename + "',
					'backcolor': 'FFFFFF',
					'frontcolor': '000000',
					'lightcolor': '000000',
					'screencolor': '000000',
					'volume': '100',
					'autostart': 'true',
					'mute': 'false',
					'quality': 'false',
					'controlbar': 'bottom',
					'width': '100%',
					'height': '100%',
					events: {
						onComplete: function () {
							alert('COMPLETED');
						}
					}
				});
			</script>
			<div>
				<p>这是一只傻兔子的故事,兔子急了也是会咬人的.....</p>

			</div>
		</div>


	</article>
	<script type="text/javascript">
		$('body').delegate('#user_section', 'pageinit', function () {
		});
		$('body').delegate('#user_section', 'pageshow', function () {
			var hash = J.Util.parseHash(location.hash);
			console.log(hash.param);
		});
	</script>
</section>