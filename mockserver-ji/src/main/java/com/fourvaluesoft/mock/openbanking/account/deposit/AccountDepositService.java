package com.fourvaluesoft.mock.openbanking.account.deposit;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountDepositService {

    private String dataDir;

    public AccountDepositService(String dataDir) {
        this.dataDir = dataDir;
    }

    public AccountDeposit getDeposit(String fintechUseNum) {
        try {
            InputStream inputStream = createDataFileInputStream(fintechUseNum);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            AccountDeposit accountDeposit = gson.fromJson(bufferedReader, AccountDeposit.class);
            bufferedReader.close();

            return accountDeposit;
        } catch (FileNotFoundException ex) {
            return createErrorAccountDeposit("00001", "파일 요청거부-파일 미존재 오류([991)]");
        } catch (JsonIOException ex) {
            return createErrorAccountDeposit("00002", "Json IO 거부-Json 입출력 오류[(993)]");
        } catch (JsonSyntaxException ex) {
            return createErrorAccountDeposit("00003", "Json 데이터거부-Json 데이터 오류[(994)]");
        } catch (IOException ex) {
            return createErrorAccountDeposit("00004", "IO 거부-입출력 오류[(995)]");
        }
    }

    public AccountDeposit getDeposit(String accountNum, String bankCodeStd) {
        try {
            InputStream inputStream = createDataFileInputStream(accountNum + "_" + bankCodeStd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            AccountDeposit accountDeposit = gson.fromJson(bufferedReader, AccountDeposit.class);
            bufferedReader.close();

            return accountDeposit;
        } catch (FileNotFoundException ex) {
            return createErrorAccountDeposit("00001", "파일 요청거부-파일 미존재 오류([991)]");
        } catch (JsonIOException ex) {
            return createErrorAccountDeposit("00002", "Json IO 거부-Json 입출력 오류[(993)]");
        } catch (JsonSyntaxException ex) {
            return createErrorAccountDeposit("00003", "Json 데이터거부-Json 데이터 오류[(994)]");
        } catch (IOException ex) {
            return createErrorAccountDeposit("00004", "IO 거부-입출력 오류[(995)]");
        }
    }

    private AccountDeposit createErrorAccountDeposit(String rspCode, String rspMessage) {
        AccountDeposit accountDeposit = new AccountDeposit();
        accountDeposit.setRspCode(rspCode);
        accountDeposit.setRspMessage(rspMessage);

        return accountDeposit;
    }

    private FileInputStream createDataFileInputStream(String dataKey) throws FileNotFoundException {
        String fileName = dataDir + "/deposit/" + dataKey + ".json";

        return new FileInputStream(fileName);
    }
}
