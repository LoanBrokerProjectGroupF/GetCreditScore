/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kryptag.getcreditscore;

import com.kryptag.rabbitmqconnector.MessageClasses.CreditMessage;
import com.google.gson.Gson;
import com.kryptag.rabbitmqconnector.MessageClasses.BasicMessage;
import com.kryptag.rabbitmqconnector.RMQConnection;
import com.kryptag.rabbitmqconnector.RMQConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author florenthaxha
 */
public class Consumer extends RMQConsumer {

    public Consumer(ConcurrentLinkedQueue q, RMQConnection rmq) {
        super(q, rmq);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            doWork();
        }
    }

    private void doWork() {
        Gson g = new Gson();
        this.getRmq().createConnection();
        while (Thread.currentThread().isAlive()) {
            if (!this.getQueue().isEmpty()) {
                // should be in try catch!
                // internal queue 
                BasicMessage bmsg = g.fromJson(this.getQueue().remove().toString(), BasicMessage.class);
                CreditMessage cmsg = createCreditMessage(bmsg);
                this.getRmq().sendMessage(g.toJson(cmsg));
                System.out.println(cmsg.toString());
            }
        }
    }

    private int getCreditScore(String ssn) {
        int creditscore = 0;
        CreditScoreService_Service csss = new CreditScoreService_Service();
        try {
            creditscore = csss.getCreditScoreServicePort().creditScore(ssn);
        } catch (UnsupportedOperationException e) {
        }
        return creditscore;
    }

    private CreditMessage createCreditMessage(BasicMessage bmsg) {
        CreditMessage cmsg = new CreditMessage(getCreditScore(bmsg.getSsn()), bmsg.getSsn(), bmsg.getLoanAmount(), bmsg.getLoanDuration());
        return cmsg;
    }

}
