package br.com.springai.test.controller;

import br.com.springai.test.service.ChatService;
import br.com.springai.test.service.ImageService;
import br.com.springai.test.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ai")
public class ChatController {

    private final ChatService chatService;
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ChatController(ChatService chatService, RecipeService recipeService, ImageService imageService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt) {
        return chatService.getResponseWithOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "none") String dietaryRestriction) {

        return recipeService.createRecipe(ingredients, cuisine , dietaryRestriction);
    }

    @GetMapping("generate-image")
    public List<String> generateImages(@RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") Integer height,
                                       @RequestParam(defaultValue = "1024") Integer width) {

        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, height, width);

        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .collect(Collectors.toList());

        return imageUrls;
    }

}
