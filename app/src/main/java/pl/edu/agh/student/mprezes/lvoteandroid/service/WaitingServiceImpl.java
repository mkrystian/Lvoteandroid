package pl.edu.agh.student.mprezes.lvoteandroid.service;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;

import static android.content.Context.MODE_PRIVATE;
import static android.content.SharedPreferences.Editor;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

public class WaitingServiceImpl extends AbstractService implements WaitingService {

    private static final String PREFS_KEY = "WaitingList";
    private static Set<WaitingVote> waitingVotes;
    private static SharedPreferences sharedPrefs;

    public static void initialize(Activity context) {
        sharedPrefs = context.getPreferences(MODE_PRIVATE);
        readWaitingVotes(sharedPrefs);
    }

    @Override
    public boolean addVote(WaitingVote waitingVote) {
        waitingVotes.add(waitingVote);
        saveWaitingVotes();

        return true;
    }

    private static void readWaitingVotes(SharedPreferences sharedPrefs) {
        String waitingListString = sharedPrefs.getString(PREFS_KEY, null);
        if (StringUtils.isNotBlank(waitingListString)) {
            waitingVotes = parseJSONString(waitingListString);
        } else {
            waitingVotes = new HashSet<>();
        }
    }

    private static Set<WaitingVote> parseJSONString(String waitingListString) {
        ObjectReader objectReader = new ObjectMapper().reader().forType(new TypeReference<Set<WaitingVote>>() {
        });

        Set<WaitingVote> result = new HashSet<>();

        try {
            result = objectReader.readValue(waitingListString);
        } catch (IOException e) {
            Log.e("Read waiting votes", "Could not parse waiting list", e);
        }
        return result;
    }

    private void saveWaitingVotes() {
        String waitingVotesString = convertToJSON();

        if (StringUtils.isNotBlank(waitingVotesString)) {
            Editor editor = sharedPrefs.edit();
            editor.putString(PREFS_KEY, waitingVotesString);
            editor.apply();
        }

    }

    private String convertToJSON() {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String waitingVotesString = StringUtils.EMPTY;
        try {
            waitingVotesString = objectWriter.writeValueAsString(waitingVotes);
        } catch (JsonProcessingException e) {
            Log.e("Save waiting votes", "Could not convert to JSON", e);
        }
        return waitingVotesString;
    }
}
