package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        	//디버깅 
        	//공백일때 true 
        	
        	boolean bool = false;
        	String input = reader.readLine();
        	bool = input == null;
        	if (bool) return;
        	String[] str =input.split(" ");
        	String path = str[1];
        	log.debug("path: {} ",path);
        	log.debug("filepath: {} ",new File("./webapp" + path).toPath());
        	//while(!bool) {
        	//	String input = reader.readLine();
        	//	log.debug("	버퍼 문자열 : {}",input);
        	//	bool = "".equals(input) && input == null;
        	//}//end while
        	DataOutputStream dos = new DataOutputStream(out);
        	byte[] body = Files.readAllBytes(new File("./webapp" + path).toPath());
//            byte[] body = "Hello World".getBytes();
        	if(path.endsWith(".css")) {
        		
        		response200HeaderWithCss(dos, body.length);
        		responseBody(dos, body);
        	}
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    private void response200HeaderWithCss(DataOutputStream dos, int lengthOfBodyContent) {
    	try {
    		dos.writeBytes("HTTP/1.1 200 OK \r\n");
    		dos.writeBytes("Content-Type: text/css;charset=utf-8\r\n");
    		dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
    		dos.writeBytes("\r\n");
    	} catch (IOException e) {
    		log.error(e.getMessage());
    	}
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
