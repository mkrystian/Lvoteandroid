package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;

/**
 * @author Krystian Majewski
 * @since 30.05.2017
 */

public class AccountConverterDTO extends ConverterDTO<Account, AccountDTO> {
    @Override
    public Account convert(AccountDTO accountDTO) {
        Account result = new Account();

        result.setId(accountDTO.getId());
        result.setUsername(accountDTO.getLogin());
        result.setFirstName(accountDTO.getFirstName());
        result.setLastName(accountDTO.getLastName());
        result.setEmail(accountDTO.getEmail());

        return result;
    }
}
