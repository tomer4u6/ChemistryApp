package com.example.chemistryapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class mattersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView matterLV;
    String[] matters = {
            "מים - H2O",
            "מלח בישול - NaCl",
            "אספירין - C9H8O4",
            "גלוקוז - C6H12O6",
            "פחמן דו חמצני - CO2"
    };
    int[] mattersId = {
            R.string.water,
            R.string.salt,
            R.string.aspirin,
            R.string.glucose,
            R.string.carbonDioxide
    };

    int[] imageArray1 = {
            R.drawable.waterimg,
            R.drawable.saltimg,
            R.drawable.aspirinimg,
            R.drawable.glucoseimg,
            R.drawable.carbondioxideimg
    };
    int[] imageArray2 = {
            R.drawable.watermol,
            R.drawable.saltmol,
            R.drawable.aspirinmol,
            R.drawable.glucosemol,
            R.drawable.carbondioxidemol
    };

    AlertDialog.Builder adb;
    AlertDialog ad;
    LinearLayout matters_dialog;
    TextView matterText;
    ImageView matterImage1,matterImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        matterLV = (ListView)findViewById(R.id.matterLV);
        matterLV.setOnItemClickListener(this);
        matterLV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, matters);
        matterLV.setAdapter(adp);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        matters_dialog = (LinearLayout)getLayoutInflater().inflate(R.layout.matters_dialog,null);
        matterText = (TextView)matters_dialog.findViewById(R.id.matterText);
        matterImage1 = (ImageView)matters_dialog.findViewById(R.id.matterImage1);
        matterImage2 = (ImageView)matters_dialog.findViewById(R.id.matterImage2);

        matterText.setText(getString(mattersId[i]));
        matterImage1.setImageResource(imageArray1[i]);
        matterImage2.setImageResource(imageArray2[i]);

        adb = new AlertDialog.Builder(this);
        adb.setView(matters_dialog);
        adb.setTitle(matters[i]);
        adb.setCancelable(false);
        adb.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ad.cancel();
            }
        });
        ad = adb.create();
        ad.show();
    }
}
