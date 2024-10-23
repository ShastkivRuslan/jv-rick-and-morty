package mate.academy.rickandmorty.dto.external;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseDto(InfoDto info,
                          @JsonProperty("results")
                          List<CreateCharacterDto> characters) {
}
