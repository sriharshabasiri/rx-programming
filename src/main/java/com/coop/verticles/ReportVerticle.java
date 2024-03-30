package com.coop.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ReportVerticle extends AbstractVerticle {

    @Override
    public void start(){

        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.get("/getreport").handler(context->{
            JsonObject response = new JsonObject();
            response.put("txnid","suspicious");
            context.response().end(response.encodePrettily());
        });

        router.post("/addreport").handler(BodyHandler.create()).handler(context->{
            String report = context.body().asJsonObject().toString();
            System.out.println("report to add "+report);
            JsonObject response = new JsonObject();
            response.put("message","report added");
            context.response().end(response.encodePrettily());
        });

        router.get("/getreport/:id").handler(context->{
            String id = context.request().getParam("id");
            System.out.println("id "+id);
            JsonObject response = new JsonObject();
            response.put("message","report for id "+id);
            context.response().end(response.encodePrettily());
        });


        router.get("/fetchreport").handler(context->{
            String id = context.request().params().get("id");
            System.out.println("id "+id);
            JsonObject response = new JsonObject();
            response.put("message","report for id "+id);
            context.response().end(response.encodePrettily());
        });

        httpServer.requestHandler(router).listen(8089);
        System.out.println("Report Verticle");
    }
}
