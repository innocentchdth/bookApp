package com.innocentchdth.zss.dto;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class TransactionResponseDto {
	
	private ZonedDateTime updated;
	private String responseCode;
	private String responseDescription;
	private String reference;
	private String debitReference;
	
	

}
