package com.fontanly;





import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * ��ȡtoken��
 */
public class AuthService {

    /**
     * ��ȡȨ��token
     * @return ����ʾ����
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // ������ȡ�� API Key ����Ϊ��ע���
        String clientId = "4H3F0rGI8CGtIgUqbCGgllCO";
        // ������ȡ�� Secret Key ����Ϊ��ע���
        String clientSecret = "mG9FB0vZWtwyO31Ns9M0L4rXyEXAZdQ4";
        return getAuth(clientId, clientSecret);
    }

    /**
     * ��ȡAPI����token
     * ��token��һ������Ч�ڣ���Ҫ���й�����ʧЧʱ�����»�ȡ.
     * @param ak - �ٶ��ƹ�����ȡ�� API Key
     * @param sk - �ٶ��ƹ�����ȡ�� Securet Key
     * @return assess_token ʾ����
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // ��ȡtoken��ַ
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_typeΪ�̶�����
                + "grant_type=client_credentials"
                // 2. ������ȡ�� API Key
                + "&client_id=" + ak
                // 3. ������ȡ�� Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // �򿪺�URL֮�������
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * ���ؽ��ʾ��
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("��ȡtokenʧ�ܣ�");
            e.printStackTrace(System.err);
        }
        return null;
    }

}
