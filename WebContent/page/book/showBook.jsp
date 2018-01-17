<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
		<style type="text/css">
			table{
				text-align: center;
			}
			table td{
				text-align: center;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#addBook").click(function(){
					var varAction="${ctx}/books/addBook.do";
					$.ajax({
						type:"get",
						url:varAction,
						data:{"param":"123"},
						dataType:"json",
						error:function(){
							alert("网络异常");
						},
						success:function(data){
							alert(data.param);
						}
					});
				});
			});
		</script>
	</head>
	<body style="text-align: center;">
		<div style="display: inline-block;">
			<table style="border: 1px solid black;">
				<tr>
					<td>书本编号</td>
					<td>书名编号</td>
					<td>书名</td>
				</tr>
				<tr>
					<td>${book["id"] }</td>
					<td>${book["bookId"] }</td>
					<td>${book["bookName"] }</td>
				</tr>
			</table>
		</div>
		<br>
		<div><input type="button" id="addBook" name="addBook" value="addBook"/></div>
	</body>
</html>