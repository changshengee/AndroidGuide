package changsheng.com.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author changshengee
 */
public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int currentIndex = 0;
    private boolean mIsCheater;

    private static final int RESULT_CODE_CHEAT = 0;

    public static final String TAG = "QuizActivity";
    public static final String KEY_INDEX = "index";
    public static final String KEY_CHEAT = "cheat";

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mCheatButton = findViewById(R.id.cheat_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEAT);
        }
        updateQuestion();
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex = (currentIndex + 1) % mQuestionBank.length;
                        updateQuestion();
                        mIsCheater = false;
                    }
                });
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Start Cheat!");
                // Intent intent=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[currentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                //startActivity(intent);
                startActivityForResult(intent, RESULT_CODE_CHEAT);
            }
        });
        mQuestionTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex = (currentIndex + 1) % mQuestionBank.length;
                        updateQuestion();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = CheatActivity.wasAnswerShown(data);
        mQuestionBank[currentIndex].setCheated(mIsCheater);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, currentIndex);
        outState.putBoolean(KEY_CHEAT, mIsCheater);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[currentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressed) {
        boolean answerStatus = mQuestionBank[currentIndex].isAnswerTrue();
        boolean cheat = mQuestionBank[currentIndex].isCheated();
        int message = userPressed == answerStatus ? R.string.correct_toast : R.string.incorrect_toast;
        if (cheat) {
            message = R.string.judgment;
        }
        Toast.makeText(QuizActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
