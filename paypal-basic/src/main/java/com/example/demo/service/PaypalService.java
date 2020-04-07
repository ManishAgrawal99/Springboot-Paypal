package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {

//	@Autowired
//	private APIContext apiContext;
	
	
	String clientId = "ATzX5CEdXUWo4sFBqATxQfJ56u3fBWufuA5WtpIxtT4hTIz3HMGVUNJSP1bDwt0KHY81S10T-ZkvUwsr";
	String clientSecret = "EAd5o_L6RyC8kBxfTmIKUk090_hosjzP-VVnfNZpl_gQ4zZAN32XY24ugMCh0ivWhGtDJ6eBV-7xXENT";
	

	public Payment createPayment(Double total, String currency, String method, String intent, String description,
			String cancelUrl, String successUrl) throws PayPalRESTException {
		
		Amount amount = new Amount();
	    amount.setCurrency("INR");
	    amount.setTotal(total.toString());
	    
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);


	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");
	    
	    

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);
	    
	    

	    RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);

		
		
		Payment createdPayment;
	    try {
	    	
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        createdPayment = payment.create(context);
	        return createdPayment;
	        
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    
		return null;
	}

	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		
		Payment payment = new Payment();
		payment.setId(paymentId);
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		
		try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            return createdPayment;
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
		
		
		return null;
	}

}
