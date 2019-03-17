package com.github.nicolasperuch;

import akka.actor.ActorSystem;
import com.github.nicolasperuch.service.DeliverService;
import eventstore.Event;
import eventstore.SubscriptionObserver;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;

@Component
public class EntryPoint {

    @Autowired
    private DeliverService deliverService;

    final ActorSystem system = ActorSystem.create();
    final EsConnection connection = EsConnectionFactory.create(system);
    final Closeable closeable = connection.subscribeToStream("pizza", new SubscriptionObserver<Event>() {
        @Override
        public void onLiveProcessingStart(Closeable subscription) {
            system.log().info("Live processing started");
        }

        @Override
        public void onEvent(Event event, Closeable subscription) {
            system.log().info("Event received: \n {}", event.toString());
            try {
                deliverService.takeOrder(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            system.log().error(e.toString());
        }

        @Override
        public void onClose() {
            system.log().error("Subscription closed");
        }
    }, false, null);


}
