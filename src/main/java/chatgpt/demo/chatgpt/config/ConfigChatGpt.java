package chatgpt.demo.chatgpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigChatGpt {
    
    @Value("${openai.key}") //go tho the application.properties and get key
    private String apiKey;

    @Bean
    public RestTemplate template(){

      RestTemplate restTemplate = new RestTemplate(); //initializing 

     //intercepet request and add this headers inside request 
      restTemplate.getInterceptors().add(( request, body, execution)->{
        request.getHeaders().add("Authorization", "Bearer "+apiKey);
        request.getHeaders().add("Content-Type", "application/json");
        return execution.execute(request, body);
      });
    return restTemplate;
    }
    
}
