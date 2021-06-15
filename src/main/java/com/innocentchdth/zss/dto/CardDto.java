package com.innocentchdth.zss.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CardDto{
	
	private String id;
	private Date expiry;

}
