package com.innocentchdth.zss.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

		private String type;

		private String extendedType;

		private BigDecimal amount;
		
		private Date created;
		
		private Object card;
		
		private String reference;
		private String narration;
		
		private Map<String, Object> additionalData;
		
		
}
		
	
