package com.fourvaluesoft.mock.openbanking.account.deposit;

import com.fourvaluesoft.mock.openbanking.Controller;
import com.google.gson.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountDepositController implements Controller {

    public static final String ACCOUNT_DEPOSIT = "ACCOUNT_DEPOSIT";

    private AccountDepositService accountDepositService;

    public AccountDepositController(AccountDepositService accountDepositService) {
        this.accountDepositService = accountDepositService;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("POST".equals(request.getMethod())) {
            InputStream inputStream = request.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);
            JsonArray jsonArray = jsonObject.get("req_list").getAsJsonArray();

            if (existJsonElement(jsonArray, "fintech_use_num")) {
                String fintechUseNum = createMember(jsonArray, "fintech_use_num");

                AccountDeposit accountDeposit = accountDepositService.getDeposit(fintechUseNum);
                request.setAttribute(ACCOUNT_DEPOSIT, accountDeposit);
            } else if (existJsonElement(jsonArray, "account_num") && existJsonElement(jsonArray, "bank_code_std")) {
                String accountNum = createMember(jsonArray, "account_num");
                String accountHolderName = createMember(jsonArray, "bank_code_std");

                AccountDeposit accountDeposit = accountDepositService.getDeposit(accountNum, accountHolderName);
                request.setAttribute(ACCOUNT_DEPOSIT, accountDeposit);
            }
        } else {
            AccountDeposit accountDeposit = createErrorAccountDeposit("O0010", "허용되지 않는 메소드");

            request.setAttribute(ACCOUNT_DEPOSIT, accountDeposit);
        }

        return createViewPath();
    }

    private String createMember(JsonArray jsonArray, String jsonElement) {
        return jsonArray.get(0).getAsJsonObject().get(jsonElement).getAsString();
    }

    private boolean existJsonElement(JsonArray jsonArray, String jsonElement) {
        return jsonArray.get(0).getAsJsonObject().has(jsonElement);
    }

    private AccountDeposit createErrorAccountDeposit(String rspCode, String rspMessage) {
        AccountDeposit accountDeposit = new AccountDeposit();
        accountDeposit.setRspCode(rspCode);
        accountDeposit.setRspMessage(rspMessage);

        return accountDeposit;
    }

    private String createViewPath() {
        return "/WEB-INF/pages/account/showAccountDeposit.jsp";
    }
}
