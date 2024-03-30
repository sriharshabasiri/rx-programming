package com.coop.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class FraudDetectionVerticle extends AbstractVerticle {

    @Override
    public void start(){
        JsonObject fdMessage = new JsonObject();
        fdMessage.put("txnid","flag suspicious");

        //DeliveryOptions options = new DeliveryOptions().setSendTimeout(1000);

      EventBus flagtransaction = vertx.eventBus().send("flagtransaction", fdMessage);

        System.out.println("message sent");
    }
}
