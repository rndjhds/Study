package com.fourvaluesoft.mock.openbanking.account.transaction;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountTransactionService {

    private String dataDir;

    public AccountTransactionService(String dataDir) {
        this.dataDir = dataDir;
    }

    public AccountTransaction getTransaction(String fintechUseNum) {
        try {
            InputStream inputStream = createDataFileInputStream(fintechUseNum);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            AccountTransaction accountTransaction = gson.fromJson(bufferedReader, AccountTransaction.class);
            bufferedReader.close();

            return accountTransaction;
        } catch (FileNotFoundException ex) {
            return createErrorAccountTransaction("00001", "파일 요청거부-파일 미존재 오류([991)]");
        } catch (JsonIOException ex) {
            return createErrorAccountTransaction("00002", "Json IO 거부-Json 입출력 오류[(993)]");
        } catch (JsonSyntaxException ex) {
            return createErrorAccountTransaction("00003", "Json 데이터거부-Json 데이터 오류[(994)]");
        } catch (IOException ex) {
            return createErrorAccountTransaction("00004", "IO 거부-입출력 오류[(995)]");
        }
    }

    private AccountTransaction createErrorAccountTransaction(String rspCode, String rspMessage) {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setRspCode(rspCode);
        accountTransaction.setRspMessage(rspMessage);

        return accountTransaction;
    }

    private FileInputStream createDataFileInputStream(String fintechUseNum) throws FileNotFoundException {
        String fileName = dataDir + "/transaction/" + fintechUseNum + ".json";

        return new FileInputStream(fileName);
    }
}
