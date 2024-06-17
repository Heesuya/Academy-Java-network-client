package kh.java.start;

import kh.java.func.DNSClient;
import kh.java.func.FileClient;
import kh.java.func.QuizClient;
import kh.java.func.TcpClient;
import kh.java.func.ThreadChatClient;
import kh.java.func.UdpClient;

public class ClientStart {

	//Connection refused: connect 포트가 안열렸다 
	//1)서버가 안열렸다. 2)
	public static void main(String[] args) {
		//TcpClient client = new TcpClient();
		//client.tcpClient();
		QuizClient qc = new QuizClient();
		//qc.program();
		UdpClient uc = new UdpClient();
		//uc.udpClientTest();
		DNSClient dc = new DNSClient();
		//dc.dnsClinet();
		FileClient fc = new FileClient();
		//fc.client1();
		//fc.client2();
		ThreadChatClient tc = new ThreadChatClient();
		tc.main();
	}

}
