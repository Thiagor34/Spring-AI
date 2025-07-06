package br.com.springai.test.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestriction) {
        var template = """
                Preciso criar uma receita usando os seguintes ingredientes: {ingredients}.
                Eu tenho preferencia por uma cozinha {cuisine}.
                Por favor, considere uma dieta com restrição de : {dietaryRestriction}.
                Por favor, providencie a receita com um titulo,  detalhes dos ingredientes, detalhes do preparo.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestriction", dietaryRestriction
        );

        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
