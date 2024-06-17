package kh.java.func;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FileClient {
	public void client1() {
		String serverIp = "192.168.10.20";
		int serverPort = 5678;
		
		Socket socket = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		
		try {
			socket = new Socket(serverIp, serverPort);
			System.out.println("서버 접속 완료");
			InputStream in = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream("quiz1.gif");
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(fos);
			
			while(true) {
				int data = bis.read();
				if(data == -1) {
					break;
				}
				bos.write(data);
			}
			System.out.println("파일 다운로드 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				bos.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void client2() {
		Scanner sc = new Scanner(System.in);
		String serverIp = "192.168.10.20";
		int serverPort = 9876;
		
		Socket socket = null;
		DataInputStream dis = null;//서버에서 메세지를 받을 보조스트림
		DataOutputStream dos = null;//서버에서 메세지를 전송할 보조스트림
		BufferedInputStream bis = null;//서버에서 파일을 받을 보조스트림 
		BufferedOutputStream bos = null;//서버에서 받은 파일을 저장할 보조스트림 
		
		try {
			socket = new Socket(serverIp, serverPort);
			System.out.println("서버 접속 완료");
			
			InputStream in = socket.getInputStream();
			dis = new DataInputStream(in);
			OutputStream out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			
			int fileCount = dis.readInt();
			/*
			String fileCountStr = dis.readUTF();
			int fileCount = Integer.parseInt(fileCountStr);
			*/
			for(int i = 0; i < fileCount; i++) {
				String filename = dis.readUTF();
				System.out.println(filename);
			}
			
			System.out.print("다운로드할 파일명을 입력하세요 : ");
			String filename = sc.nextLine();
			dos.writeUTF(filename);
			
			int result = dis.readInt();
			if(result == 1 ) {
				FileOutputStream fos = new FileOutputStream(filename);
				bos = new BufferedOutputStream(fos);
				bis = new BufferedInputStream(in);
				
				while(true) {
					int data = bis.read();
					if(data == -1) {
						break;
					}
					bos.write(data);
				}
				System.out.println("파일 다운로드 완료");
			}else {
				System.out.println("파일이 존재하지 않습니다.");
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
				//out과 상관이 없어서 따로 한다. 
				
				//만들어질때만 close 를 하겠다. 
				if(bis != null) {
					bis.close();
				}else {
					//주스트림이 이미 닫혀서 따로 적어줘야 한다.
					dis.close();					
				}
				if(bos != null) {
					bos.close();
				}else {
					dos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
