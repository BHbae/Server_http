package server_http_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer2 {
	
	public static void main(String[] args) {
		
		try {
			// 서버 생성
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 8);
			
			// 서버 프로토콜 정의(프로토콜, 핸들러)
			// 핸들러 처리를 내부 정적 클래스로 사용
			httpServer.createContext("/test", new MyTestHandler());
			httpServer.createContext("/GoodBy", new GoodByHandler());
			
			
			
			// 서버 시작
			httpServer.start();
			System.out.println(">>> gogo <<<");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// end of main
	
	static class MyTestHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println("method : " + method);
			if("GET".equalsIgnoreCase(method)) {
				// 겟
				handleGetRequest(exchange);
			} else if("POST".equalsIgnoreCase(method)){
				// 포스트
				handlePostRequest(exchange);
			} else {
				// 405
			}
			
		}
		
		private void handleGetRequest(HttpExchange exchange) throws IOException{
			String response = "깨꾸리 쩜프";
			exchange.sendResponseHeaders(200, response.length());
			
			PrintWriter writer = new PrintWriter(exchange.getResponseBody(), true);
			writer.println(response);
			writer.flush();
			writer.close();
			
			//OutputStream os = exchange.getResponseBody();
			//os.write(response.getBytes());
			//os.close();
			
		} // end of handleGetRequest
		
		private void handlePostRequest(HttpExchange exchange) throws IOException{
			
			String response = "Flog JUMP";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
		} //  end of handlePostRequest
		
	}
	
	static class GoodByHandler implements HttpHandler{

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			
		}
		
	}
	
}
