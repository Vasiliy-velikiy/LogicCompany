package com.moskalev.domain.dto.impl.personDto;




import com.moskalev.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 09.03.22
 * Class  for transfer user data for update in Database
 */
@Getter
@Setter
@RequiredArgsConstructor
@Schema(name = "PersonUpdateInfo", description = "Info about person to create")
public class PersonDto {
    @Schema(description = "first name")
    String firstName;

    @Schema(description = "last name")
    String lastName;

    @Schema(description = "email")
    @Email
    String email;

    @Schema(description = "role", required = true)
    @NotNull
    Role role;

    @Schema(description = "password")
    @Size(min = 7, max = 300)
    String password;

}
