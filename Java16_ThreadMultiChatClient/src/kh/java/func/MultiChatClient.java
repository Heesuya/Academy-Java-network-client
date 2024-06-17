package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiChatClient extends Thread {	
	private DataInputStream dis = null;
	
	@Override
	public void run() {
		try {
			while(true) {
				String receiveMsg = dis.readUTF();
				System.out.println(receiveMsg);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void main() {
		Scanner sc = new Scanner(System.in);
		String serverIp = "192.168.10.96";
		int serverPort = 12345;
		
		Socket socket = null;
	
		DataOutputStream dos = null;
		try {
			socket = new Socket(serverIp, serverPort);
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			while(true) {				
				System.out.print("채팅에서 사용할 닉네임을 입력하세요 : ");
				String nickname = sc.next();
				dos.writeUTF(nickname);
				int result = dis.readInt();
				if(result == 1) {
					System.out.println("이미 사용중인 닉네임입니다.");
				}else {
					break;
				}
			}
			this.start();
			System.out.println("<<채팅 시작>>");
		
			while(true) {
				String sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if(sendMsg.equals("exit")) {
					break;
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
