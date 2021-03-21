package fr.epsi.ateliermspr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AtelierMsprActivity {

    public static void displayActivity(AtelierMsprActivity activity, Product product){
        Intent intent = new Intent(activity, ProductDetailsActivity.class);
        //infos product
        intent.putExtra("name", product.getShotName());
        intent.putExtra("picture_url", product.getPictureUrl());
        intent.putExtra("description", product.getDescription());

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        String name = this.getIntent().getExtras().getString("name","");
        String picture_url = this.getIntent().getExtras().getString("picture_url","");
        String description = this.getIntent().getExtras().getString("description", "");


        setTitle(name);
        showBack();

        ImageView imageView = findViewById(R.id.imageViewProductDetail);
        Picasso.get().load(picture_url).into(imageView);

        TextView descriptionV = findViewById(R.id.textViewPDDescription);
        descriptionV.setText(description);


    }
}