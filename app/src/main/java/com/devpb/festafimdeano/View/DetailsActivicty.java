package com.devpb.festafimdeano.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.devpb.festafimdeano.Data.SecurityPreferences;
import com.devpb.festafimdeano.R;

import constant.FimDeAnoConstants;

public class DetailsActivicty extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activicty);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkPresence = findViewById(R.id.check_confirm);
        this.mViewHolder.checkPresence.setOnClickListener(this);
        this.loadDataFromActivity();

    }

    @Override
    public void onClick(View v) {
        //clique
        if (v.getId() == R.id.check_confirm) ;{


            //salvar a prensença
            if (this.mViewHolder.checkPresence.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);


            }  else{
            this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            //salvar a ausência

            }
        }
    }
    private void loadDataFromActivity () {

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.checkPresence.setChecked(true);
            }else {
                this.mViewHolder.checkPresence.setChecked(false);
            }
        }


    }

    private static class ViewHolder {
        CheckBox checkPresence;
    }


}

