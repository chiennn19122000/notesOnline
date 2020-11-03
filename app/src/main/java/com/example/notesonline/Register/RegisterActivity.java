package com.example.notesonline.Register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.notesonline.BaseActivity;
import com.example.notesonline.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import butterknife.BindView;

import static com.example.notesonline.Constants.UrlRegister;
import static com.example.notesonline.Constants.UrlSendMail;

public class RegisterActivity extends BaseActivity {

    protected int getLayoutRes() {
        return R.layout.activity_register;
    }


    @Override
    protected void setupListener() {

    }

    @Override
    protected void populateData() {
        registerFragment();

    }

    private void registerFragment() {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.layout_register,new RegisterFragment());
        ft_add.commit();
    }

}
