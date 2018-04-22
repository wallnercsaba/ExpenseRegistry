package com.ferenczcsabawallner.expenseregistry;

import com.ferenczcsabawallner.expenseregistry.mock.MockNetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 20..
 */

@Singleton
@Component(modules = {MockNetworkModule.class, })
public class TestComponent {
}
