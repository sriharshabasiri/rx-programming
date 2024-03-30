package com.coop.verticles;

import io.vertx.core.AbstractVerticle;

public class FraudDetectionVerticle extends AbstractVerticle {

    @Override
    public void start(){
        System.out.println("FraudDetectionVerticle");
    }
}
