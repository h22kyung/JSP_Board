<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<center>

<h2>�亯�� �Է��ϱ�</h2>
<%
//�Խñ� �б⿡�� �亯�۾��⸦ Ŭ���ϸ� �Ѱ��ִ� �����͵��� �޾���
int num = Integer.parseInt(request.getParameter("num"));
int ref = Integer.parseInt(request.getParameter("ref"));
int re_step = Integer.parseInt(request.getParameter("re_step"));
int re_level = Integer.parseInt(request.getParameter("re_level"));


//form����� ������ �Է¹޾Ƽ� dao ȣ���ؼ� ����!!

%>

<form action="BoardReWriteProc.jsp" method="post">
<table width="600" border="1" bordercolor="gray">
	<tr height="40">
		<td align="center" width="150"> �ۼ��� </td>
		<td width="450"><input type="text" name="writer" size="60"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150"> ���� </td>
		<td width="450"><input type="text" name="subject" value="[�亯]" size="60"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150"> �̸��� </td>
		<td width="450"><input type="email" name="email" size="60"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150"> ��й�ȣ </td>
		<td width="450"><input type="password" name="password" size="60"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150"> �۳��� </td>
		<td width="450"><textarea rows="10" cols="60" name="content" ></textarea></td>
	</tr>
		<tr height="40">
		<td align="center" width="150"> �ۼ��� </td>
		<td width="450"><input type="text" name="writer" size="60"></td>
	</tr>
<!-- form���� ����ڷκ��� �Է¹����ʰ� �����͸� �ѱ� -->
	<tr height="40">
		<td align="center" colspan="2">
		<input type="hidden" name="ref" value="<%=ref %>">
		<input type="hidden" name="re_step" value="<%=re_step %>">
		<input type="hidden" name="re_level" value="<%=re_level %>">
		
		
		
		<input type="submit" value="�亯�۾���Ϸ�"> &nbsp;&nbsp;
		<input type="reset" value="���"> &nbsp;&nbsp;
	
		<button onclick="location.href='BoardList.jsp'"> ��ü �Խñۺ���</button>
		
		</td>
	</tr>


</table></form>






</center>










</body>
</html>