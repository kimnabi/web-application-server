<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User" %>
 <%@ page import="db.DataBase" %>
<%
String userId = request.getParameter("userId");
String password = request.getParameter("password");
User user = DataBase.findUserById(userId);
if(user == null){
	//사용자가 데이타베이스에 존재하지않다 에러메세지전송 
}
if(password.equals(user.getPassword())){
	//로그인 처리  
}
%>