package fr.epsi.ateliermspr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<Product> products;
    private AtelierMsprActivity activity;

    public ProductAdapter(AtelierMsprActivity activity, ArrayList<Product> products){
        this.products = products;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.getTextViewProduct().setText(product.getName());


        holder.getTextViewDescription().setText(product.getShotDescription());

        Picasso.get().load(product.getPictureUrl()).into(holder.getImageViewProduct());

        holder.getLayoutCell().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsActivity.displayActivity(activity, product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewProduct;
        private final TextView textViewDescription;
        private final ImageView imageViewProduct;
        private final View layoutCell;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textViewProduct = view.findViewById(R.id.textViewNameProduct);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            imageViewProduct = view.findViewById(R.id.imageViewProduct);
            layoutCell = view.findViewById(R.id.layoutCellProduct);
        }

        public View getLayoutCell() {
            return layoutCell;
        }
        public TextView getTextViewProduct() {
            return textViewProduct;
        }
        public TextView getTextViewDescription(){
            return  textViewDescription;
        }
        public ImageView getImageViewProduct() {
            return imageViewProduct;
        }
    }
}

