<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="com.google.gson.FieldNamingPolicy"%>
<%@ page import="com.fourvaluesoft.mock.openbanking.account.transaction.AccountTransactionController"%>
<%@ page contentType="application/json" language="java" pageEncoding="UTF-8"%>
<%
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    String accountTransaction = gson.toJson(request.getAttribute(AccountTransactionController.ACCOUNT_TRANSACTION));
%>
<%=accountTransaction%>
