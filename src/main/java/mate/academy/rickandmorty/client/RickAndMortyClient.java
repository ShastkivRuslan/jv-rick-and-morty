package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    public List<CreateCharacterDto> getAllCharactersFromApi() {
        List<CreateCharacterDto> characters = new ArrayList<>();
        String url = BASE_URL;

        ResponseDto response;
        do {
            response = getPageWithCharacters(url);
            characters.addAll(response.characters());
            url = response.info().next();
        } while (url != null);

        return characters;
    }

    private ResponseDto getPageWithCharacters(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> send
                    = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(send.body(), ResponseDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}