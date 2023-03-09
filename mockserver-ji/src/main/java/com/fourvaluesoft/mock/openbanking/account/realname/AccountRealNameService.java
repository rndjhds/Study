package com.fourvaluesoft.mock.openbanking.account.realname;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountRealNameService {

    private String dataDir;

    public AccountRealNameService(String dataDir) {
        this.dataDir = dataDir;
    }

    public AccountRealName getRealName(String accountNum) {
        try {
            InputStream inputStream = createDataFileInputStream(accountNum);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            AccountRealName accountRealName = gson.fromJson(bufferedReader, AccountRealName.class);
            bufferedReader.close();

            return accountRealName;
        } catch (FileNotFoundException ex) {
            return createErrorAccountRealName("00001", "파일 요청거부-파일 미존재 오류([991)]");
        } catch (JsonIOException ex) {
            return createErrorAccountRealName("00002", "Json IO 거부-Json 입출력 오류[(993)]");
        } catch (JsonSyntaxException ex) {
            return createErrorAccountRealName("00003", "Json 데이터거부-Json 데이터 오류[(994)]");
        } catch (IOException ex) {
            return createErrorAccountRealName("00004", "IO 거부-입출력 오류[(995)]");
        }
    }

    private FileInputStream createDataFileInputStream(String accountNum) throws FileNotFoundException {
        String fileName = dataDir + "/real_name/" + accountNum + ".json";

        return new FileInputStream(fileName);
    }

    private AccountRealName createErrorAccountRealName(String rspCode, String rspMessage) {
        AccountRealName accountRealName = new AccountRealName();
        accountRealName.setRspCode(rspCode);
        accountRealName.setRspMessage(rspMessage);

        return accountRealName;
    }
}