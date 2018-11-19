/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kryptag.getcreditscore;

import com.kryptag.rabbitmqconnector.RMQConnection;
import com.kryptag.rabbitmqconnector.RMQProducer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author florenthaxha
 */
public class Producer extends RMQProducer{
    
    public Producer(ConcurrentLinkedQueue queue, RMQConnection rmq) {
        super(queue, rmq);
    }

    @Override
    public void run() {
        super.run(); 
    }
}
