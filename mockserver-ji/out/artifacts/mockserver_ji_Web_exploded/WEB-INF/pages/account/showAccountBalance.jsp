<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="com.google.gson.FieldNamingPolicy"%>
<%@ page import="com.fourvaluesoft.mock.openbanking.account.balance.AccountBalanceController"%>
<%@ page contentType="application/json" language="java" pageEncoding="UTF-8" %>
<%
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    String accountBalanceJson = gson.toJson(request.getAttribute(AccountBalanceController.ACCOUNT_BALANCE));
%>
<%=accountBalanceJson%>