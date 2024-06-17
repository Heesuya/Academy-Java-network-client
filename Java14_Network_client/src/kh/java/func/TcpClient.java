package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
	public void tcpClient() {
		Scanner sc = new Scanner(System.in);
		//1. 서버의 아이피주소, 포트번호 지정
		String serverIp = "192.168.10.20";//서버아이피주소
		int serverPort = 7777;			  //서버에서 사용할 프토번호
		//자원반환에 필요한 객체 미리 선언 
		Socket socket = null;//서버랑 데이터를 주고받을 매개체
		//서버와 데이터를 주고받을 보조스트림
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		try {
			socket = new Socket(serverIp, serverPort);//나 지금부터 접속해도 되?
			System.out.println("서버 접속 완료");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			//4. 보조스트림을 통한 성능개선
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			//데이터 송/수신 준비 완료
			
			while(true) {
				String serverMsg = dis.readUTF();//서버로부터 메세지를 수신해서 문자열에 저장
				if(serverMsg.equals("exit")) {
					break;
				}
				System.out.println("서버 : "+serverMsg);
				
				System.out.print("서버에 보낼 메세지 입력 : ");
				String str = sc.nextLine();
				dos.writeUTF(str);//서버로 메세지 전송
				if(str.equals("exit")) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//자원반환
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
