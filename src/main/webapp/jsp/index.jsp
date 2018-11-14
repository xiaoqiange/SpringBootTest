<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">
<link href="<%=basePath%>/css/bootstrap-theme.min.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
	<div>
		<form id="uploadForm" enctype="multipart/form-data">
			<div class="form-group">
				<label for="file">选择文件</label> <input type="file" name="file"
					id="file" multiple="multiple">
				<img alt="" id="image" src="<%=basePath %>user/downLoad" width="112px" height="112px">
			</div>
		</form>
		<a id="submit">上传</a>
	</div>
	<script type="text/javascript">
    $(function () {
        $("#submit").click(function(){
        	var formData=new FormData($("#uploadForm")[0]);
        	if(!$("#file").val()){
        		alert("请选择上传文件");
        		return ;
        	}
        	$.ajax({
        		url:"<%=basePath%>user/upload",
					type : 'POST',
					data : formData,
					processData : false,
					contentType : false,
					success : function(data) {
						if (data != 0) {
							/* $("#image").attr("src",data); */
						}
					}
				});
			});
		});
	</script>
</body>
</html>