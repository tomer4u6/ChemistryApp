package com.example.chemistryapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TableActivity extends AppCompatActivity {

    int[] elementGenericId = {

            R.string.Gen_H,
            R.string.Gen_He,
            R.string.Gen_Li,
            R.string.Gen_Be,
            R.string.Gen_B,
            R.string.Gen_C,
            R.string.Gen_N,
            R.string.Gen_O,
            R.string.Gen_F,
            R.string.Gen_Ne,
            R.string.Gen_Na,
            R.string.Gen_Mg,
            R.string.Gen_Al,
            R.string.Gen_Si,
            R.string.Gen_P,
            R.string.Gen_S,
            R.string.Gen_Cl,
            R.string.Gen_Ar,
            R.string.Gen_K,
            R.string.Gen_Ca
    };

    int[] elementTextId = {
            R.string.Text_H,
            R.string.Text_He,
            R.string.Text_Li,
            R.string.Text_Be,
            R.string.Text_B,
            R.string.Text_C,
            R.string.Text_N,
            R.string.Text_O,
            R.string.Text_F,
            R.string.Text_Ne,
            R.string.Text_Na,
            R.string.Text_Mg,
            R.string.Text_Al,
            R.string.Text_Si,
            R.string.Text_P,
            R.string.Text_S,
            R.string.Text_Cl,
            R.string.Text_Ar,
            R.string.Text_K,
            R.string.Text_Ca
    };

    int[] configArray = {
            R.drawable.hconfig,
            R.drawable.heconfig,
            R.drawable.liconfig,
            R.drawable.beconfig,
            R.drawable.bconfig,
            R.drawable.cconfig,
            R.drawable.nconfig,
            R.drawable.oconfig,
            R.drawable.fconfig,
            R.drawable.neconfig,
            R.drawable.naconfig,
            R.drawable.mgconfig,
            R.drawable.alconfig,
            R.drawable.siconfig,
            R.drawable.pconfig,
            R.drawable.sconfig,
            R.drawable.clconfig,
            R.drawable.arconfig,
            R.drawable.kconfig,
            R.drawable.caconfig

    };

    AlertDialog.Builder adb;
    AlertDialog ad;
    LinearLayout element_dialog;
    TextView elementGeneric,elementText;
    ImageView electronConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openElement(View view) {

        char tag = view.getTag().toString().charAt(0);
        int i = tag - 'A';

        element_dialog = (LinearLayout)getLayoutInflater().inflate(R.layout.element_dialog,null);
        elementGeneric = (TextView)element_dialog.findViewById(R.id.elementGeneric);
        elementText = (TextView)element_dialog.findViewById(R.id.elementText);
        electronConfig = (ImageView)element_dialog.findViewById(R.id.electronConfig);

        elementGeneric.setText(getString(elementGenericId[i]));
        elementText.setText(getString(elementTextId[i]));
        electronConfig.setImageResource(configArray[i]);

        elementText.setMovementMethod(new ScrollingMovementMethod());
        elementGeneric.setMovementMethod(new ScrollingMovementMethod());

        adb = new AlertDialog.Builder(this);
        adb.setView(element_dialog);
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
