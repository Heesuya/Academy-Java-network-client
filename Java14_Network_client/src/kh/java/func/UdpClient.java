package kh.java.func;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {
	public void udpClientTest() {
		//1. 서버에서 사용할 포트번호 지정
		int serverPort = 8888;
		//자원반환할 객체 미리 선언
		DatagramSocket socket = null;
		
		try {
			//DatagramScoket 객체 생성
			socket = new DatagramSocket();//서비스를 요청하는 쪽은 객체생성시 port번호를 넣지 않음
			String msg = "난 UDP 클라이언트다!";
			byte[] outMsg = msg.getBytes();
			//서버 아이피주소를 InetAddress형태로 만들어야 함
			InetAddress serverIp = InetAddress.getByName("192.168.10.20");
			//데이터를 보낼 패킷객체 생성
			DatagramPacket outPacket = new DatagramPacket(outMsg, outMsg.length, serverIp, serverPort);
			//서버로 데이터 전송
			socket.send(outPacket);
			System.out.println("데이터 전송 완료");
			
			byte[] inMsg = new byte[1024];
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);
			socket.receive(inPacket);
			String serverMsg = new String(inMsg).trim();
			System.out.println(serverMsg);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			socket.close();
		}
	}
}
