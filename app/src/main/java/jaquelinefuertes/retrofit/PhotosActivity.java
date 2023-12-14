package jaquelinefuertes.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import jaquelinefuertes.retrofit.adapters.PhotosAdapter;
import jaquelinefuertes.retrofit.conexiones.ApiConexiones;
import jaquelinefuertes.retrofit.conexiones.RetrofitObject;
import jaquelinefuertes.retrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<Photo> photos;
    private PhotosAdapter adapter;
    private RecyclerView.LayoutManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recycler = findViewById(R.id.contenedorPhotos);
        photos = new ArrayList<>();
        adapter = new PhotosAdapter(photos, R.layout.photo_view_holder, this);
        lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(lm);
        
        if (getIntent().getExtras().getString("ALBUM_ID") != null){
            doGetPhotos(getIntent().getExtras().getString("ALBUM_ID"));
        }
    }

    private void doGetPhotos(String albumId) {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones api = retrofit.create(ApiConexiones.class);
        Call<ArrayList<Photo>> getPhotos = api.getPhotosAlbum(albumId);
        
        getPhotos.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK){
                    photos.addAll(response.body());
                    adapter.notifyItemRangeInserted(0, photos.size());
                }else {
                    Toast.makeText(PhotosActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                Toast.makeText(PhotosActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}