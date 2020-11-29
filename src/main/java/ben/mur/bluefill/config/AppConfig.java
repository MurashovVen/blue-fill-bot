package ben.mur.bluefill.config;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:vk.properties")
@ComponentScan(basePackages = "ben.mur.bluefill")
public class AppConfig {

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public VkApiClient vkApiClient() throws ClientException, ApiException {
        VkApiClient vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
        GroupActor groupActor = ctx.getBean(GroupActor.class);

        vkApiClient.groups().getLongPollServer(groupActor, groupActor.getGroupId()).execute();

        return vkApiClient;
    }

    @Bean
    public GroupActor groupActor() {
        Environment env = ctx.getEnvironment();

        String groupId = env.getProperty("groupId");
        String accessToken = env.getProperty("token");

        return new GroupActor(Integer.parseInt(groupId), accessToken);
    }
}