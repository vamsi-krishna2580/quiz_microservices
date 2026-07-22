package com.example.quiz_service.Quizz.Config;

import com.example.quiz_service.Quizz.Client.QuestionClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(QuestionClient.class)
@Configuration
public class HttpClientConfig {

}
