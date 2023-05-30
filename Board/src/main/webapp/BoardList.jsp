<%@page import="java.util.Vector"%>
<%@page import="model.BoardBean" %>
<%@page import="model.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

</head>
<body>

<%
	//게시글이 몇개인지 모르니까 백터로 ..
	BoardDAO bdao = new BoardDAO();

	Vector<BoardBean> vec = bdao.getAllBoard();

%>
<center>
<h2> 게시글 보기 </h2>
<table width="700" border="1" bgcolor="pink">
	<tr height="40">
		<td align="center" width="50"> 번호 </td>
		<td align="center" width="320"> 제목 </td>
		<td align="center" width="100"> 작성자 </td>
		<td align="center" width="150"> 작성일 </td>
		<td align="center" width="80"> 조회수 </td>
		
	</tr>
<%
	for(int i=0; i<vec.size();i++){
		BoardBean bean = vec.get(i);
		
	%>
		<tr height="40">
		<td align="center" width="50"> <%=i+1%> </td>
		<td align="left" width="320"> <a href="BoardInfo.jsp?num=<%=bean.getNum() %>">
		
		<%
		if(bean.getRe_step()>1){
			
			for(int j=0; j<(bean.getRe_step()-1)*5 ; j++){
				%> &nbsp;
				<%
			}
		}
		
		%>
		
		<%=bean.getSubject() %>
		
		</a> </td>
		<td align="center" width="100"> <%=bean.getWriter() %> </td>
		<td align="center" width="150"> <%=bean.getReg_date() %> </td>
		<td align="center" width="80"> <%=bean.getReadcount() %> </td>
		
	</tr>
<%
	}
%>
	<tr height="40">
	<td colspan="5" align="center">
	<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.jsp'">
	</td></tr></table></center>
		





</body>
</html>