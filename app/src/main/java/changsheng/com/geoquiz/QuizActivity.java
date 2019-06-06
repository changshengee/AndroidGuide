package changsheng.com.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * @author changshengee
 */
public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mTrueButton =  findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
    }
}
