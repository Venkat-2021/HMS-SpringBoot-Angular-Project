package com.hms.bookingservice.config;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


@Configuration
public class SMSConfig {

	 public static final String ACCOUNT_SID = ""; 
	    public static final String AUTH_TOKEN = ""; 

	public void sendSMS(long number, String message)  throws Exception {
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message messages = Message.creator(new com.twilio.type.PhoneNumber("+91"+number),  
	                "", 
			        message).create();
		 System.out.println(messages.getSid());
	}
}
