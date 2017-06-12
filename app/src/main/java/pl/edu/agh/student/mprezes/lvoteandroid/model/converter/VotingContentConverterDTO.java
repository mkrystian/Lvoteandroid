package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import java.util.ArrayList;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingContentDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingContent;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

class VotingContentConverterDTO extends ConverterDTO<VotingContent, VotingContentDTO> {
    @Override
    public VotingContent convert(VotingContentDTO dto) {
        VotingContent result = new VotingContent();

        result.setId(dto.getId());
        result.setQuestion(dto.getQuestion());
        ArrayList<String> answers = new ArrayList<>();
        if (dto.getAnswers() != null) {
            answers.addAll(dto.getAnswers());
        }
        result.setAnswers(answers);

        return result;
    }
}
