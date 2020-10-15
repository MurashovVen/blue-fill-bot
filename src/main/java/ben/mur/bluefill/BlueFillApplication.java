package ben.mur.bluefill;

import ben.mur.bluefill.callback.CallbackApiLongPollHandler;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BlueFillApplication {

    public static void main(String[] args) throws ClientException, ApiException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BlueFillApplicationContextConfiguration.class);
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
        applicationContextProvider.setApplicationContext(ctx);

        CallbackApiLongPollHandler handler = ctx.getBean(CallbackApiLongPollHandler.class);
        handler.run();

        SpringApplication.run(BlueFillApplication.class, args);
    }
}