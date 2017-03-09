package morxander.testingeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class ChildActivity extends AppCompatActivity {

    EditText childActivityEditText;
    Button childActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        // init the views
        childActivityEditText = (EditText) findViewById(R.id.childactivity_edittext);
        childActivityButton = (Button) findViewById(R.id.childactivity_button);
    }
}
