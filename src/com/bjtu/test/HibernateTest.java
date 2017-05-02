package com.bjtu.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.bjtu.entity.NannyUser;

public class HibernateTest {

	public static void main(String[] args) {
		// ��ʼ��һ��POJO����
		NannyUser user = new NannyUser();
		user.setUsername("123456@qq.com");
		user.setPassword("password");
		user.setGender(1);
		user.setName("vp");

		// ��ȡhibernate.cfg.xml���ã�����ʼ������
		Configuration cfg = new Configuration();
		cfg.configure();

		// �������û�ȡsessionFactory,Ȼ��������
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		// ʹ��HQL����ѯ
		// Query query = session.createQuery("from NannyUser");
		// List<NannyUser> userList = query.getResultList();
		// System.out.println(userList.size());

//		Query query = sessionFactory.openSession().createQuery("from NannyUser u where u.username = :username")
//				.setParameter("username", "123456@qq.com");
//		System.out.println(query.list().size());

		// ����user������nannyuser����
		 session.save(user);

		// �ύ���񣬹رջỰ
		tx.commit();
		session.close();
	}
}
