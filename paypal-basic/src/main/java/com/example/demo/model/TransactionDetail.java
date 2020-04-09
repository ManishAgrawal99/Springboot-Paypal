package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transaction")
public class TransactionDetail {
	
	@Id
	private String id;
	private Object details;
	
	public TransactionDetail() {
		super();
	}
	
	public TransactionDetail(String id, Object details) {
		super();
		this.id = id;
		this.details = details;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TransactionDetail)) {
			return false;
		}
		TransactionDetail n = (TransactionDetail)obj;
		
		if(n.id.equalsIgnoreCase(this.id)) {
			return true;
		}
		else {
			return false;
		}
	}
}
