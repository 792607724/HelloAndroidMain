package com.example.helloandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private Button basicDialog, listDialog, singleChoiceDialog, multipleChoiceDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.initButton();


    }

    private void initButton() {
        basicDialog = findViewById(R.id.basicDialog);
        listDialog = findViewById(R.id.listDialog);
        singleChoiceDialog = findViewById(R.id.singleChoiceDialog);
        multipleChoiceDialog = findViewById(R.id.multipleChoiceDialog);

        basicDialog.setOnClickListener(this);
        listDialog.setOnClickListener(this);
        singleChoiceDialog.setOnClickListener(this);
        multipleChoiceDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.basicDialog:
                basicDialogOperate();
                break;
            case R.id.listDialog:
                listDialogOperate();
                break;
            case R.id.singleChoiceDialog:
                singleChoiceDialogOperate();
                break;
            case R.id.multipleChoiceDialog:
                multipleChoiceDialogOperate();
                break;
        }
    }

    private void basicDialogOperate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setIcon(R.drawable.laofuzi);
        builder.setTitle("基础对话框");
        builder.setMessage("这是一个基础对话框！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "点击了确定", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "点击了取消", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "点击了好的", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    String[] items = {"Android", "IOS", "Web", "Django", "Python"};

    private void listDialogOperate() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setTitle("请选择一个技术分支：");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "点击了" + items[i], Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    int index;

    private void singleChoiceDialogOperate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setSingleChoiceItems(items, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                index = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "选择了" + items[index], Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "你没有做出选择", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    boolean[] bools = {false, false, false, false, false};

    private void multipleChoiceDialogOperate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setTitle("请选择技术分支：");
        builder.setMultiChoiceItems(items, bools, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                bools[i] = b;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < items.length; j++) {
                    if (bools[j]) {
                        buffer.append(items[j] + " ");
                    }
                }
                Toast.makeText(MainActivity3.this, "选择了" + buffer.toString(), Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}