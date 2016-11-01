package vls;

public interface HttpClient {

	String post(HttpRequest request);

	String get(HttpRequest request);
}