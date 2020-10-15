package ben.mur.bluefill.callback;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    private final ApplicationContext ctx;

    private static final Logger LOG = LoggerFactory.getLogger(CallbackApiLongPollHandler.class);

    @Autowired
    public CallbackApiLongPollHandler(VkApiClient vkApiClient, GroupActor groupActor, ApplicationContext ctx) {
        super(vkApiClient, groupActor);
        this.ctx = ctx;
    }

    @Override
    public void messageNew(Integer groupId, Message message) {
        LOG.info("messageNew: " + message.toString());
        MessageHandler messageHandler = ctx.getBean(MessageHandler.class);
        try {
            messageHandler.handleMessage(message);
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
