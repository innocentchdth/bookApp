package com.innocentchdth.zss.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor	
public class CategoryDto {
	
	  @ApiModelProperty(value="Category Unique Id")
	    private Long id;

	   
	    @ApiModelProperty(value="Title of the category")
	    private String title;


}
