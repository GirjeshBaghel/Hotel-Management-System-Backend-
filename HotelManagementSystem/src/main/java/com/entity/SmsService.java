
package com.entity;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {
    private final String ACCOUNT_SID = "AC15aba062eccd7f603e42ceae3c89b816";
    private final String AUTH_TOKEN = "d6a0b40288b67d8886f58d533f1507a0";

    public void sendSms(String phoneNumber, String message) {
    	System.out.print("Sms Method is Running0");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.print("Sms Method is Running1");
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber("+918802612236"),
                message
        ).create();
        System.out.print("Sms Method is Running2");
    }
}
