<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#assign path="${request.getContextPath()}">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>logon</title>
		<link href="${path}/css/global.css" rel="stylesheet" type="text/css" />
	</head>
	<body>


		${path}
		<br>
			登录页面 <br />
			
			<div>
				<a href="${path}/kindctrl/show_simple.do">简单显示KindEditor</a>
			</div>
			
			<div>
				<a href="${path}/kindctrl/show_upload_file.do">KindEditor-含上传插件的示例 </a>
			</div>
			
			<div>
				<form action="${path}/l/testForm.do">
					
					ID:<input name="ID"  value="001"/>
					<br>
					age:<input name="age"  value="22"/>
					<br>
					user_name:<input name="user_name"  value="张小小"/>
					<br>
					<input type="submit"    value="提交"  />
					
				</form>
			</div>
			
			
	</body>
</html>
