package android.gbsc.ibm.com.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "android.gbsc.ibm.com.geoquiz.answer_is_true";
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_ANSWER_IS_SHOW = "answerShow";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Button mCheatButton;
//    private boolean mIsCheater;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, true),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int currentQuestion = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(currentQuestion);
//        mIsCheater = false;
        //mIsCheaterArray[mCurrentIndex] = false;
    }

    private void checkAnswer(boolean answer) {
        int answerId = 0;
        if (mQuestionBank[mCurrentIndex].isAnswerShow()) {
            answerId = R.string.judgment_toast;
        } else {
            if (answer == mQuestionBank[mCurrentIndex].isTrueQuestion()) {
                answerId = R.string.correct_toast;
            } else {
                answerId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(QuizActivity.this, answerId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getActionBar();
//            actionBar.setSubtitle("sub title");
//        }

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            boolean[] isAnswerShowArray = savedInstanceState.getBooleanArray(KEY_ANSWER_IS_SHOW);
            for (int i = 0; i < mQuestionBank.length; ++i) {
                mQuestionBank[i].setAnswerShow(isAnswerShowArray[i]);
            }
        }

        mQuestionTextView = (TextView) this.findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) this.findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) this.findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton = (ImageButton) this.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPrevButton = (ImageButton) this.findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mCheatButton = (Button) this.findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answer_is_true = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(EXTRA_ANSWER_IS_TRUE, answer_is_true);
//                startActivity(i);
                startActivityForResult(i, 0);
            }
        });

        updateQuestion();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        boolean[] isAnswerShowArray = new boolean[mQuestionBank.length];
        for (int i = 0; i < mQuestionBank.length; ++i) {
            isAnswerShowArray[i] = mQuestionBank[i].isAnswerShow();
        }
        savedInstanceState.putBooleanArray(KEY_ANSWER_IS_SHOW, isAnswerShowArray);

        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
//        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOW, false);
        boolean cheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOW, false);
        mQuestionBank[mCurrentIndex].setAnswerShow(cheater);
        Log.d(TAG, "mIsCheater = " + mQuestionBank[mCurrentIndex].isAnswerShow());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}