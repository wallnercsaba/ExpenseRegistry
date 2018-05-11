package com.ferenczcsabawallner.expenseregistry.test;

import com.ferenczcsabawallner.expenseregistry.BuildConfig;
import com.ferenczcsabawallner.expenseregistry.mock.MockExpenseApi;
import com.ferenczcsabawallner.expenseregistry.mock.MockRepository;
import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainPresenter;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainScreen;
import com.ferenczcsabawallner.expenseregistry.util.DateHelper;
import com.ferenczcsabawallner.expenseregistry.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ferenczcsabawallner.expenseregistry.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Csabi on 2018. 04. 23..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainTest {
    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    @Before
    public void setup() throws Exception{
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);
    }

    @Test
    public void testDaySelect(){
        mainPresenter.SyncWithServer();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2018);
        mainPresenter.SelectDay(DateHelper.JustDate(cal.getTime()));
        ArgumentCaptor<List> expenseCaptor = ArgumentCaptor.forClass(
                List.class);
        verify(mainScreen).ShowExpenses(expenseCaptor.capture());

        assertTrue(expenseCaptor.getValue().size()>0);
    }

    @Test
    public void ShowEditDialogWithExpenseRecord(){
        mainPresenter.SyncWithServer();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2018);
        mainPresenter.SelectDay(DateHelper.JustDate(cal.getTime()));
        ArgumentCaptor<List> expenseCaptor = ArgumentCaptor.forClass(
                List.class);
        verify(mainScreen).ShowExpenses(expenseCaptor.capture());
        List<ExpenseRecord> e = expenseCaptor.getValue();
        mainPresenter.ShowDialog(e.get(0));

        ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<ExpenseRecord> expenseRecordCaptor = ArgumentCaptor.forClass(ExpenseRecord.class);

        verify(mainScreen).ShowDialog(dateCaptor.capture(), expenseRecordCaptor.capture());

        assertTrue(dateCaptor.getValue().equals(DateHelper.JustDate(cal.getTime())) && expenseRecordCaptor.getValue().place==e.get(0).place);
    }

    @Test
    public void ShowEditDialogEmpty(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2018);
        mainPresenter.SelectDay(DateHelper.JustDate(cal.getTime()));

        mainPresenter.ShowDialog(null);
        ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<ExpenseRecord> expenseRecordCaptor = ArgumentCaptor.forClass(ExpenseRecord.class);

        verify(mainScreen).ShowDialog(dateCaptor.capture(), expenseRecordCaptor.capture());
        assertTrue(dateCaptor.getValue().equals(DateHelper.JustDate(cal.getTime())) && expenseRecordCaptor.getValue()==null);

    }

    @After
    public void tearDown(){ mainPresenter.detachScreen(); }
}
