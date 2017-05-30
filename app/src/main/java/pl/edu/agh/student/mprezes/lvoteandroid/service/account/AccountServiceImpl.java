package pl.edu.agh.student.mprezes.lvoteandroid.service.account;

import android.util.Log;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.AccountClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.model.AccountConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.context.ApplicationContext;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.ConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

/**
 * @author Krystian Majewski
 * @since 27.05.2017
 */

public class AccountServiceImpl extends AbstractService implements AccountService {

    private final ConverterDTO<Account, AccountDTO> accountConverter = new AccountConverterDTO();
    private final AccountClientService accountClientService = getClientService(AccountClientService.class);

    @Override
    public Account getAccount() {

        return accountConverter.convert(accountClientService.getAccount(ContextProvider.getHeadersMap()));
    }

    @Override
    public void setApplicationContext() {
        Log.i("ApplicationContext", "Setting application context");

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setUserAccount(getAccount());

        ContextProvider.setApplicationContext(applicationContext);
    }
}
