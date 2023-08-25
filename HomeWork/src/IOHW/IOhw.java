package IOHW;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOhw {

	public static void main(String[] args) {
		//File f1= new File("d:/D_Other/Tulips.jpg");
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=new FileInputStream("e:/D_Other/Tulips.jpg");
			fos=new FileOutputStream("e:/D_Other/복사본_Tulips.jpg");
			int data =0; //데이터
			while((data=fis.read())!=-1) {
				 fos.write(data);
			}
			System.out.println("파일복사 완료.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
