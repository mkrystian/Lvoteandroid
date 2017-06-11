package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.UserGroupDTO;

/**
 * @author Krystian Majewski
 * @since 08.06.2017
 */

public interface UserGroupClientService extends ClientService {

    @RequestLine("GET /user-groups-owned")
    List<UserGroupDTO> getUserGroupsOwned(@HeaderMap Map<String, ?> headerMap);

    @RequestLine("GET /user-groups-belongs")
    List<UserGroupDTO> getUserGroupsUserBelongsTo(@HeaderMap Map<String, ?> headerMap);
}
