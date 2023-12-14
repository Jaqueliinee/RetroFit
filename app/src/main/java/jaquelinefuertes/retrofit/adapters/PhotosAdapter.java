package jaquelinefuertes.retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jaquelinefuertes.retrofit.R;
import jaquelinefuertes.retrofit.modelos.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoVH> {
    private List<Photo> objects;
    private int resource;
    private Context context;

    public PhotosAdapter(List<Photo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoVH(LayoutInflater.from(context).inflate(resource,null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH holder, int position) {
        Photo p = objects.get(position);
        holder.lblTitulo.setText(p.getTitle());
        Picasso.get().load(p.getThumbnailUrl()).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PhotoVH extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView lblTitulo;

        public PhotoVH(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgImagenPhotoCard);
            lblTitulo = itemView.findViewById(R.id.lblTituloPhotoCard);
        }
    }
}
