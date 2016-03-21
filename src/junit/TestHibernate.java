package junit;

import com.yc.electricity.domain.ElecTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.junit.Test;

import java.util.Date;

/**
 * Created by yuchuan on 3/19/16.
 */
public class TestHibernate {

    @Test
    public void testSave() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ElecTest et = new ElecTest();
        et.setTestName("hinbernate5");
        et.setTestDate(new Date());
        et.setTestRemark("Test Hibernate555");
        session.save(et);
        transaction.commit();
        session.close();
    }

}
