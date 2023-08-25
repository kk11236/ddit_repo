package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiveAddr;
	private int port;	//데이터 보낼때 사용할 포트번호
	private File file;
	
	public UdpFileSender(String receiverIp, int port) {
		try {
			ds = new DatagramSocket();
			this.port = port;
			
			receiveAddr = InetAddress.getByName(receiverIp);
			file = new File("e:/D_Other/Tulips.jpg");
			
			if(!file.exists()) {
				System.out.println("해당 파일이 존재하지 않습니다.");
				System.exit(0);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//전송시작...
	public void start() throws InterruptedException {
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		try {
			//프로토콜 : 서로 통신하는 사람들이 약속을 해서 대화하는 것. 통신을 의미 할 때 하는 약속. 
			//전송 시작을 알려주기 위한 문자열 전송
			sendData("start".getBytes());
			
			//파일명 전송
			sendData(file.getName().getBytes());
			
			//총 파일 크기 전송
			sendData(String.valueOf(fileSize).getBytes());
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[1000];
			while(true) {
				Thread.sleep(10); //패킷전송가의 간격을 주기 위해서...
				
				int readBytes = fis.read(buffer, 0, buffer.length);
				
				if(readBytes == -1) {	// 다 읽은 경우...
					break;
				}
				
				sendData(buffer, readBytes); //읽은 파일내용 전송하기
				
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + "Byte(s) ("
						+ fileSize + " Byte(s) (" + (totalReadBytes * 100 / fileSize) + "%)");
				
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(ms)");
			System.out.println("평균 전송속도 : " + transferSpeed + "(Bytes/ms)");
			
			System.out.println("전송 완료...");
			
			fis.close();
			ds.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
			
		}
	}
	
	/**
	 * 바이트배열 데이터 전송하기
	 * @param data 전송할 바이트배열
	 * @throws IOException 
	 */
	private void sendData(byte[] data) throws IOException {
		
		sendData(data, data.length);
				
	}
	/**
	 * 바이트배열 데이터 전송하기
	 * @param data 전송할 바이트 배열
	 * @param length 실제 전송할 크지
	 * @throws IOException 
	 */
	private void sendData(byte[] data, int length) throws IOException {
		
		dp = new DatagramPacket(data, length, receiveAddr, port);
		ds.send(dp);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new UdpFileSender("192.168.145.13", 8888).start();
	}
	

}
