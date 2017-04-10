package cn.featherfly.common.location.impl;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.location.UnicodeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractLocationAddressHttpApi.
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractLocationAddressHttpApi {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected String request(String url) throws Exception {
        return request(url, null);
    }

    protected String request(String url, Map<String, String> params) throws Exception {
        return request(url, params, null);
    }

    protected String request(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        if (params == null) {
            params = new HashMap<>();
        }
        if (headers == null) {
            headers = new HashMap<>();
        }

        StringBuilder queryStr = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (LangUtils.isNotEmpty(param.getKey())) {
                if (queryStr.length() > 0) {
                    queryStr.append("&");
                } else {
                    queryStr.append("?");
                }
                queryStr.append(param.getKey()).append("=").append(param.getValue());
            }
        }
        if (queryStr.length() > 0) {
            url += queryStr.toString();
        }
        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();
        String result = null;
        URL finalUrl = new URL(url);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) finalUrl
                .openConnection();

        connection.setRequestMethod("GET");

        for (Map.Entry<String, String> header : headers.entrySet()) {
            if (LangUtils.isNotEmpty(header.getKey())) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        connection.connect();
        InputStream is = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        result = sbf.toString();
        return UnicodeUtils.unicodeToString(result);
    }
}
