package com.ashunevich.mvp_android_example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;



import com.ashunevich.mvp_android_example.databinding.ActivityMainBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements iView {

    private MainActivityPresenter presenter;
    ArrayList<TextWatcher> watchersList;


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        binding = ActivityMainBinding.inflate (getLayoutInflater ());
        setContentView(binding.getRoot());
        presenter = new MainActivityPresenter (this);
        watchersList = new ArrayList<> ();
        loadAllTextWatchers(watchersList);
        setCheckBoxListener();
        binding.floatingActionButton.setOnClickListener (view -> updateView());
    }


    private void setCheckBoxListener(){
        binding.checkBox.setOnCheckedChangeListener ((compoundButton, b) -> setTextWatcherStatus(b));
    }

    private void setTextWatcherStatus(boolean b){
        if(b){
            setTextListener ();
        }
        else {
            removeTextListeners ();
        }
    }

    private void updateView(){
        showProgressBar ();
        hideProgressBarWithDelay();
        presenter.updateName (binding.editTextTextPersonName.getText ().toString ());
        presenter.updateTelephone (binding.editTextTextPersonName2.getText ().toString ());
    }

    private void hideProgressBarWithDelay(){
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this::hideProgressBar, 500);
    }

    private void setTextListener(){
        binding.editTextTextPersonName.addTextChangedListener (watchersList.get (0));
        binding.editTextTextPersonName2.addTextChangedListener (watchersList.get (1));
    }

    private void removeTextListeners(){
        binding.editTextTextPersonName.removeTextChangedListener (watchersList.get (0));
        binding.editTextTextPersonName2.removeTextChangedListener (watchersList.get (1));
    }

    private void loadAllTextWatchers(ArrayList<TextWatcher> list){
        list.add (numberChangeListener);
        list.add (name);
    }

    TextWatcher numberChangeListener = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            presenter.updateTelephone (String.valueOf (charSequence));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher name = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            presenter.updateName (String.valueOf (charSequence));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void updateUserInfoTextView(String info) {
        binding.textView.setText (info);
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility (View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility (View.INVISIBLE);
    }
}