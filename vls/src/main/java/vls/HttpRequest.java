package vls;

import java.util.Map;

public class HttpRequest {
	public String action;
	public String contentType;
	public String message;
	public String url;
	public Map<String, String> headers;

	public HttpRequest(String url, String action, String contentType, Map<String, String> headers, String message) {
		this.action = action;
		this.contentType = contentType;
		this.message = message;
		this.url = url;
		this.headers = headers;
		headers.put("Content-Type", contentType);
	}
	
	public HttpRequest(String url, String action, String contentType, Map<String, String> headers) {
		this(url,action,contentType,headers,null);
	}

}