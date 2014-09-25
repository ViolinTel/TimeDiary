package com.tel.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by violintel on 7/21/14.
 */
public class SecondActivity extends Activity {
    private Button button;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        button = (Button) this.findViewById(R.id.button3);
        textView = (TextView) this.findViewById(R.id.msg1);
        editText = (EditText) this.findViewById(R.id.returnnum);
        Intent intent = getIntent();
        int a = intent.getIntExtra("a", 0);
        int b = intent.getIntExtra("b", 0);
        textView.setText(a + " + " + b + " = " + "?" );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                int c = Integer.parseInt(editText.getText().toString());
                intent.putExtra("result", c);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
