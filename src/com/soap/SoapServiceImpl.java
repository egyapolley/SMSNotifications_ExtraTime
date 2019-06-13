package com.soap;




import com.restclient.RestUtility;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.SoapInterface")
public class SoapServiceImpl implements SoapInterface {

    @Override
    public String sendMesssage(SoapRequest request) {
        if (!request.getCallingSubscriber().isEmpty() && request.getSmsId()!=0 && !request.getPhoneContact().isEmpty())
        {
            String details=(request.getDetails().equals("others")||request.getDetails().isEmpty())?"details":request.getDetails();
            String result = RestUtility.pushSMS(request.getCallingSubscriber(), request.getSmsId(), request.getPhoneContact(),details);
            if (result.equals("Success")) return "Success";
        }
        return "Error";


    }
}
