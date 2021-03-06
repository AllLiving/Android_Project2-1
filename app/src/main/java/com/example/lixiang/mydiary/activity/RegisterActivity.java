package com.example.lixiang.mydiary.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.example.lixiang.mydiary.R;
import com.example.lixiang.mydiary.callback.OnSignUpCallback;
import com.example.lixiang.mydiary.model.User;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtnRegister;
    private EditText mEtUser;
    private EditText mEtPwd;
    private EditText mEtConfirm;
    private String mUser;
    private String mPwd;
    private String mConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkInput()){
                    Toast.makeText(getApplicationContext(),"输入不合法",Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setUsername(mUser);
                user.setPassword(mPwd);
                user.setEmail(mUser);
                user.signUpInBackground(new OnSignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.putExtra("from","RegisterActivity");
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtConfirm = (EditText) findViewById(R.id.et_confirm);
    }

    private boolean checkInput(){
        mUser = mEtUser.getText().toString();
        mPwd = mEtPwd.getText().toString();
        mConfirm = mEtConfirm.getText().toString();

        if (mUser.equals("") || mPwd.equals("") || mConfirm.equals("")){
            Toast.makeText(getApplicationContext(),"输入不合法",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mPwd.equals(mConfirm)){
            Toast.makeText(getApplicationContext(),"输入密码不匹配",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
