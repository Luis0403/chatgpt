package chatgpt.demo.chatgpt.ChatGptController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import chatgpt.demo.chatgpt.dto.ChatGptRequest;
import chatgpt.demo.chatgpt.dto.ChatGptResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/bot")
public class ChatGptController {

    @Value("${openai.model}") //go tho the application.properties and get model
    private String model;

    @Value(("${openai.url}")) //go tho the application.properties and get url
    private String apiUrl; 

    @Autowired
    private RestTemplate template;

    
    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){

        //creating request, passinf the variables inside ChatGptRequest the model and question
        ChatGptRequest request=new ChatGptRequest(model, prompt);

        //making request to openAI
        ChatGptResponse response=template.postForObject(apiUrl, request, ChatGptResponse.class);

        //return only the response from openai
        return response.getChoices().get(0).getMessage().getContent();
    }
    
}
