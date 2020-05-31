package com.geekvan.test;
import com.geekvan.dao.IUserDao;
import com.geekvan.doman.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MybaitsTest {
    public static void main(String[] args) throws IOException {

        //1.读取配置文件
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建工厂
        SqlSessionFactoryBuilder builder=
                new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //3.sqlSeeion
        SqlSession session=factory.openSession();
        //4.接口代理对象
        IUserDao userDao=session.getMapper(IUserDao.class);
        //5.执行方法
        List<User> users=userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
