package com.fourvaluesoft.mock.openbanking.account.transaction;

import com.fourvaluesoft.mock.openbanking.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountTransactionController implements Controller {

    public static final String ACCOUNT_TRANSACTION = "ACCOUNT_TRANSACTION";

    private AccountTransactionService accountTransactionService;

    public AccountTransactionController(AccountTransactionService accountTransactionService) {
        this.accountTransactionService = accountTransactionService;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("GET".equals(request.getMethod())) {
            String fintechUseNum = request.getParameter("fintechUseNum");
            AccountTransaction accountTransaction = accountTransactionService.getTransaction(fintechUseNum);

            request.setAttribute(ACCOUNT_TRANSACTION, accountTransaction);
        } else {
            AccountTransaction accountTransaction = createErrorAccountTransaction("O0010", "허용되지 않는 메소드");

            request.setAttribute(ACCOUNT_TRANSACTION, accountTransaction);
        }

        return createViewPath();
    }

    private AccountTransaction createErrorAccountTransaction(String rspCode, String rspMessage) {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setRspCode(rspCode);
        accountTransaction.setRspMessage(rspMessage);

        return accountTransaction;
    }

    private String createViewPath() {
        return "/WEB-INF/pages/account/showAccountTransaction.jsp";
    }
}
