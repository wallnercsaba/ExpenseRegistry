package com.ferenczcsabawallner.expenseregistry;

import com.ferenczcsabawallner.expenseregistry.interactor.InteractorModule;
import com.ferenczcsabawallner.expenseregistry.mock.MockNetworkModule;
import com.ferenczcsabawallner.expenseregistry.mock.MockRepositoryModule;
import com.ferenczcsabawallner.expenseregistry.repository.RepositoryModule;
import com.ferenczcsabawallner.expenseregistry.ui.UIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 20..
 */

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, MockRepositoryModule.class})
public interface TestComponent extends ExpenseRegistryApplicationComponent {
}
