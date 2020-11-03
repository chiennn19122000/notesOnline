package com.example.notesonline.ForgetPw;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notesonline.BaseActivity;
import com.example.notesonline.R;
import com.example.notesonline.Register.RegisterFragment;

public class ForgetPasswordActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void setupListener() {
        setTitle("Quên mật khẩu");
    }

    @Override
    protected void populateData() {
        forgetPwFragment();
    }

    private void forgetPwFragment() {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.layout_forgetpw,new ForgetPwFragment());
        ft_add.commit();
    }
}
