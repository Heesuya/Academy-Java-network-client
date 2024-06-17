package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseballClient {
	public void client() {
		Scanner sc = new Scanner(System.in);

		String serverIp = "127.0.0.1";// 루프백 아이피:자기자신을 호출하는 아이피 주소 (내 자신을 요청하는구나~)
		int serverPort = 9880;

		int count = 0;

		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try {
			socket = new Socket(serverIp, serverPort);
			System.out.println("서버접속완료");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);

			int check = dis.readInt();
			if (check == 1) {
				System.out.println("< < < < < Game Start > > > > >");
			}

			while (true) {

				ArrayList<Integer> clientNum = new ArrayList<Integer>();
				// System.out.println();
				// int num = sc.nextInt();

				for (int i = 0; i < 3; i++) {
					System.out.print(i + 1 + "번째 숫자입력 : ");
					int num = sc.nextInt();
					dos.writeInt(num);
				}

				System.out.println(clientNum);

				// String serverMsg = dis.readUTF();
				// System.out.println(serverMsg);
				count++;
				String serverMsg = dis.readUTF();
				System.out.println(serverMsg);
				if(serverMsg.equals("3스트라이크")) {
					break;
				}else if (count == 10) {
					System.out.println("기회끝");
					break;
				}
				
			} // while

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // catch

		} // finally 종료
	}// client 종료
}
