package com.coop.verticles;

import io.vertx.core.AbstractVerticle;

public class ReportVerticle extends AbstractVerticle {

    @Override
    public void start(){
        System.out.println("Report Verticle");
    }
}
