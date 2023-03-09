package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountBalanceController implements Controller {

    public static final String ACCOUNT_BALANCE = "ACCOUNT_BALANCE";

    private AccountBalanceService balanceService;

    public AccountBalanceController(AccountBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            String tranDtime = request.getParameter("tranDtime");
            AccountBalance accountBalance = balanceService.getBalance(tranDtime);

            request.setAttribute(ACCOUNT_BALANCE, accountBalance);
        } else {
            AccountBalance accountBalance = createErrorAccountBalance("O0010", "허용되지 않는 메소드");

            request.setAttribute(ACCOUNT_BALANCE, accountBalance);
        }

        return createViewPath();
    }

    private AccountBalance createErrorAccountBalance(String rspCode, String rspMessage) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setRspCode(rspCode);
        accountBalance.setRspMessage(rspMessage);

        return accountBalance;
    }

    private String createViewPath() {
        return "/WEB-INF/pages/account/showAccountBalance.jsp";
    }
}
