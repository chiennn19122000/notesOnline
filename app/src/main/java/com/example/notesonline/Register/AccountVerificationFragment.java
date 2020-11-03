package com.example.notesonline.Register;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.notesonline.R;

import java.util.Random;

import static com.example.notesonline.Constants.RequestGmail;
import static com.example.notesonline.Constants.UrlActivate;
import static com.example.notesonline.Constants.UrlSendMail;

public class AccountVerificationFragment extends Fragment {
    private int verification ;
    TextView content;
    EditText verificationcode;
    Button activate;
    private String code,gmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_verification, container, false);
        getActivity().setTitle("Kích hoạt tài khoản");
        content = (TextView) view.findViewById(R.id.content);
        verificationcode = (EditText) view.findViewById(R.id.verification_code);
        activate = (Button) view.findViewById(R.id.activate);

        Bundle bundle = getArguments();
        gmail = bundle.getString(RequestGmail);

        content.setText("Mã xác nhận đã được gửi đến \n" + gmail);
        checkVerificationCode(gmail);
        Activate();
        return view;
    }

    private void Activate() {
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("abc",code +" | " +verificationcode.getText().toString());
                if (verificationcode.getText().toString().equals(code))
                {
                    AcceptActivation();

                }
                else
                {
                    Toast.makeText(getActivity(),"Sai mã kích hoạt",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AcceptActivation() {
        AndroidNetworking.post(UrlActivate)
                .addBodyParameter("gmail",gmail)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),"Kích hoạt thành công",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void checkVerificationCode(String gmail) {
        Random rd = new Random();
        verification = rd.nextInt(1000000);
        code = String.valueOf(verification);
        if (verification<9)
        {
            code = "00000" + code;
        } else if (verification<99) {
            code = "0000" + code;
        } else if (verification<999) {
            code = "000" + code;
        } else if (verification<9999) {
            code = "00" + code;
        } else if (verification<99999) {
            code = "0" + code;
        } else
        { }


        AndroidNetworking.post(UrlSendMail)
                .addBodyParameter("receive_mail",gmail)
                .addBodyParameter("verification_code",code)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
