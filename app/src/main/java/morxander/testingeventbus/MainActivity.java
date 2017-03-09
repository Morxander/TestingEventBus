package morxander.testingeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mainActivityTextView;
    Button mainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init the views
        mainActivityTextView = (TextView) findViewById(R.id.mainactivity_textview);
        mainActivityButton = (Button) findViewById(R.id.mainactivity_button);
    }
}
