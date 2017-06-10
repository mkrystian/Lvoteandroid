package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import org.threeten.bp.LocalDateTime;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class VotingConverterDTO extends ConverterDTO<Voting, VotingDTO> {
    @Override
    public Voting convert(VotingDTO dto) {
        Voting result = new Voting();

        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setStart(LocalDateTime.parse(dto.getStartDateTime()));
        result.setEnd(LocalDateTime.parse(dto.getEndDateTime()));

        return result;
    }
}
