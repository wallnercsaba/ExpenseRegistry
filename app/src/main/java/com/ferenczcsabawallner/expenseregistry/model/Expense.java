package com.ferenczcsabawallner.expenseregistry.model;

import java.util.Objects;
import java.util.Date;

import com.google.gson.annotations.SerializedName;




public class Expense   {
  
  @SerializedName("id")
  private Long id = null;
  
  @SerializedName("place")
  private String place = null;
  
  @SerializedName("amount")
  private Long amount = null;
  
  @SerializedName("date")
  private Date date = null;
  
  @SerializedName("timestamp")
  private Date timestamp = null;
  
  @SerializedName("deleted")
  private Boolean deleted = null;
  

  
  /**
   **/
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   **/
  public String getPlace() {
    return place;
  }
  public void setPlace(String place) {
    this.place = place;
  }

  
  /**
   **/
  public Long getAmount() {
    return amount;
  }
  public void setAmount(Long amount) {
    this.amount = amount;
  }

  
  /**
   **/
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

  
  /**
   **/
  public Date getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  
  /**
   **/
  public Boolean getDeleted() {
    return deleted;
  }
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Expense expense = (Expense) o;
    return Objects.equals(id, expense.id) &&
        Objects.equals(place, expense.place) &&
        Objects.equals(amount, expense.amount) &&
        Objects.equals(date, expense.date) &&
        Objects.equals(timestamp, expense.timestamp) &&
        Objects.equals(deleted, expense.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, place, amount, date, timestamp, deleted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Expense {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    deleted: ").append(toIndentedString(deleted)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
