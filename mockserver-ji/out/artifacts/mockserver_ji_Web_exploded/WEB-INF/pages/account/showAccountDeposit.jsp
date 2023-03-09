<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="com.google.gson.FieldNamingPolicy"%>
<%@ page import="com.fourvaluesoft.mock.openbanking.account.deposit.AccountDepositController"%>
<%@ page contentType="application/json" language="java" pageEncoding="UTF-8" %>
<%
  Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
  String accountDeposit = gson.toJson(request.getAttribute(AccountDepositController.ACCOUNT_DEPOSIT));
%>
<%=accountDeposit%>
