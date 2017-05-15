package pl.bajtas.whoshouldcall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bajtas.whoshouldcall.config.PersistanceConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PersistanceConfig.class})
public class WhoShouldCallApplicationTests {

    @Test
    public void contextLoads() {

    }


}
