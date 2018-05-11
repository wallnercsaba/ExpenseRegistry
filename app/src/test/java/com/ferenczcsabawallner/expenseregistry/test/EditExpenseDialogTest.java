package com.ferenczcsabawallner.expenseregistry.test;

import com.ferenczcsabawallner.expenseregistry.BuildConfig;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialogPresenter;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialogScreen;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainPresenter;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainScreen;
import com.ferenczcsabawallner.expenseregistry.util.DateHelper;
import com.ferenczcsabawallner.expenseregistry.utils.RobolectricDaggerTestRunner;

import junit.framework.Assert;

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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created by Csabi on 2018. 05. 10..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class EditExpenseDialogTest {
    private MainPresenter mainPresenter;
    private MainScreen mainScreen;
    private ExpenseEditDialogPresenter expenseEditDialogPresenter;
    private ExpenseEditDialogScreen expenseEditDialogScreen;

    @Before
    public void setup() throws Exception{
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);

        expenseEditDialogScreen = mock(ExpenseEditDialogScreen.class);
        expenseEditDialogPresenter = new ExpenseEditDialogPresenter();
        expenseEditDialogPresenter.attachScreen(expenseEditDialogScreen);
    }

    @Test
    public void addNewExpense(){
        mainPresenter.SyncWithServer();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 20);

        mainPresenter.SelectDay(DateHelper.JustDate(cal.getTime()));

        ArgumentCaptor<List> expenseCapture = ArgumentCaptor.forClass(List.class);

        verify(mainScreen).ShowExpenses(expenseCapture.capture());
        Assert.assertTrue(expenseCapture.getValue()==null);

        reset(mainScreen);

        expenseEditDialogPresenter.SaveExpense("Tesco", (long) 5000, DateHelper.JustDate(cal.getTime()), null);

        verify(mainScreen).ShowExpenses(expenseCapture.capture());

        Assert.assertTrue(expenseCapture.getValue().size()==1&& ((ExpenseRecord)expenseCapture.getValue().get(0)).place.equals("Tesco"));
    }

    @Test
    public void modifyExpense(){
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
        reset(mainScreen);
        ExpenseRecord e = (ExpenseRecord) expenseCaptor.getValue().get(0);

        expenseEditDialogPresenter.SaveExpense("Tesco", (long) 60000, new Date(e.date), e.getId());

        verify(mainScreen).ShowExpenses(expenseCaptor.capture());

        assertTrue(expenseCaptor.getValue().size()>0 && ((ExpenseRecord)expenseCaptor.getValue().get(0)).place.equals("Tesco"));
    }

    @Test
    public void deleteExpense(){
        mainPresenter.SyncWithServer();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2018);
        mainPresenter.SelectDay(DateHelper.JustDate(cal.getTime()));
        ArgumentCaptor<List> expenseCaptor = ArgumentCaptor.forClass(
                List.class);
        verify(mainScreen).ShowExpenses(expenseCaptor.capture());

        int size = expenseCaptor.getValue().size();
        ExpenseRecord e = (ExpenseRecord) expenseCaptor.getValue().get(0);
        assertTrue(expenseCaptor.getValue().size()>0);
        reset(mainScreen);

        expenseEditDialogPresenter.DeleteExpense(e.getId());
        verify(mainScreen).ShowExpenses(expenseCaptor.capture());
        assertTrue(expenseCaptor.getValue().size()==size-1);
    }

    @After
    public void tearDown(){
        mainPresenter.detachScreen();
        expenseEditDialogPresenter.detachScreen();
    }
}
