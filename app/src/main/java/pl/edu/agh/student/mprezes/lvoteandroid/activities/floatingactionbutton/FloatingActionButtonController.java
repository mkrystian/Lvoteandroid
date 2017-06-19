package pl.edu.agh.student.mprezes.lvoteandroid.activities.floatingactionbutton;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.WaitingListActivity;
import pl.edu.agh.student.mprezes.lvoteandroid.service.WaitingService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.WaitingServiceImpl;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

public class FloatingActionButtonController {

    private final WaitingService service = new WaitingServiceImpl();
    private final Activity context;
    private final FloatingActionButton button;


    public FloatingActionButtonController(FloatingActionButton button, Activity context) {
        this.context = context;
        this.button = button;
    }

    public void create() {
        setIcon();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, WaitingListActivity.class));
            }
        });
    }

    public void refresh() {
        setIcon();
    }

    private void setIcon() {
        if (service.hasNewVotes()) {
            button.setImageResource(R.drawable.ic_warining);
        } else {
            button.setImageResource(R.drawable.ic_mail);
        }
    }
}
