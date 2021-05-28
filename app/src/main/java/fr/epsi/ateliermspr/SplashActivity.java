package fr.epsi.ateliermspr;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashActivity extends AtelierMsprActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.displayActivity(SplashActivity.this);
                finish();
            }
        },2000);
    }
    /// scanner code
    public static class ScannerActivity extends AtelierMsprActivity {
        private String wsUrl = "https://www.ectatest.fr/api/gostyle/v_0/singleProduct/";

        private TextView textView;
        private ImageView imageView;

        static public void displayActivity(AtelierMsprActivity activity){
            Intent intent = new Intent(activity, ScannerActivity.class);
            activity.startActivity(intent);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scanner);

            setTitle("Scanner de QRCode");
            showBack();

            textView = findViewById(R.id.textViewSinglePDescription);
            imageView = findViewById(R.id.imageViewSingleProduct);

           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

            Button btnZone1 = findViewById(R.id.buttonToScan);
            btnZone1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScanButton(v);
                }
            });

        }

        public void ScanButton(View view){
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.initiateScan();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(intentResult != null){
                if(intentResult.getContents() == null){
                    setTitle("Scanner de QRCode");
                    imageView.setImageResource(0);
                    textView.setText("Une erreur s'est produite");
                }else{
                    // on affiche le résultat en cas de succès
                    if (CheckingStringNumeric.isNumenic(intentResult.getContents())){
                        int idProduct = Integer.valueOf(intentResult.getContents(), 10); // on récupère la réponse du qrcode (valeur réelle)
                        this.getProduct(idProduct);
                    }else{
                        setTitle("Scanner de QRCode");
                        imageView.setImageResource(0);
                        textView.setText("QRCode introuvable !");
                    }
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        public void getProduct(int idProduct){

            WSCall wsCall = new WSCall(this.wsUrl+idProduct, new WSCall.Callback() {
                @Override
                public void onComplete(String result) {
                    try {
                        Product product = null;
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonItems = jsonObject.getJSONArray("items");

                        if (!jsonItems.get(0).equals(null)){
                            product = new Product(jsonItems.getJSONObject(0));
                            // affichage du résultat
                            setTitle(product.getName());
                            Picasso.get().load(product.getPictureUrl()).into(imageView);
                            textView.setText(product.getDescription());
                        }else {
                            setTitle("Scanner de QRCode");
                            imageView.setImageResource(0);
                            textView.setText("QRCode introuvable !");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(ScannerActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
            wsCall.run();
        }
    }
}