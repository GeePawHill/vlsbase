package vls;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import dataset.Dataset;
import javafx.application.Platform;

@RestController
@EnableAutoConfiguration
public class VlsController {

	static Logger logger = LoggerFactory.getLogger("Controller");

	public VlsController() {
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/control/vls")
	public String vls() {
		Platform.runLater(() -> Vls.simulationOn());
		return "Ok.";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{server}/**", method = { RequestMethod.GET, RequestMethod.POST })
	public String dealers2(@PathVariable("server") String server, final HttpServletRequest request,
			@RequestParam Map<String, String> params) {

		String finalPath = parseFinalPath(request);
		System.out.println(finalPath);
		HashMap<String, String> headers = parseHeaders(request);
		String contentType = request.getContentType();
		
		final String realServer = "http://localhost:9001/";
		if (request.getMethod().equals("GET")) {
			return handleGet(finalPath, headers, contentType, realServer);
		}
		if (request.getMethod().equals("POST")) {
			return handlePost(finalPath, headers, contentType, realServer, parseBody(request));
		}

		return "Something went wrong";
	}

	private String handlePost(String finalPath, HashMap<String, String> headers, String contentType,
			final String realServer, String body) {
		HttpRequest newRequest = new HttpRequest(realServer, finalPath, contentType,
				headers, body);
		String result = new RealHttpClient().post(newRequest);
		return result;
	}

	private String parseBody(final HttpServletRequest request) {
		String body = "";
		BufferedReader reader;
		try {
			reader = request.getReader();
			String line = reader.readLine();
			while (line != null) {
				body += line + "\n";
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body;
	}

	private String handleGet(String finalPath, HashMap<String, String> headers, String contentType,
			final String realServer) {
		HttpRequest newRequest = new HttpRequest(realServer, finalPath, contentType,
				headers);
		String result = new RealHttpClient().get(newRequest);
		return result;
	}

	private HashMap<String, String> parseHeaders(final HttpServletRequest request) {
		HashMap<String, String> headers = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
				
		while(headerNames.hasMoreElements()){
			String nextElement = headerNames.nextElement();
			headers.put(nextElement, request.getHeader(nextElement));
		}
		return headers;
	}

	private String parseFinalPath(final HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

		AntPathMatcher apm = new AntPathMatcher();
		String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		return finalPath+request.getQueryString();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/control/reset")
	public String reset() {
		Dataset.data.reset();
		return "Ok.";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/dealers")
	public String dealers() {
		logger.warn("REQUEST /dealers");
		String response = Vls.handler().dealers();
		logger.warn("RESPONSE " + response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/orders")
	public String orders(@RequestParam("id") String id) {
		logger.warn("REQUEST /orders");
		String response = Vls.handler().orders(id);
		logger.warn("RESPONSE " + response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/transfer")
	public String transfer(@RequestParam(value = "fromId") String fromId, @RequestParam(value = "toId") String toId,
			@RequestParam(value = "order") String order) {
		logger.warn("REQUEST /transfer Order: " + order + " From " + fromId + " To " + toId);
		String response = Vls.handler().transfer(fromId, toId, order);
		logger.warn("RESPONSE " + response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/batches")
	public String batches() {
		logger.warn("REQUEST /batches");
		String response = Vls.handler().batches();
		logger.warn("RESPONSE " + response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/batch")
	public String batch(@RequestParam(value = "id") String id) {
		logger.warn("REQUEST /batch Id " + id);
		String response = Vls.handler().batch(id);
		logger.warn("RESPONSE " + response);
		return response;
	}

}