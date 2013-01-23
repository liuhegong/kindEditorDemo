<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#assign path="${request.getContextPath()}">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />  
    <title>KindEditor-含上传插件的示例</title>
    <style>
			form {
				margin: 0;
			}
			textarea {
				display: block;
			}
		</style>
		<script charset="utf-8" src="${path}/js/jquery-1.7.2.min.js"></script>
		<script charset="utf-8" src="${path}/js/kindeditor-min.js"></script>
		<script charset="utf-8" src="${path}/js/lang/zh_CN.js"></script>
		<script charset="utf-8" src="${path}/js/plugins/emoticons/emoticons.js"></script>
		
		 <script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					uploadJson : '${path}/file_upload/doimgupload.do',//上传文件必须
					allowImageUpload : true,
					allowFileManager : false,
					afterUpload : function(url) {
                        alert(url);
                	},
                	
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
		
		function  submit_form()
		{
			$(document.body).append(editor.html()+"<br>----------------<br>");
		}
		
		</script>
  </head>
  <body>
		<h3>含有上传插件的示例</h3>
		<form>
			<textarea name="content" style="width:700px;height:200px;visibility:hidden;"></textarea>
			<br>
			<input type="button" value="提交" onclick="javascript:submit_form();" />
		</form>
		
  </body>
</html>
