package net.ilx.hessian.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.HttpClient;

import com.caucho.hessian.client.AbstractHessianConnectionFactory;
import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;

public class HttpClientHessianConnectionFactory extends AbstractHessianConnectionFactory implements HessianConnectionFactory {

	private HttpClient httpClient;

	public HttpClientHessianConnectionFactory(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public HessianConnection open(URL url) throws IOException {
		URI uri = null;
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			String msg = String.format("Unable to convert URL '%s' to URI.", url.toString());
			throw new IllegalStateException(msg, e);
		}
		return new HttpClientHessianConnection(httpClient, uri);
	}


}
