package io.github.jaoxavier.myOwnEcormmece.rest.dto;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditedClientDTO {
    private Integer client_id;
    private String name;
    private Gender gender;
    private String email;
    private String phone_number;
    private String cell_number;
}
