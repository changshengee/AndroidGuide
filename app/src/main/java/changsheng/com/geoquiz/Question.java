package changsheng.com.geoquiz;

/**
 * @author changshengee
 */
public class Question {
    /**
     * 问题id
     */
    private int mTextResId;

    /**
     * 答案
     */
    private boolean mAnswerTrue;

    /**
     * 是否作弊过
     */
    private boolean mIsCheated = false;

    public Question(int mTextResId, boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isCheated() {
        return mIsCheated;
    }

    public void setCheated(boolean cheated) {
        mIsCheated = cheated;
    }

}
