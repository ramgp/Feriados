package com.github.feriados.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ramon
 * Date: 9/25/13
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Holiday
{
  private String date;
  private String reason;
  private String description = "";
  private String changedTo;

  public Holiday(String day, String reason)
  {
    this.date = day;
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }

  public String getDate() {
    return date;
  }

  @Override
  public String toString() {
    return reason;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
