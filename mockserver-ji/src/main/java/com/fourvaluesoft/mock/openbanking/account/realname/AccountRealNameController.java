package com.fourvaluesoft.mock.openbanking.account.realname;

import com.fourvaluesoft.mock.openbanking.Controller;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountRealNameController implements Controller {

    public static final String ACCOUNT_REAL_NAME = "ACCOUNT_REAL_NAME";

    private AccountRealNameService realNameService;

    public AccountRealNameController(AccountRealNameService realNameService) {
        this.realNameService = realNameService;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("POST".equals(request.getMethod())) {
            InputStream inputStream = request.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);

            String accountNum = jsonObject.get("account_num").getAsString();
            AccountRealName accountRealName = realNameService.getRealName(accountNum);

            request.setAttribute(ACCOUNT_REAL_NAME, accountRealName);
        } else {
            AccountRealName accountRealName = createErrorAccountRealName("O0010", "허용되지 않는 메소드");

            request.setAttribute(ACCOUNT_REAL_NAME, accountRealName);
        }

        return createViewPath();
    }

    private AccountRealName createErrorAccountRealName(String rspCode, String rspMessage) {
        AccountRealName accountRealName = new AccountRealName();
        accountRealName.setRspCode(rspCode);
        accountRealName.setRspMessage(rspMessage);

        return accountRealName;
    }

    private String createViewPath() {
        return "/WEB-INF/pages/account/showAccountRealName.jsp";
    }
}
