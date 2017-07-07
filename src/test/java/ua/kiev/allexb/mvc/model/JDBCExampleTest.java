package ua.kiev.allexb.mvc.model;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-config-test.xml", "classpath:application-context-test.xml"})
@FixMethodOrder(MethodSorters.JVM)
public class JDBCExampleTest {

//IMPORTANT - DISABLE THIS <bean class="org.springframework.web.servlet.view.XmlViewResolver"> in mvc-config.xml

    @Autowired
    private JDBCExample jdbcExample;

    //JDBC TEMPLATE INSERT TEST EXAMPLE
    @Test
    public void insertLogTest() {
        DBLog dbLog = new DBLog(10,"test Log 10");
        Assert.assertTrue(jdbcExample.insertLog(dbLog));
    }

    //JDBC TEMPLATE SELECT TEST EXAMPLE
    @Test
    public void queryAllLogsTest() {
        List<DBLog> dbLogs = jdbcExample.queryAllLogs();
        Assert.assertNotNull(dbLogs);
        for (DBLog dbLog : jdbcExample.queryAllLogs()) {
            System.out.println("DBLog id: "+ dbLog.getIdLog() + " DBLog logString: "+ dbLog.getLogString());
        }
    }

    @Test
    public void queryAllUsers() {
        List<User> users = jdbcExample.queryAllUsers();
        Assert.assertNotNull(users);
        for (User user : jdbcExample.queryAllUsers()) {
            System.out.println("User id: "+ user.getIdUser() + " User username: "+ user.getUsername());
        }
    }

    //JDBC TEMPLATE DELETE TEST EXAMPLE
    @Test
    public void deleteUSERTest() {
        Assert.assertTrue(jdbcExample.deleteUSER(5));
    }

    //JDBC TEMPLATE UPDATE TEST EXAMPLE
    @Test
    public void updateUserEnableTest() {
        User user = new User();
        user.setUsername("test1@outlook.com");
        Assert.assertTrue(jdbcExample.updateUserEnable(user, false));
    }

	/*//TEST METHOD for Test Table inside HSQLDB
    @Test
	public void queryAllTestTableRecords() {
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM TEST");
    	for (Map<String, Object> row : rows) {
				System.out.println("TESTTABLE tectcolumn: " + row.get("TESTCOLUMN"));
		}
	}*/

}