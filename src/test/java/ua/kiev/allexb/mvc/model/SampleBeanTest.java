package ua.kiev.allexb.mvc.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-config-test.xml", "classpath:application-context-test.xml"})
public class SampleBeanTest {

// Comment  this 'bean class="org.springframework.web.servlet.view.XmlViewResolver" ' in mvc-context.xml
// to avoid 'Caused by: java.lang.IllegalStateException:WebApplicationObjectSupport
// instance [org.springframework.web.servlet.view.XmlViewResolve'

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void sampleBeanTest() {
        SampleBean sampleBean = applicationContext.getBean("sampleBean", SampleBean.class);
        Assert.assertNotNull(sampleBean);

        sampleBean = (SampleBean) applicationContext.getBean("sampleBean");
        Assert.assertNotNull(sampleBean);

        Assert.assertEquals(sampleBean.getNumber(), 666);
        Assert.assertEquals(sampleBean.getStringValue(), "postConstructValue");
    }

}