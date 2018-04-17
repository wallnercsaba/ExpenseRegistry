package com.ferenczcsabawallner.expenseregistry.network;

import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Csabi on 2018. 04. 16..
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ExpenseApi provideArtistsApi(Retrofit retrofit) {
        return retrofit.create(ExpenseApi.class);
    }
}
