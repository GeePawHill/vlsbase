package vls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringClient {
	public String get(String url) {
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
}