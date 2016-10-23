package vls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonClient {
	/* Cached for performance */
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public <T> T get(String url, final Class<T> classType) {
		String response = get(url);
		final JavaType type = objectMapper.getTypeFactory().constructType(classType);
		return convertResponseToObject(type, response);
	}

	public <T> List<T> getCollection(String url, final Class<T> classType) {
		final String response = get(url);
		return convertResponseToObjectList(response, classType);
	}

	private String get(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			InputStream response = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(response));
			final StringBuffer responseBuffer = new StringBuffer();
			String line = in.readLine();
			while (line != null) {
				responseBuffer.append(line);
				line = in.readLine();
			}
			return responseBuffer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> List<T> convertResponseToObjectList(final String response, final Class<T> type)
		{
		try {
		final JavaType destinationType = createCollectionType(List.class, type);
		return convertResponseToObject(destinationType, response);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static <T> T convertResponseToObject(JavaType typeReference, final String response) {
		try {
		return objectMapper.readValue(response, typeReference);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Warning suppression is due to how java deals with collections and Class
	 * objects. This is a carry over from existing ObjectMapper interface.
	 */
	@SuppressWarnings("rawtypes")
	private static <E> CollectionType createCollectionType(Class<? extends Collection> collection, Class<E> type) {
		return objectMapper.getTypeFactory().constructCollectionType(collection, type);
	}

}