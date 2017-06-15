package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import org.bouncycastle.crypto.params.RSAKeyParameters;

import java.math.BigInteger;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.RSAKeyParametersDTO;

/**
 * @author Krystian Majewski
 * @since 15.06.2017
 */

public class RSAKeyParametersConverterDTO extends ConverterDTO<RSAKeyParameters, RSAKeyParametersDTO> {
    @Override
    public RSAKeyParameters convert(RSAKeyParametersDTO dto) {
        RSAKeyParameters result = new RSAKeyParameters(dto.isPrivate(), new BigInteger(dto.getModulus()), dto.getExponent());

        return result;
    }
}
