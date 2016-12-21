package com.module.candychat.net.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.module.candychat.net.R;
import com.module.candychat.net.adapter.InviteAdapter;
import com.module.candychat.net.model.Person;

public class ContactInviteActivity extends AppCompatActivity {
    //private AutoLabelUI mAutoLabel;
    private List<Person> mPersonList;
    private InviteAdapter adapter;
    private RecyclerView recyclerView;
    private Button ok;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_fragment);
       // mAutoLabel = (AutoLabelUI) findViewById(R.id.label_view);
        recyclerView = (RecyclerView) findViewById(R.id.invite_fragment);
        ok = (Button) findViewById(R.id.ok);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);

        mPersonList = new ArrayList<>();

        //Populate list
        List<String> names = Arrays.asList(getResources().getStringArray(R.array.names));
        List<String> ages = Arrays.asList(getResources().getStringArray(R.array.ages));
        TypedArray photos = getResources().obtainTypedArray(R.array.photos);

        for(int i = 0; i<names.size(); i++){
            mPersonList.add(new Person(names.get(i), ages.get(i), photos.getResourceId(i, -1), false));
        }

        photos.recycle();

        adapter = new InviteAdapter(mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new InviteAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                itemListClicked(position);
                 name = mPersonList.get(position).getName();
                Log.e("dddd", name);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("mPersonList",name+"");
                Intent intent = getIntent();
                intent.putExtra("data",name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void itemListClicked(int position) {
//        Person person = mPersonList.get(position);
//        boolean isSelected = person.isSelected();
//        boolean success;
//        if (isSelected) {
//            success = mAutoLabel.removeLabel(position);
//        } else {
//            success = mAutoLabel.addLabel(person.getName(), position);
//        }
//        if (success) {
//            adapter.setItemSelected(position, !isSelected);
//        }
    }

    private void setListeners() {
//        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
//            @Override
//            public void onLabelsCompleted() {
//                Toast.makeText(getApplication(), "Completed!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
//            @Override
//            public void onRemoveLabel(View view, int position) {
//                adapter.setItemSelected(position, false);
//            }
//        });
//
//        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
//            @Override
//            public void onLabelsEmpty() {
//                Toast.makeText(getApplication(), "EMPTY!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
//            @Override
//            public void onClickLabel(View v) {
//                Toast.makeText(getApplication(), ((Label) v).getText() , Toast.LENGTH_SHORT).show();
//            }
//        });

    }




}
