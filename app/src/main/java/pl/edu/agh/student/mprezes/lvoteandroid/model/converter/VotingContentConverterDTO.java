package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingAnswerDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingContentDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingContent;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

class VotingContentConverterDTO extends ConverterDTO<VotingContent, VotingContentDTO> {
    private final ConverterDTO<VotingAnswer, VotingAnswerDTO> votingAnswerConverterDTO = new VotingAnswerConverterDTO();

    @Override
    public VotingContent convert(VotingContentDTO dto) {
        VotingContent result = new VotingContent();

        result.setId(dto.getId());
        result.setQuestion(dto.getQuestion());
        result.setAnswers(votingAnswerConverterDTO.convert(dto.getAnswers()));

        return result;
    }
}
