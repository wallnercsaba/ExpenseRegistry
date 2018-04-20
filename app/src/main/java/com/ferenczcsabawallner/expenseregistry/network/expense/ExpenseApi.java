package com.ferenczcsabawallner.expenseregistry.network.expense;


import com.ferenczcsabawallner.expenseregistry.model.Expense;

import retrofit2.Call;
import retrofit2.http.*;


import java.util.Date;

import java.util.List;

public interface ExpenseApi {
  
  /**
   * Add a new expense to the database
   * 
   * @param body Expense object that needs to be added to the database
   * @return Call<Expense>
   */
  
  @POST("expense")
  Call<Expense> addExpense(
    @Body Expense body
  );

  
  /**
   * Find expense by ID
   * Returns a single Expense
   * @param expenseId ID of pet to return
   * @return Call<Expense>
   */
  
  @GET("expense/id/{expenseId}")
  Call<Expense> getExpenseById(
    @Path("expenseId") Long expenseId
  );

  
  /**
   * Updates an expense in the database
   * 
   * @param expenseId ID of pet that needs to be updated
   * @param expense Updated expense
   * @return Call<Void>
   */
  
  @PUT("expense/id/{expenseId}")
  Call<Void> updateExpense(
    @Path("expenseId") Long expenseId, @Body Expense expense
  );

  
  /**
   * Deletes an expense
   * 
   * @param expenseId Expense id to delete
   * @return Call<Void>
   */
  
  @DELETE("expense/id/{expenseId}")
  Call<Void> deleteExpense(
    @Path("expenseId") Long expenseId
  );

  
  /**
   * Get expenses by date
   * 
   * @param timestamp Date
   * @return Call<List<Expense>>
   */
  
  @GET("expense/{timestamp}")
  Call<List<Expense>> getExpense(
    @Path("timestamp") Date timestamp
  );

  
}
