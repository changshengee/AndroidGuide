package changsheng.com.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author changshengee
 */
public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.changshengee.geoquiz.answer_is_true";

    private static final String EXTRA_ANSWER_SHOWN = "com.changshengee.geoquiz.answer_shown";
    private static final String KEY_ANSWER = "answer";

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private boolean mAnswerIsTrue;
    private TextView mAnswerView;
    private Button mShowAnswer;
    private boolean mIsAnswerShown;

    private static final String KEY_ANSWER_SHOWN = "answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer_button);
        if (savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
            setAnswerShownResult();
            mAnswerIsTrue = savedInstanceState.getBoolean(KEY_ANSWER);
            setAnswerShownResult();
            if (mAnswerIsTrue) {
                mAnswerView.setText(R.string.true_button);
            } else {
                mAnswerView.setText(R.string.false_button);
            }
        } else {
            mIsAnswerShown = false;
        }
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerView.setText(R.string.true_button);
                } else {
                    mAnswerView.setText(R.string.false_button);
                }
                mIsAnswerShown = true;
                setAnswerShownResult();
                int cx = mShowAnswer.getWidth() / 2;
                int cy = mShowAnswer.getHeight() / 2;
                float radius = mShowAnswer.getWidth();
                Animator animator = ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mShowAnswer.setVisibility(View.INVISIBLE);
                    }
                });
                animator.start();
            }
        });
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN, mIsAnswerShown);
    }
}
