package junit;

import com.yc.electricity.dao.IElecTestDao;
import com.yc.electricity.domain.ElecTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by yuchuan on 3/19/16.
 */
public class TestDao {

    @Test
    public void testSave() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestDao dao = (IElecTestDao) ac.getBean(IElecTestDao.DAO_NAME);
        ElecTest et = new ElecTest();
        et.setTestName("dao111");
        et.setTestDate(new Date());
        et.setTestRemark("Test DAO");
        dao.save(et);
    }

    @Test
    public void testUpdate() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestDao dao = (IElecTestDao) ac.getBean(IElecTestDao.DAO_NAME);
        ElecTest et = new ElecTest();
        et.setTestId("402880915396585401539658944d0000");
        et.setTestName("dao2255123123");
        et.setTestDate(new Date());
        et.setTestRemark("Test DAO update");
        dao.update(et);
    }
}
