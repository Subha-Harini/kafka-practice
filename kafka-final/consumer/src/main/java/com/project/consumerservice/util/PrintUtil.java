package com.project.consumerservice.util;

import com.project.consumerservice.broker.message.UserMessage;

public class PrintUtil {

    public static UserMessage printMessage(UserMessage us){
        System.out.println(us.getMessage());
        return us;
    }


}
