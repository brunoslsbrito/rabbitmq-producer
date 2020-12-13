package com.britosw.producerapi.message;

public class Messages {
	//validation errors
	public static final String REQUEST_METHOD_NOT_SUPPORTED = "197#Request method '%s' not supported or path parameter not found.";
	public static final String TYPE_MISMATCH_REQUEST = "198#Invalid %s: Type mismatch (%s).";
	public static final String MALFORMED_JSON_REQUEST = "199#Malformed JSON request.";

	// application errors
	public static final String DEFAULT_ERROR = "201#Internal error. If problem persists, contact support.";
	public static final String ENTITY_ALREADY_EXISTS = "202#Operation failed. %s already exists.";
	public static final String ENTITY_NOT_FOUND = "203#Operation failed. %s not found.";
	public static final String ENTITY_RELATION_EXISTS = "204#Operation failed. %s is associated with a %s instance.";
	public static final String ENTITY_PROPERTY_ALREADY_EXISTS = "205#Operation failed. Another %s already exists with same %s.";
	public static final String FETCH_CLIENT_DATA_ERROR = "206#Internal error while retrieving client information. If problem persists, contact support.";
	public static final String FETCH_MERCHANT_DATA_ERROR = "207#Internal error while retrieving mechant information. If problem persists, contact support.";

	public static Integer getCode(String message) {
		return Integer.valueOf(message.substring(0, 3));
	}
}
