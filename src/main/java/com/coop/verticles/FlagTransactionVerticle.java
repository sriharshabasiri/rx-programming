package com.coop.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class FlagTransactionVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
      vertx.setTimer(2000, handler->{
          System.out.println("waiting for incoming message");

          System.out.println("getting the message");
          vertx.eventBus().consumer("flagtransaction", fdMessage -> {
              System.out.println("msg recieved"+fdMessage.body());
              JsonObject reply = new JsonObject();
              reply.put("message","recieved");
              fdMessage.reply(reply);
          });
      })  ;
    }
}
