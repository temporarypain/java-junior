package com.acme.edu.controller;

import com.acme.edu.message.Message;
import com.acme.edu.saver.SimpleSaver;

import static java.util.Objects.isNull;

/**
 * Created by Java_1 on 24.08.2018.
 */
public class Controller {

    private Message currentMessage;
    private SimpleSaver saver = new SimpleSaver();

    public void log(Message message){

        if (isNull(currentMessage)){
            currentMessage = message;
            return;
        }

        if (currentMessage.isSameTypeOf(message)){
            this.currentMessage = currentMessage.accumulate(message);
        }
        else{
            String decoratedMessage = currentMessage.getDecoratedMessage();
            saver.save(decoratedMessage);
            this.currentMessage = message;
        }
    }

    public void flush() {
        System.out.println(currentMessage.getDecoratedMessage());
        currentMessage = null;
    }

    public void setCurrentMessage(Message message) {
        this.currentMessage = message;
    }
}