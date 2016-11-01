package vls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

public class RealHttpClient implements HttpClient {

	private static final String POST = "POST";
	private static final String GET = "GET";
	private static final Logger log = Logger.getLogger(RealHttpClient.class.getSimpleName());

	@Override
	public String get(HttpRequest request) {
		return run(request, GET);
	}

	@Override
	public String post(HttpRequest request) {
		return run(request, POST);
	}

	private String run(HttpRequest request, String method) {
		try {
			String requestEndpoint = request.url + request.action;
			log.info("Request Endpoint: " + requestEndpoint);
			if(request.message!=null) log.info("Request Message: " + request.message);
			URLConnection connection = getConnection(requestEndpoint, method);
			addHeadersToConnection(request, connection);
			
			connection.setDoOutput(true);
			if (method.equals(POST))
				writeRequest(request.message, connection.getOutputStream());
			String response = readResponseString(getInputStream(connection));
			log.info("Response: " + response);
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void addHeadersToConnection(HttpRequest request, URLConnection connection) {
		for (Entry<String, String> entry : request.headers.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
	}

	private InputStream getInputStream(URLConnection connection) throws IOException {
		if (connection instanceof HttpURLConnection) {
			HttpURLConnection castConnection = (HttpURLConnection) connection;
			if (castConnection.getResponseCode() < 400)
				return castConnection.getInputStream();
			else
				return castConnection.getErrorStream();
		} else if (connection instanceof HttpsURLConnection) {
			HttpsURLConnection castConnection = (HttpsURLConnection) connection;
			if (castConnection.getResponseCode() < 400)
				return castConnection.getInputStream();
			else
				return castConnection.getErrorStream();
		}
		return null;
		
	}


	private String readResponseString(final InputStream is) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(is));) {
			final StringBuffer responseBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				responseBuffer.append(line);
			}
			return responseBuffer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void writeRequest(String message, final OutputStream os) {
		try (OutputStreamWriter writer = new OutputStreamWriter(os);) {
			writer.write(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public URLConnection getConnection(String urlString, String method) throws Exception {
		URL url = new URL(urlString);
		if (url.getProtocol().toLowerCase().equals("https")) {
			HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();
			httpsConnection.setRequestMethod(method);
			return httpsConnection;
		} else {
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod(method);
			return httpConnection;
		}
	}

}
