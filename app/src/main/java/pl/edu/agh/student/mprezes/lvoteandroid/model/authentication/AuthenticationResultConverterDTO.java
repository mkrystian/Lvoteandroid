package pl.edu.agh.student.mprezes.lvoteandroid.model.authentication;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationResponseDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.ConverterDTO;

/**
 * @author Krystian Majewski
 * @since 30.05.2017
 */

public class AuthenticationResultConverterDTO implements ConverterDTO<AuthenticationResult, AuthenticationResponseDTO> {
    @Override
    public AuthenticationResult convert(AuthenticationResponseDTO object) {
        //TODO: Implement this converter
        return null;
    }
}
