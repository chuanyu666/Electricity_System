package junit;

import com.yc.electricity.dao.IElecTestDao;
import com.yc.electricity.domain.ElecTest;
import com.yc.electricity.service.IElecTestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuchuan on 3/19/16.
 */
public class TestService {

    @Test
    public void testSave() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        ElecTest et = new ElecTest();
        et.setTestName("test service115555");
        et.setTestDate(new Date());
        et.setTestRemark("Test service");
        elecTestService.saveElecTest(et);
    }

    @Test
    public void testUpdate() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        ElecTest et = new ElecTest();
        et.setTestId("402880915396585401539658944d0000");
        et.setTestName("test method update");
        et.setTestDate(new Date());
        et.setTestRemark("Test service");
        elecTestService.updateElecTest(et);
    }

    @Test
    public void testFindObjById() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        ElecTest elecTest = elecTestService.findElecTestById("402880915396585401539658944d0000");
        System.out.println(elecTest.getTestName());
    }

    @Test
    public void testDeleteByIds() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        String[] ids = {"4028809153912e7e0153912e817a0001","40288091539130820153913085c20001"};
        elecTestService.deleteElecTestByIds(ids);
    }

    @Test
    public void testDeleteByList() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        List<ElecTest> list = new ArrayList<>();
        ElecTest et = new ElecTest();
        et.setTestId("4028809153930d9e015393123eec0000");
        ElecTest et2 = new ElecTest();
        et2.setTestId("4028809153930d9e0153931b4c800001");
        list.add(et);
        list.add(et2);
        elecTestService.deleteElecTestByList(list);
    }

    @Test
    public  void testFindList() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IElecTestService elecTestService = (IElecTestService) ac.getBean(IElecTestService.SERVICE_NAME);
        ElecTest et = new ElecTest();
        et.setTestName("service11");
        et.setTestRemark("service");
        List<ElecTest> list = elecTestService.findElecTestListByConditionNoPage(et);
        for (ElecTest elecTest : list) {
            System.out.println(elecTest.getTestName() + "   " + elecTest.getTestRemark());
        }
    }
}
