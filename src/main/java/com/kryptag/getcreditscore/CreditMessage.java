/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kryptag.getcreditscore;

import com.kryptag.rabbitmqconnector.BasicMessage;

/**
 *
 * @author florenthaxha
 */
public class CreditMessage extends BasicMessage {
    private final int creditScore;
    
    public CreditMessage(int creditScore, String ssn, double loanAmount, int loanDuration) {
        super(ssn, loanAmount, loanDuration);
        this.creditScore = creditScore;
    }

    public int getCreditScore() {
        return creditScore;
    }
    
   
    
}
