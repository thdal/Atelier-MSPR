package fr.epsi.ateliermspr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AtelierMsprActivity implements View.OnClickListener{
    static public void displayActivity(AtelierMsprActivity activity){
        Intent intent = new Intent(activity,HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.buttonScan).setOnClickListener(this);
        findViewById(R.id.buttonPromo).setOnClickListener(this);
        //title
        this.setTitle("GoStyle");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonScan:
                SplashActivity.ScannerActivity.displayActivity(this);
                break;
            case R.id.buttonPromo:
                ProductListActivity.displayActivity(this);
                break;
        }
    }
}
