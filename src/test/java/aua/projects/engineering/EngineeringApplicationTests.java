package aua.projects.engineering;

import aua.projects.engineering.dao.EmergencyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineeringApplicationTests {

    @Autowired
    private EmergencyDao emergencyDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDao(){
        assertTrue(emergencyDao.getAllUsers() != null);
    }

}
