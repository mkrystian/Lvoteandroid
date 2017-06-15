package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.RSAKeyParametersDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.PublicKeyClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.VoteClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.ConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.converter.RSAKeyParametersConverterDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.model.utils.RSABlindSignaturesUtils.RSABlindedMessage;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.BlindedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.SignedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.UnblindedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Vote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

import static pl.edu.agh.student.mprezes.lvoteandroid.model.utils.RSABlindSignaturesUtils.blindMessage;
import static pl.edu.agh.student.mprezes.lvoteandroid.model.utils.RSABlindSignaturesUtils.generateRSABlindingParameters;
import static pl.edu.agh.student.mprezes.lvoteandroid.model.utils.RSABlindSignaturesUtils.unblindSignature;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

public class VoteServiceImpl extends AbstractService implements VoteService {

    private final VoteClientService clientService = getClientService(VoteClientService.class);
    private final PublicKeyClientService publicKeyClientService = getClientService(PublicKeyClientService.class);
    private final ConverterDTO<RSAKeyParameters, RSAKeyParametersDTO> keyParametersConverterDTO = new RSAKeyParametersConverterDTO();
    private RSABlindingParameters rsaBlindingParameters;

    @Override
    public boolean vote(Voting voting, VotingAnswer votingAnswer) {
        Vote vote = new Vote();
        vote.setVotingId(voting.getId());
        vote.setAnswerId(votingAnswer.getId());

        BlindedVote blindedVote = blindVote(vote);
        SignedVote signedVote = sing(blindedVote);
        UnblindedVote unblindedVote = unblind(signedVote, vote);

        return sendUnblindedVote(unblindedVote);
    }

    private BlindedVote blindVote(Vote vote) {
        BlindedVote result = new BlindedVote();

        String message = vote.getAnswerId().toString();
        RSABlindedMessage blindedMessage = null;
        try {
            blindedMessage = blindMessage(message, getRsaBlindingParameters());
        } catch (CryptoException e) {
            e.printStackTrace();
        }

        result.setVotingId(vote.getVotingId());
        result.setBlindedMessage(blindedMessage);

        return result;
    }

    private UnblindedVote unblind(SignedVote signedVote, Vote vote) {
        UnblindedVote result = new UnblindedVote();

        result.setVotingId(vote.getVotingId());
        result.setAnswerId(vote.getAnswerId());
        result.setSignature(unblindSignature(signedVote.getBlindedSignature(), getRsaBlindingParameters()));

        return result;
    }

    private SignedVote sing(BlindedVote blindedVote) {
        return clientService.signVote(blindedVote, ContextProvider.getHeadersMap());
    }

    private boolean sendUnblindedVote(UnblindedVote unblindedVote) {
        return clientService.sendUnblindedVote(unblindedVote, ContextProvider.getHeadersMap());
    }

    private RSAKeyParameters getPublicKey() {
        return keyParametersConverterDTO.convert(publicKeyClientService.getPublicKey(ContextProvider.getHeadersMap()));
    }

    private RSABlindingParameters getRsaBlindingParameters() {
        if (rsaBlindingParameters == null) {
            rsaBlindingParameters = generateRSABlindingParameters(getPublicKey());
        }

        return rsaBlindingParameters;
    }

}
