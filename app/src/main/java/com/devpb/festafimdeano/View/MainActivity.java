package com.devpb.festafimdeano.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devpb.festafimdeano.Data.SecurityPreferences;
import com.devpb.festafimdeano.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import constant.FimDeAnoConstants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SecurityPreferences mSecurityPreferences;
    private ViewHolder mViewHolder = new ViewHolder();
    private static DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDayLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        // para pegar data atual
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDayLeft.setText(daysLeft);



    }


    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivicty.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence );
            startActivity(intent);
        }
    }

    private void verifyPresence() {
        //buscar se está confirmado ou não
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.não_confirmado));
        } else if (presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        } else {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.não));
        }


    }

    private int getDaysLeft() {
        //data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //dia máximo do ano
        Calendar calendarMax = Calendar.getInstance();
        int max = calendarMax.getActualMaximum(Calendar.DAY_OF_YEAR);
        return max - today;

    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDayLeft;
        Button buttonConfirm;


    }

}

