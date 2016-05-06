<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Recommendation管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

		function popupSearchMovie(){
			if(${empty recommendation.id}) {
				top.$.jBox.open($("#searchMovie").html(), "Select Movie", 800, 420, {
					ajaxData: {
						selectIds: $("#companyId").val()
					},
					buttons: {
						"确定": "ok",
						"关闭": true
					},
					submit: function (v, h, f) {
						if (v == "ok") {
							$('#inputForm input[name="movie.id"]').val(h.find("#selectedMovieId").val());
							$('#inputForm input[name="movie.title"]').val(h.find("#selectedMovieName").val());
						}
					},
					loaded: function (h) {
						$(".jbox-content", top.document).css("overflow-y", "hidden");
					}
				});
			}
		}

		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

            $('#inputForm input[name="movie.title"]').click(popupSearchMovie);
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/recommendation/recommendation/">Recommendation List</a></li>
		<li class="active"><a href="${ctx}/recommendation/recommendation/form?id=${recommendation.id}"><shiro:hasPermission name="recommendation:recommendation:edit">${not empty recommendation.id?'Modify':'Add'}</shiro:hasPermission> Recommendation<shiro:lacksPermission name="recommendation:recommendation:edit">View</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recommendation" action="${ctx}/recommendation/recommendation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">Movie Name：</label>
			<div class="controls">
				<div class="input-append">
					<form:input type="hidden" id="movie" path="movie.id" />
					<form:input id="movie" path="movie.title" class="input-xlarge required" readonly="true" data-msg-required=""/>
                    <a id="companyButton" class="btn" onclick="popupSearchMovie();"><i class="icon-search"></i></a>
                </div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<!--<div class="control-group">
			<label class="control-label">Movie Genre：</label>
			<div class="controls">
				<div class="input-append">
					<%--<form:select id="movie" path="movie.genre" class="input-medium" >--%>
						<%--<form:option value="" label=""/>--%>
						<%--<form:options items="${fns:getMovieGenreList()}" itemLabel="genreName" itemValue="id" htmlEscape="false"/>--%>
					<%--</form:select>--%>
				</div>
			</div>
		</div>-->

		<div class="control-group">
			<label class="control-label">sort Order：</label>
			<div class="controls">
				<form:input path="sortOrder" htmlEscape="false" maxlength="6" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Remarks:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="recommendation:recommendation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>

	</form:form>

	<div id="searchMovie" style="display: none;">
		<div id="searchForm" class="breadcrumb form-search">
			<input id="selectedMovieId" type="hidden" value=""/>
			<input id="selectedMovieName" type="hidden" value=""/>
			<%--<input id="pageNo" name="pageNo" type="hidden" value="1"/>--%>
			<%--<input id="pageSize" name="pageSize" type="hidden" value="10"/>--%>
			<ul class="ul-form">
				<li><label>Name：</label>
					<input id="searchName" path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li hidden="hidden"><label style="width: 100px;">Movie Genre：</label>
					<select id="searchGenre">
						<option value=""></option>
					</select>
				</li>
				<li class="btns"><input id="searchSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
				<li class="clearfix"></li>
			</ul>
		</div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th>Select</th>
				<th>Name</th>
				<th>Movie Genre</th>
				<th>Director</th>
				<th>Update Date</th>
			</tr>
			</thead>
			<tbody id="movieChoiceContent"></tbody>
		</table>
		<div class="pagination">${page}</div>
		<script type="text/javascript">
			$(document).ready(function() {

			$.ajax({
				url: "${ctx}/genre/movieGenre/getMovieGenreList",
				type: "GET",
				success: function (movieGenreList) {
					$.each(movieGenreList, function (index, movieGenre) {
						var genreId = movieGenre.id;
						var genreName = movieGenre.genreName;
						$("#searchGenre").append('<option value="' + genreId + '">' + genreName + '</option>');
					});
				},
				complete: function () { },
				error: function () { }
			});

			$("#searchSubmit").click(function(){
				$("#movieChoiceContent").html('');
				var movieName = $.trim($("#searchName").val());
				var movieGenre = $("#searchGenre").val();
				movieName = movieName!=""?movieName:undefined;
				movieGenre = movieGenre!=""?movieGenre:undefined;
				$.ajax({
					url: "${ctx}/recommendation/recommendation/searchMovieList",
					type: "GET",
					data: {"movieName": movieName, "movieGenre": movieGenre},
					success: function (movieList) {
						$.each(movieList, function(index, movie) {
							var movieId = movie.id;
							var movieTitle = movie.title;
							var movieGenre = movie.genre;
							var movieDirector = movie.director;
							var movieUpdateDate = movie.updateDate;
							var movieHTML = "";
							movieHTML += '<tr>';
							movieHTML += '	<td>';
							movieHTML += '		<input type="radio" onclick="javascript:$(\'#selectedMovieId\').val(\'' + movieId + '\'); $(\'#selectedMovieName\').val(\'' + movieTitle + '\');" name="movieEnity" movieId="\'' + movieId + '\'">';
							movieHTML += '	</td>';
							movieHTML += '	<td>' + movieTitle + '</td>';
							movieHTML += '	<td>' + movieGenre + '</td>';
							movieHTML += '	<td>' + movieDirector + '</td>';
							movieHTML += '	<td>' + movieUpdateDate + '</td>';
							movieHTML += '</tr>';
							$("#movieChoiceContent").append(movieHTML)
						});
					},
					complete: function () { },
					error: function () { }
				});
			});

			});
		</script>
	</div>

</body>
</html>