package com.tel.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Base64InputStream;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

/**
 * Created by violintel on 7/14/14.
 */
public class Test1 extends Activity {
    public MidApp midApp;
    private EditText one, two, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(Test1.this);
        builder.setIcon(R.drawable.icon);
        builder.setTitle("This is a dialog");
        builder.setMessage("Do you want to leave?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Test1.this, "you choose OK", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Test1.this, "you choose Cancle", Toast.LENGTH_LONG).show();
            }
        });

        final AlertDialog dialog = builder.create();
        Button button1 = (Button) this.findViewById(R.id.button1);
        final TextView textView = (TextView) this.findViewById(R.id.text1);
        final TextView textView1 = (TextView) this.findViewById(R.id.text2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midApp = (MidApp) getApplication();
                String name1 = midApp.getName();
                midApp.setName("Second");
                String name2 = midApp.getName();

                MyData myData = new MyData("Jack");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                String base64String = "";
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(myData);
                    base64String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    objectOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(base64String);
                dialog.show();
                textView.setText(name1 + " " + name2);
                byte[] base64_byte = Base64.decode(clipboardManager.getText().toString(), Base64.DEFAULT);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64_byte);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    MyData myData1 = (MyData) objectInputStream.readObject();
                    textView1.setText(myData.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        one = (EditText) this.findViewById(R.id.one);
        two = (EditText) this.findViewById(R.id.two);
        result = (EditText) this.findViewById(R.id.result);
        Button button2 = (Button) this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(one.getText().toString());
                int b = Integer.parseInt(two.getText().toString());

                Intent intent = new Intent(Test1.this, SecondActivity.class);
                intent.putExtra("a", a);
                intent.putExtra("b", b);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int c = data.getIntExtra("result", 0);
                result.setText(String.valueOf(c));
            }
        }


    }
}
