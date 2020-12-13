package com.britosw.producerapi.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApplicationMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Code")
	private Integer code;

	@JsonProperty("Message")
	private String message;

	public static ApplicationMessage parse(String message) {
		String[] splitted = message.split("#");
		return new ApplicationMessage(Integer.valueOf(splitted[0]), splitted[1]);
	}
}
