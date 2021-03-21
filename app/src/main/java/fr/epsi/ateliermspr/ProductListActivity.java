package fr.epsi.ateliermspr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListActivity extends AtelierMsprActivity {
    private String wsUrl = "https://kombay.go.yj.fr/api/gostyle/v_0/products/products.json";
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    public static void displayActivity(AtelierMsprActivity activity){
        Intent intent = new Intent(activity, ProductListActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        setTitle("Promotion");
        showBack();
        recyclerView = findViewById(R.id.recyclerViewProduct);

        products = new ArrayList<>();
        productAdapter = new ProductAdapter(this, products);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        WSCall wsCall = new WSCall(this.wsUrl, new WSCall.Callback() {
            @Override
            public void onComplete(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonItems = jsonObject.getJSONArray("items");
                    for (int i = 0; i < jsonItems.length(); i++){
                        Product product = null;
                        product = new Product(jsonItems.getJSONObject(i));
                        products.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(ProductListActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        wsCall.run();
    }
}