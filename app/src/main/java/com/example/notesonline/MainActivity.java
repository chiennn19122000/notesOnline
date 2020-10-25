package com.example.notesonline;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notesonline.fragment.RegisterFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.layout_login)
    RelativeLayout loginLayout;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.register)
    TextView register;

    @BindView(R.id.forget_pw)
    TextView forgetPwd;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupListener() {

        HideTitle();
    }

    @Override
    protected void populateData() {
        Register();
    }
    private void Register()
    {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentRegister();
            }
        });
    }
    private void fragmentRegister()
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.layout_login,new RegisterFragment());
        ft_add.addToBackStack(null);
        ft_add.commit();
    }
}