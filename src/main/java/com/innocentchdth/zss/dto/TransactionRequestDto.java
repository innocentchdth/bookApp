package com.innocentchdth.zss.dto;

import java.time.ZonedDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class TransactionRequestDto {

		private String type;

		private String extendedType;

		private Double amount;
		
		private ZonedDateTime created;
		
		private String reference;
		private String narration;
		
		private Map<String, Object> additionalData;
		
		private CardDto card;
}
		
	
