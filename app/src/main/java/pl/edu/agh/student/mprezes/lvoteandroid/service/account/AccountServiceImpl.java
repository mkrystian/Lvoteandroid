package pl.edu.agh.student.mprezes.lvoteandroid.service.account;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.AccountServiceClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

/**
 * @author Krystian Majewski
 * @since 27.05.2017
 */

public class AccountServiceImpl extends AbstractService implements AccountService {
    @Override
    public Account getAccount() {
        AccountServiceClientService clientService = getClientService(AccountServiceClientService.class);

        AccountDTO account = clientService.getAccount(ContextProvider.getHeadersMap());

        Account result = new Account();
        result.setId(account.getId());
        result.setUsername(account.getLogin());
        result.setFirstName(account.getFirstName());
        result.setLastName(account.getLastName());
        result.setEmail(account.getEmail());

        return result;
    }
}
