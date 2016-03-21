package android.gbsc.ibm.com.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_SHOW =
            "android.gbsc.ibm.com.geoquiz.answer_show";
    private static final String TAG = "CheatActivity";
    private static final String KEY_ANSWER_IS_SHOW = "answerShow";
    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShow;

    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    private TextView mVersionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAnswerIsTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) this.findViewById(R.id.answerTextView);

        if (savedInstanceState != null) {
            mAnswerIsShow = savedInstanceState.getBoolean(KEY_ANSWER_IS_SHOW);
            if (mAnswerIsShow) {
                mAnswerTextView.setText(String.valueOf(mAnswerIsTrue));
            }
            Log.i(TAG, "mAnswerIsShow = " + mAnswerIsShow);
        } else {
            mAnswerIsShow = false;
        }


        mShowAnswerButton = (Button) this.findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(String.valueOf(mAnswerIsTrue));
                mAnswerIsShow = true;
                setAnswerShowResult(mAnswerIsShow);
            }
        });

        setAnswerShowResult(mAnswerIsShow);

        mVersionTextView = (TextView) this.findViewById(R.id.buildVersionTextView);
        mVersionTextView.setText("API level " + String.valueOf(Build.VERSION.SDK_INT));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setAnswerShowResult(boolean isAnswerShow) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOW, isAnswerShow);
        setResult(Activity.RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_ANSWER_IS_SHOW, mAnswerIsShow);
    }
}
