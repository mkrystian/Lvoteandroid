package pl.edu.agh.student.mprezes.lvoteandroid.service.usergroup;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.UserGroupDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.UserGroupClientService;
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


    @Override
    public List<UserGroup> getUserGroups() {
        return converter.convert(userGroupClientService.getUserGroupsOwned(ContextProvider.getHeadersMap()));
    }

    @Override
    public List<UserGroup> getGroupsUserBelong() {
        return converter.convert(userGroupClientService.getUserGroupsUserBelongsTo(ContextProvider.getHeadersMap()));
    }

}
