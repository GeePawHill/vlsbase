package vls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String object(Object o)
	{
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
