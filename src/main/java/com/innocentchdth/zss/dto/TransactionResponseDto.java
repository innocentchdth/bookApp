package com.innocentchdth.zss.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
	
	private ZonedDateTime updated;
	private String responseCode;
	private String responseDescription;
	private String reference;
	private String debitReference;
	
	

}
