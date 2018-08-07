package rest;

import java.util.List;

import model.CoinMarketCapApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    // Path for getting price from coinmarketcap Api for different coins.
    @GET("ticker/{coinId}")
    Call<List<CoinMarketCapApi>> getCoinPrice(@Path("coinId") String coinId);

}
