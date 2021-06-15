package com.innocentchdth.zss.dto;

import javax.validation.constraints.Min;

import com.innocentchdth.zss.model.Category;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor	
public class BookDto {
	    
	    @ApiModelProperty(value="Book Unique Id")
	    private Long id;

	   
	    @ApiModelProperty(value="Title of the book")
	    private String title;

	  
	    @ApiModelProperty(value = "Price of the book")
	    @Min(value = 0, message = "Price should be positive value.")
	    private float price;
	   
	    @ApiModelProperty(value="Category of the book")
	    private Category category;

	

}
