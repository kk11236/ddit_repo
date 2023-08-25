package hw.board.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession객체 제공하는 팩토리 클래스
 * @author PC-13
 *
 */
public class MyBatisUtil {
	private static SqlSessionFactory sqlsessionFactory;

	static {
		try {

			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml"); 

			sqlsessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	/**
	 * SqlSession객체를 제공하는 팩토리 메서드
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance() {
		return sqlsessionFactory.openSession();
	}
	
	/**
	 * SqlSession 객체를 제공하는 팩토리 메서드
	 * @param autoCommit 오토커밋 여부
	 * @return	SqlSession 객체
	 */	
	public static SqlSession getInstance(boolean autoCommit) {
		return sqlsessionFactory.openSession(autoCommit);
	}

}
