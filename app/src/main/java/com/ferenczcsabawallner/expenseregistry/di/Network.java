package com.ferenczcsabawallner.expenseregistry.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Csabi on 2018. 05. 11..
 */

@Qualifier
@Retention(RUNTIME) // not needed
public @interface Network {
}
