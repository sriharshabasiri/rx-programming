package com.coop;

import com.coop.verticles.FlagTransactionVerticle;
import com.coop.verticles.FraudDetectionVerticle;
import com.coop.verticles.ReportVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.config.ConfigStoreOptions;

public class TransactionMonitoring extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setConfig(new JsonObject().put("path", "vertx-config.json"));

        ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("sys");

        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore).addStore(sysPropsStore);

        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

        retriever.getConfig().onComplete(ar -> {
            if (ar.failed()) {
                // Failed to retrieve the configuration
            } else {
                JsonObject config = ar.result();
                int instances= config.getInteger("no-of-instances");

                DeploymentOptions deploymentOptions = new DeploymentOptions();
                deploymentOptions.setConfig(config);
                deploymentOptions.setInstances(instances);

                vertx.deployVerticle(FraudDetectionVerticle.class.getName(),deploymentOptions);
                vertx.deployVerticle(FlagTransactionVerticle.class.getName(),deploymentOptions);
               vertx.deployVerticle(ReportVerticle.class.getName(),deploymentOptions);

            }
        });
   }

}
