package com.asha.icgweb.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class IPTest {

	public static void main(String args[]) throws IOException {
	    
        InetAddress addr = InetAddress.getLocalHost();
      
        String ipAddress = addr.getHostAddress();
        
        String port= "8082";
        String url = ipAddress.concat(":").concat(port);
        System.out.println("url: " + url);
       
        Socket s = new Socket("https://www.google.com", 0);
        System.out.println("listening on port: " + s.getLocalPort());
        
        String hostname = addr.getHostName();
        System.out.println("Name of hostname : " + hostname);

	}

}
