package com.candychat.net.alogin.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.wouchat.messenger.R;

import butterknife.Bind;


public class SignByEmailFinalFragment extends Fragment {

    @Bind(R.id.txt_show_phone_number)
    TextView txtShowPhone;
    @Bind(R.id.input_username)
    EditText dtUserName;
    @Bind(R.id.input_lastname)
    EditText dtLastName;
    @Bind(R.id.btn_register_now)
    TextView btnRegisterNow;
    @Bind(R.id.txt_skip)
    TextView txtSkip;
    TextView txt_show_email;
    Typeface type;

    public PrefManager prefManager;
    String mUsername;
    String mLastName;
    String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        prefManager = WOUApp.get(getActivity()).getPrefManager();
        email = prefManager.email().getOr("");
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_email_final, container, false);
         type = WOUApp.CustomFontTypeFace();
        txtShowPhone = (TextView) rootView.findViewById(R.id.txt_sign_email);
        dtUserName = (EditText) rootView.findViewById(R.id.input_username);
        dtLastName = (EditText) rootView.findViewById(R.id.input_lastname);
        txt_show_email = (TextView) rootView.findViewById(R.id.txt_show_email);
        btnRegisterNow = (TextView) rootView.findViewById(R.id.btn_register_now);
        txtSkip = (TextView) rootView.findViewById(R.id.txt_skip);
        dtUserName.setTypeface(type);
        dtLastName.setTypeface(type);



        txt_show_email.setText(email);

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserNameSettingFragment oneFragment = new UserNameSettingFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Register();

        return rootView;
    }

    public void Register(){
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = dtUserName.getText().toString();
                mLastName = dtLastName.getText().toString();

                prefManager.name().put(mUsername);
                prefManager.commit();

                UserNameSettingFragment oneFragment = new UserNameSettingFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

}