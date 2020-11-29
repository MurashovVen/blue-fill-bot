package ben.mur.bluefill.callback;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    private final MessageHandler messageHandler;
    private static final Logger LOG = LoggerFactory.getLogger(CallbackApiLongPollHandler.class);

    @Autowired
    public CallbackApiLongPollHandler(VkApiClient vkApiClient, GroupActor groupActor, MessageHandler messageHandler) throws ClientException, ApiException {
        super(vkApiClient, groupActor);
        this.messageHandler = messageHandler;

        this.run();
    }

    @Override
    public void messageNew(Integer groupId, Message message) {
        LOG.info("messageNew: " + message.toString());

        try {
            messageHandler.handleMessage(message);
        } catch (ClientException | ApiException e) {
            log.warn(e.getMessage());
        }
    }
}
