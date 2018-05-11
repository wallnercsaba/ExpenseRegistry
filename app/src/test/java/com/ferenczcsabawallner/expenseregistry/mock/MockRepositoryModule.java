package com.ferenczcsabawallner.expenseregistry.mock;

import com.ferenczcsabawallner.expenseregistry.repository.Repository;
import com.ferenczcsabawallner.expenseregistry.repository.SugarOrmRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 05. 08..
 */
@Module
public class MockRepositoryModule {

    @Singleton
    @Provides
    public Repository provideRepository() {
        return new MockRepository();
    }
}
