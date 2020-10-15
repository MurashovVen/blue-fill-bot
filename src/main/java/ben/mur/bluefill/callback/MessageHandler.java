package ben.mur.bluefill.callback;

import ben.mur.bluefill.services.PhraseGenerator;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageHandler {

    private final VkApiClient vkApiClient;
    private final GroupActor groupActor;
    private final ApplicationContext ctx;
    private final Random rnd = new Random();

    @Autowired
    MessageHandler(VkApiClient vkApiClient, GroupActor groupActor, ApplicationContext ctx){
        this.vkApiClient = vkApiClient;
        this.groupActor = groupActor;
        this.ctx = ctx;
    }

    public void handleMessage(Message message) throws ClientException, ApiException {
        String textMessage = message.getText().toLowerCase();
        if (textMessage.contains("фил")){
            sendChatMessage(message);
        }
    }

    private void sendChatMessage(Message message) throws ClientException, ApiException {
        PhraseGenerator phraseGenerator = ctx.getBean(PhraseGenerator.class);
        String answer = phraseGenerator.getAnswer(message.getText());
        vkApiClient.messages().send(groupActor).peerId(message.getPeerId()).
                message(answer).randomId(rnd.nextInt(99999)).execute();
    }
}
