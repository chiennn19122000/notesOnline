package com.example.notesonline.Register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.notesonline.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.notesonline.Constants.RequestGmail;
import static com.example.notesonline.Constants.UrlRegister;

public class RegisterFragment extends Fragment {

    private EditText fullname,gmail,username,password,re_password;
    private Button register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        getActivity().setTitle("Đăng ký tài khoản");
        fullname = (EditText) view.findViewById(R.id.fullname);
        gmail = (EditText) view.findViewById(R.id.gmail);
        username = (EditText) view.findViewById(R.id.username_rigister);
        password = (EditText) view.findViewById(R.id.password_rigister);
        re_password = (EditText) view.findViewById(R.id.re_pasword_rigister);
        register = (Button) view.findViewById(R.id.btn_register);

        Register();

        return view;
    }

    private void Register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkName())
                {
                    if (checkGmail())
                    {
                        if (checkUsername())
                        {
                            if (checkPassword())
                            {

                                sendDataRegister();

                            }
                            else if (password.getText().toString().length()<6)
                            {
                                Toast.makeText(getActivity(),"Mật khẩu phải dài hơn 6 ký tự",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Tên đăng nhập nên để dài hơn 6 ký tự",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Gmail phải có đuôi là @gmail.com",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Không được để trống tên!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openFragmentAccountVerification() {

        Bundle bundle = new Bundle();
        bundle.putString(RequestGmail,gmail.getText().toString());

        FragmentManager fm = getFragmentManager();

        AccountVerificationFragment verificationFragment =new AccountVerificationFragment();
        verificationFragment.setArguments(bundle);

        FragmentTransaction ft_rep = fm.beginTransaction();
        ft_rep.replace(R.id.layout_register, verificationFragment);
        ft_rep.addToBackStack(null);
        ft_rep.commit();
    }


    private void sendDataRegister() {
        InfoRegister register = new InfoRegister(username.getText().toString(),password.getText().toString(),gmail.getText().toString(),fullname.getText().toString());
        AndroidNetworking.post(UrlRegister)
                .addBodyParameter(register)
                .setTag(register)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("response").equals("Successfully"))
                            {
                                Toast.makeText(getActivity(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                                openFragmentAccountVerification();

                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Tên đăng nhập hoặc Gmail đã được sử dung.",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }




    private boolean checkName() {
        if (fullname.getText().toString().length()>=1)
        {
            return true;
        }
        return false;
    }

    private boolean checkGmail() {
        if(gmail.getText().toString().indexOf("@gmail.com") != -1)
        {
            return true;
        }
        return false;
    }

    private boolean checkUsername() {
        if (username.getText().toString().length()>=6)
        {
            return true;
        }
        return false;
    }
    private boolean checkPassword() {
        if (password.getText().toString().equals(re_password.getText().toString()) && password.getText().toString().length() >=6)
        {
            return true;
        }
        return false;
    }
}
