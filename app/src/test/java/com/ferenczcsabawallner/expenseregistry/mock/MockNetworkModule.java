package com.ferenczcsabawallner.expenseregistry.mock;

import com.ferenczcsabawallner.expenseregistry.network.NetworkConfig;
import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Csabi on 2018. 04. 20..
 */
@Module
public class MockNetworkModule {


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
        return new MockExpenseApi();
    }

}
