package ie.tudublin;

import processing.data.TableRow;

public class Task
{
    private String name;
    private int startDate;
    private int endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public Task(String name, int startDate, int endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
        
    }

    @Override
    public String toString() {
        // return "Task [endDate=" + endDate + ", name=" + name + ", startDate=" + startDate + "]";
        return "Task Name: " + name + " Start Date: " + startDate + " End Date: " + endDate;
    }
    

}