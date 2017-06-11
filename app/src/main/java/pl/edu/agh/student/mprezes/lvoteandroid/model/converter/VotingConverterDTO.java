package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import org.threeten.bp.LocalDate;

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
        result.setStart(LocalDate.parse(dto.getStartDate()));
        result.setEnd(LocalDate.parse(dto.getEndDate()));

        return result;
    }
}
