package org.fkit.test;

import java.io.InputStream;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fkit.domain.User;

public class MyBatisTest {

	static String[] uNames = { "admin", "developer", "python", "java" };

	public static void main(String[] args) throws Exception {
		// 读取mybatis-config.xml文件
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		// 初始化mybatis，创建SqlSessionFactory类的实例
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 创建Session实例
		SqlSession session = sqlSessionFactory.openSession();
		// 创建User对象
		Random rd = new Random();

		for (int i = 1; i < 100; i++) {
			int idx = rd.nextInt(uNames.length);
			User user = new User(uNames[idx], "男", 26 + rd.nextInt(uNames.length));
			// 插入数据
			session.insert("org.fkit.mapper.UserMapper.save", user);
		}
		// 提交事务
		session.commit();
		// 关闭Session
		session.close();
	}

}
