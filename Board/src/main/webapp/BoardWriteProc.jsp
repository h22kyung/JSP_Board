<%@page import="model.BoardDAO"%>
<%@page import="model.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("UTF-8");//한글처리
%>

<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>
	</jsp:useBean>
	
<%
	BoardDAO bdao = new BoardDAO();

	bdao.insertBoard(boardbean);
	
	response.sendRedirect("BoardList.jsp");

%>




</body>
</html>