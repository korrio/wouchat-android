package com.candychat.net.event;


import com.candychat.net.model.response.RegisterData;

/**
 * Created by Mac on 3/2/15.
 */
public class RegisterSuccessEvent {
    private RegisterData registerData;

    public RegisterSuccessEvent(RegisterData loginData) {
        this.registerData = registerData;
    }

    public RegisterData getRegisterData() {
        return registerData;
    }
}
