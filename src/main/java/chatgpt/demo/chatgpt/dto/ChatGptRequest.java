package chatgpt.demo.chatgpt.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequest {

    private String model;
    private List<ChatGpMessage> message;

    public ChatGptRequest(String model, String prompt){
        this.model=model;
        this.message=new ArrayList<>();
        this.message.add(new ChatGpMessage("user", prompt));
    }
    
}
