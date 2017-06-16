package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VoteDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Vote;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

class VoteConverterDTO extends ConverterDTO<Vote, VoteDTO> {
    @Override
    public Vote convert(VoteDTO dto) {
        Vote result = new Vote();
        result.setVotingId(dto.getId());
        result.setAnswerId(dto.getAnswerId());
        return result;
    }
}
