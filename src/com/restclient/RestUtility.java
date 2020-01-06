package com.restclient;

import com.dbutil.DButility;
import com.hubtel.*;


public class RestUtility {
    private static final String SOURCE = "Surfline";
    private static final String USERNAME = "cpepfxgl";
    private static final String PASSWORD = "gtgoptsq";

    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    public static String pushSMS(String surflineNumber, int smsID, String phoneContact, String code) {

        String message = DButility.getMessage(smsID);
        if (message != null) {

            message = message.replace("SSSSSS", code);
            BasicAuth basicAuth = new BasicAuth(USERNAME, PASSWORD);
            ApiHost apiHost = new ApiHost(basicAuth);
            apiHost.setSecuredConnection(false);

            MessagingApi messagingApi = new MessagingApi(apiHost);
            Message message1 = new Message();
            message1.setContent(message.replace("XXXXXX", surflineNumber));
            message1.setFrom(SOURCE);
            message1.setTo(phoneContact);
            message1.setFlashMessage(false);
            try {
                MessageResponse messageResponse = messagingApi.sendMessage(message1);
                if (messageResponse.getStatus() == 0 && messageResponse.getMessageId() != null) {
                    if (DButility.insert(code, phoneContact, surflineNumber, message, "SEND SUCCESS " + messageResponse.getStatus() + " " + messageResponse.getDetail()))
                        return SUCCESS;
                } else {
                    System.out.println("DB insert failure for successful sent Message ");
                    return ERROR;
                }

            } catch (HttpRequestException e) {
                System.out.println("Server Status: " + e.getHttpResponse().getStatus());
                System.out.println("Response Body:" + e.getHttpResponse().getBodyAsString());
                if (DButility.insert(code, phoneContact, surflineNumber, message, "SEND ERROR " + e.getHttpResponse().getStatus() + " " + e.getHttpResponse().getBodyAsString())) {
                    System.out.println("DB insert failure for failed delivery message ");
                }
                return ERROR;
            }
        }


        return ERROR;
    }


}


