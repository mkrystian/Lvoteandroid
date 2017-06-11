package pl.edu.agh.student.mprezes.lvoteandroid.service.usergroup;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.UserGroupDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.UserGroupClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.model.UserGroup;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.ConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.UserGroupConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

/**
 * @author Krystian Majewski
 * @since 08.06.2017
 */

public class UserGroupServiceImpl extends AbstractService implements UserGroupService {

    private final UserGroupClientService userGroupClientService = getClientService(UserGroupClientService.class);
    private final ConverterDTO<UserGroup, UserGroupDTO> converter = new UserGroupConverterDTO();
    private final Account userAccount = ContextProvider.getApplicationContext().getUserAccount();

    private List<UserGroup> getAllUserGroups() {
        return converter.convert(userGroupClientService.getUserGroups(ContextProvider.getHeadersMap()));
    }

    @Override
    public List<UserGroup> getUserGroups() {
        List<UserGroup> result = new ArrayList<>();

        for (UserGroup group : getAllUserGroups()) {
            if (userAccount.equals(group.getOwner())) {
                result.add(group);
            }
        }
        return result;
    }

    @Override
    public List<UserGroup> getGroupsUserBelong() {
        List<UserGroup> result = new ArrayList<>();

        for (UserGroup group : getAllUserGroups()) {
            if (group.getMembers().contains(userAccount)) {
                result.add(group);
            }
        }
        return result;
    }

}
