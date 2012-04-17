package org.bluemagic.config.service.utils;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RestClientUtil {

	private RestClientUtil() {
		throw new AssertionError();
	}
	
	public static String get(URI uri) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			
			System.out.println(response.getStatusLine().getStatusCode() + " (" + response.getStatusLine().getReasonPhrase() + ")");
			
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		return null;
	}
	
	public static int post(URI uri, String json) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(uri);
		
		try {
			StringEntity entity = new StringEntity(json);
			entity.setContentType("application/json");
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode() + " (" + response.getStatusLine().getReasonPhrase() + ")");
			return response.getStatusLine().getStatusCode();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
	
	public static int delete(URI uri) {
		HttpClient client = new DefaultHttpClient();
		HttpDelete delete = new HttpDelete(uri);
		
		try {
			HttpResponse response = client.execute(delete);
			System.out.println(response.getStatusLine().getStatusCode() + " (" + response.getStatusLine().getReasonPhrase() + ")");
			return response.getStatusLine().getStatusCode();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
	
	public static int put(URI uri, String json) {
		HttpClient client = new DefaultHttpClient();
		HttpPut put = new HttpPut(uri);
		
		try {
			StringEntity entity = new StringEntity(json);
			entity.setContentType("application/json");
			put.setEntity(entity);
			
			HttpResponse response = client.execute(put);
			System.out.println(response.getStatusLine().getStatusCode() + " (" + response.getStatusLine().getReasonPhrase() + ")");
			return response.getStatusLine().getStatusCode();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
}
