package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.UserGroupDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.model.UserGroup;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class UserGroupConverterDTO extends ConverterDTO<UserGroup, UserGroupDTO> {

    private static final ConverterDTO<Account, AccountDTO> accountConverter = new AccountConverterDTO();
    private static final ConverterDTO<Voting, VotingDTO> votingConverter = new VotingConverterDTO();

    @Override
    public UserGroup convert(UserGroupDTO dto) {
        UserGroup result = new UserGroup();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setOwner(accountConverter.convert(dto.getOwner()));
        result.setMembers(accountConverter.convert(dto.getMembers()));
        result.setVotings(votingConverter.convert(dto.getVotings()));

        return result;
    }
}
