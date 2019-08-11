<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.staticfile.org/foundation/5.5.3/css/foundation.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/foundation/5.5.3/js/foundation.min.js"></script>
<script src="https://cdn.staticfile.org/foundation/5.5.3/js/vendor/modernizr.js"></script>
<title>Welcome</title>
</head>
<body>
<div>
	<form action="/Retrieval_system/FIle" style="margin-left:30%;margin-top:240px;" name="form" method="post" enctype="multipart/form-data">
 		<div class="input-group mt-3 mb-3" >
 
			<div clas="input-group-prepend" style="background:none;border:none">
				<button  type="button" style="width:80px;height:40px" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown">
				Type</button>
				<div class="dropdown-menu" style="background:none;border:none">
					<a id="tapian" class="dropdown-item" style="font-family:'宋体'; color:blue;world-spacing:3px;">拓片</a>
					<a id="yinshuati" class="dropdown-item" style="font-family:'宋体'; color:blue;world-spacing:3px;">印刷体</a>
				</div>
			</div>
		<input type="file"  name="file" title="选择文件" style="width:32%;height:40px;border:2px solid #000" value=""/>
		<input type="submit" class="btn btn-warning" style="width:10%;height:40px" name="submit"  value="检索" />
		</div>
		<div>	
		</div>
	</form>
</div>
</form>
</body>
</html>