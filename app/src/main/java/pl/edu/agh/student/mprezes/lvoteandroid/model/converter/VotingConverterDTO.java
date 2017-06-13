package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import org.threeten.bp.LocalDate;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingContentDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingContent;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class VotingConverterDTO extends ConverterDTO<Voting, VotingDTO> {

    private final ConverterDTO<Account, AccountDTO> accountConverterDTO = new AccountConverterDTO();
    private final ConverterDTO<VotingContent, VotingContentDTO> votingContentConverterDTO = new VotingContentConverterDTO();

    @Override
    public Voting convert(VotingDTO dto) {
        Voting result = new Voting();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setStart(LocalDate.parse(dto.getStartDate()));
        result.setEnd(LocalDate.parse(dto.getEndDate()));
        result.setAlreadyVoted(accountConverterDTO.convert(dto.getAlreadyVoteds()));
        result.setVotingContent(votingContentConverterDTO.convert(dto.getContent()));

        return result;
    }
}
