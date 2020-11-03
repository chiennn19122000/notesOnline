package com.example.notesonline.ForgetPw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesonline.R;

public class ForgetPwFragment extends Fragment {
    private TextView result_user;
    private EditText forget_user;
    private Button request_pw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pw, container, false);
        result_user = (TextView) view.findViewById(R.id.result_user);
        forget_user = (EditText) view.findViewById(R.id.userforget);
        request_pw = (Button) view.findViewById(R.id.request_pw);
        return view;
    }
}
