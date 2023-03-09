package com.fourvaluesoft.mock.openbanking;

import com.fourvaluesoft.mock.openbanking.account.balance.AccountBalanceController;
import com.fourvaluesoft.mock.openbanking.account.balance.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.deposit.AccountDepositController;
import com.fourvaluesoft.mock.openbanking.account.deposit.AccountDepositService;
import com.fourvaluesoft.mock.openbanking.account.realname.AccountRealNameController;
import com.fourvaluesoft.mock.openbanking.account.realname.AccountRealNameService;
import com.fourvaluesoft.mock.openbanking.account.transaction.AccountTransactionController;
import com.fourvaluesoft.mock.openbanking.account.transaction.AccountTransactionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private Map<String, Controller> controllerMap;

    @Override
    public void init() throws ServletException {
        controllerMap = new HashMap<>();

        controllerMap.put("/account/balance", createAccountBalanceController(createDataDir("WEB-INF/data/account")));
        controllerMap.put("/inquiry/real_name", createAccountRealNameController(createDataDir("WEB-INF/data/inquiry")));
        controllerMap.put("/account/transaction_list", createAccountTransactionController(createDataDir("WEB-INF/data/account")));
        controllerMap.put("/transfer/deposit", createAccountDepositController(createDataDir("WEB-INF/data/transfer")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = controllerMap.get(request.getServletPath()).processRequest(request, response);
        forwardToView(request, response, viewPath);
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response, String viewPath) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private AccountRealNameController createAccountRealNameController(String dataDir) {
        return new AccountRealNameController(new AccountRealNameService(dataDir));
    }

    private AccountBalanceController createAccountBalanceController(String dataDir) {
        return new AccountBalanceController(new AccountBalanceService(dataDir));
    }

    private AccountTransactionController createAccountTransactionController(String dataDir) {
        return new AccountTransactionController(new AccountTransactionService(dataDir));
    }

    private AccountDepositController createAccountDepositController(String dataDir) {
        return new AccountDepositController(new AccountDepositService(dataDir));
    }

    private String createDataDir(String dataDir) {
        return getServletContext().getRealPath(dataDir);
    }
}
