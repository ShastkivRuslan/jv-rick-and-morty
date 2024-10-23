package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final RickAndMortyClient rickAndMortyClient;

    @PostConstruct
    public void init() {
        List<CreateCharacterDto> allCharacters = rickAndMortyClient.getAllCharactersFromApi();
        allCharacters.forEach(System.out::println);
    }
}
