package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingAnswerDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

class VotingAnswerConverterDTO extends ConverterDTO<VotingAnswer, VotingAnswerDTO> {
    @Override
    public VotingAnswer convert(VotingAnswerDTO dto) {
        VotingAnswer result = new VotingAnswer();

        result.setId(dto.getId());
        result.setAnswer(dto.getAnswer());

        return result;
    }
}
