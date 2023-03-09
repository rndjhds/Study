package com.fourvaluesoft.mock.openbanking.account.balance;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountBalanceService {

    private String dataDir;

    public AccountBalanceService(String dataDir) {
        this.dataDir = dataDir;
    }

    public AccountBalance getBalance(String tranDtime) {
        try {
            InputStream inputStream = createDataFileInputStream(tranDtime);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            AccountBalance accountBalance = gson.fromJson(bufferedReader, AccountBalance.class);
            bufferedReader.close();

            return accountBalance;
        } catch (FileNotFoundException ex) {
            return createErrorAccountBalance("00001", "파일 요청거부-파일 미존재 오류([992)]");
        } catch (JsonSyntaxException ex) {
            return createErrorAccountBalance("00003", "Json 데이터거부-Json 데이터 오류[(993)]");
        } catch (JsonIOException ex) {
            return createErrorAccountBalance("00004", "Json IO 거부-Json 입출력 오류[(994)]");
        } catch (IOException ex) {
            return createErrorAccountBalance("00005", "IO 거부-입출력 오류[(995)]");
        }
    }

    private FileInputStream createDataFileInputStream(String tranDtime) throws FileNotFoundException {
        String fileName = dataDir + "/balance/" + tranDtime + ".json";

        return new FileInputStream(fileName);
    }

    private AccountBalance createErrorAccountBalance(String rspCode, String rspMessage) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setRspCode(rspCode);
        accountBalance.setRspMessage(rspMessage);

        return accountBalance;
    }
}
