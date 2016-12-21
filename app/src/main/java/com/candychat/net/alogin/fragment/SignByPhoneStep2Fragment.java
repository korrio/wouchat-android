package com.candychat.net.alogin.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.utils.Utils;
import com.wouchat.messenger.R;

public class SignByPhoneStep2Fragment extends Fragment {
    Toolbar toolbar;

    EditText dtEmail;
    EditText dtPassword;
    Button btnNext;
    TextView txtSkip;
    CheckBox imgShowPassword;
    String numberPhone;
    TextView showPhone;
    Typeface type ;
    String email;
    String password;
    public PrefManager prefManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        prefManager = WOUApp.get(getActivity()).getPrefManager();

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_phone_number_step_2, container, false);

         type = WOUApp.CustomFontTypeFace();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            numberPhone  = bundle.getString("numberPhone");
//            Log.e("numberPhone",numberPhone);
        }

        dtEmail = (EditText) rootView.findViewById(R.id.input_email);
        dtPassword = (EditText) rootView.findViewById(R.id.input_password);
        btnNext = (Button) rootView.findViewById(R.id.btn_register_now);
        txtSkip = (TextView) rootView.findViewById(R.id.txt_skip);
        imgShowPassword = (CheckBox) rootView.findViewById(R.id.check_show_password);
        showPhone = (TextView) rootView.findViewById(R.id.txt_show_phone_number);

        dtEmail.setTypeface(type);
        dtPassword.setTypeface(type);

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prefManager.email().put(email);
                prefManager.password().put(password);
                prefManager.commit();

                UserNameSettingFragment oneFragment = new UserNameSettingFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        showPhone.setText(numberPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = dtEmail.getText().toString();
                password = dtPassword.getText().toString();


                if(Utils.isValidEmailAddress(email) ) {
                    if(password.length() >= 6) {
                        prefManager.email().put(email);
                        prefManager.password().put(password);
                        prefManager.commit();

                        SignByEmailFinalFragment oneFragment = new SignByEmailFinalFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, oneFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else {
                        Utils.theToast("Please input pass word with more than 6 letters");
                    }

                } else {
                    Utils.theToast("Please input valid email address");
                }
            }
        });

        imgShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    dtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Log.i("checker", "true");
                } else {
                    Log.i("checker", "false");
                    dtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        return rootView;
    }

    public void Next(){

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.setTitle("Sign up");
        // toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}