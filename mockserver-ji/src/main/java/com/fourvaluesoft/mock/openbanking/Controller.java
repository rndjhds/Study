package com.fourvaluesoft.mock.openbanking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
