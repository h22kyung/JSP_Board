<%@page import="model.BoardDAO"%>
<%@page import="model.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	
	request.setCharacterEncoding("euc-kr");	
%>


<!--  �����͸� �ѹ��� �޾ƿ��� ��Ŭ���� ��� -->

<jsp:useBean id="boardbean" class="model.BoardBean">
<jsp:setProperty name="boardbean" property="*"/>
</jsp:useBean>

<%
//�����ͺ��̽� ��ü ����
BoardDAO bdao = new BoardDAO();
bdao.reWriteBoard(boardbean);

response.sendRedirect("BoardList.jsp");



%>


</body>
</html>