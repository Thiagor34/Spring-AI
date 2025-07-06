package br.com.springai.test.service;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, Integer n,
                                       Integer height, Integer width) {
        ImageResponse imageResponse = imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width)
                                .build())
        );
//        ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt));

        return imageResponse;
    }
}
