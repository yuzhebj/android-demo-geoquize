package android.gbsc.ibm.com.geoquiz;

/**
 * Created by yuzhe on 2/16/2016.
 */
public class TrueFalse {

    private int mQuestion;
    private boolean mTrueQuestion;

    private boolean mAnswerShow;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        mAnswerShow = false;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public boolean isAnswerShow() {
        return mAnswerShow;
    }

    public void setAnswerShow(boolean mAnswerShow) {
        this.mAnswerShow = mAnswerShow;
    }
}
