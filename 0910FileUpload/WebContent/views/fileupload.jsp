<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload.file" method="post" 
	enctype="multipart/form-data">
		<label for="name">이름</label>
		<input type="text" name="name" /><br />
		<label for="file">첨부파일</label>
		<input type="file" name="file" /><br />
		<input type="submit" value="파일 전송" />
	
	</form>
</body>
</html>