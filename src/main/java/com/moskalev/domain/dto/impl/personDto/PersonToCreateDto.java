package com.moskalev.domain.dto.impl.personDto;

import com.moskalev.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 09.03.22
 * Class  for transfer user data for create in Database
 */

@Getter
@Setter
@Schema(name = "PersonCreateInfo", description = "Info about person to create")
@RequiredArgsConstructor
public class PersonToCreateDto {

    @Schema(description = "first name", required = true)
    @NotBlank(message = "first name should be valid")
    @Size(max = 300)
    private String firstName;

    @Schema(description = "last name", required = true)
    @NotBlank
    @Size(max = 300)
    private String lastName;

    @Schema(description = "email", required = true)
    @NotBlank
    @Email(message = "email should be valid")
    @Size(max = 30)
    private String email;

    @Schema(description = "password", required = true)
    @NotBlank
    @Size(min = 7, max = 300)
    private String password;

    @Schema(description = "role", required = true)
    @NotNull
    private Role role;
}
