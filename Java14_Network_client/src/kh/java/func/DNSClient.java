package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DNSClient {
	public void dnsClinet() {
		Scanner sc = new Scanner(System.in);
		
		String serverIp = "192.168.10.20";
		int serverport = 8888;
		
		Socket socket = null;
		
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		try {
			socket = new Socket(serverIp, serverport);
			
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			dos = new DataOutputStream(out);
			dis = new DataInputStream(in);
			
			String serverMsg = dis.readUTF();
			System.out.println(serverMsg);
			String sendMsg = sc.nextLine();
			dos.writeUTF(sendMsg);
			String serverMsg2 = dis.readUTF();
	
			System.out.println(serverMsg2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void dnsClinetOne() {
		Scanner sc = new Scanner(System.in);
		
		String serverIp = "192.168.10.20";
		int serverport = 8888;
		
		Socket socket = null;
		
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		try {
			socket = new Socket(serverIp, serverport);
			
			OutputStream out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			InputStream in = socket.getInputStream();
			dis = new DataInputStream(in);
			
			System.out.println("알고싶은 도메인 주소를 입력하세요 : ");
			String domain = sc.nextLine();
			dos.writeUTF(domain);
			String ipAddress = dis.readUTF();
				System.out.println(ipAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
