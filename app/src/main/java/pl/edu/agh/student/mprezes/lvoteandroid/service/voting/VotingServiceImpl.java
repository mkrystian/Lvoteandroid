package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.VotingClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.ConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.VotingConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

/**
 * @author Krystian Majewski
 * @since 11.06.2017
 */

public class VotingServiceImpl extends AbstractService implements VotingService {

    private final VotingClientService votingClientService = getClientService(VotingClientService.class);
    private final ConverterDTO<Voting, VotingDTO> converterDTO = new VotingConverterDTO();

    @Override
    public List<Voting> getAllOwnedVotings() {
        return converterDTO.convert(votingClientService.getAllUserVotings(ContextProvider.getHeadersMap()));
    }

    @Override
    public List<Voting> getAllAvailableVotings() {
        return converterDTO.convert(votingClientService.getAvaialableVotings(ContextProvider.getHeadersMap()));
    }
}
