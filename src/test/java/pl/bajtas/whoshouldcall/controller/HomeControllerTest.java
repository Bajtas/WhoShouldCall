package pl.bajtas.whoshouldcall.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import pl.bajtas.whoshouldcall.config.AppConfig;
import pl.bajtas.whoshouldcall.config.PersistanceConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Bajtas on 16.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PersistanceConfig.class, AppConfig.class})
public class HomeControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private HomeController homeController;
    @Mock
    private RequestAttributes requestAttributes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        RequestContextHolder.setRequestAttributes(requestAttributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void renderHomePageViewTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("index"));
    }
}
