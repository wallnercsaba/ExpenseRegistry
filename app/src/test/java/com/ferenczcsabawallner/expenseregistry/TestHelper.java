package com.ferenczcsabawallner.expenseregistry;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by Csabi on 2018. 04. 23..
 */

public class TestHelper {
    public static void setTestInjector() {
        ShadowLog.stream = System.out;
//        ExpenseRegistryApplication application = (ExpenseRegistryApplication) RuntimeEnvironment.application;
//        ExpenseRegistryApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
//        application.injector = injector;
    }


}
