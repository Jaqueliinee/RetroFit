package jaquelinefuertes.retrofit.conexiones;

import java.util.ArrayList;

import jaquelinefuertes.retrofit.modelos.Album;
import jaquelinefuertes.retrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConexiones {
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId")String albumId);
}
