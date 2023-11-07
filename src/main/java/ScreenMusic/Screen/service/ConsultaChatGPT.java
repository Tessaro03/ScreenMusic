package ScreenMusic.Screen.service;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

public static String obterInformacao(String texto) {
    OpenAiService service = new OpenAiService(System.getenv("CHATGPT_KEY"));

    CompletionRequest requisicao = CompletionRequest.builder()
    .model("text-davinci-003")
    .prompt("Me fale sobre: " + texto)
    .maxTokens(1000)
    .temperature(0.7)
    .build();

    var resposta = service.createCompletion(requisicao);
    return resposta.getChoices().get(0).getText();
}
}
