package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ThreadChatClient extends Thread{
	//서버가 보낸 데이터를 화면에 출력
	private DataInputStream dis = null;

	@Override
	public void run() {
		try {
			while(true) {
				String receiveMsg = dis.readUTF();
				System.out.println("[sever] : "+receiveMsg);
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void main() {
		Scanner sc = new Scanner(System.in);
		String serverIp = "127.0.0.1";
		int serverPort = 4567;
		
		Socket socket = null;
		DataOutputStream dos = null;
		
		try {
			socket = new Socket(serverIp, serverPort);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			System.out.println("채팅시작");
			this.start();
			//클라이언트가 키보드로 입력한 값을 서버로 전송
			while(true) {
				String sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
