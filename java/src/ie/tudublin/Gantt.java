package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	public ArrayList<Task> tasks = new ArrayList<Task>();

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");
		for(TableRow row:t.rows())
		{
			Task tk = new Task(row);
			tasks.add(tk);

		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{
			println(t);
		}
	}

	public void displayTasks()
	{
		float leftMargin = width / 6;
		float margin = width / 20;
		int numDays = 30;
		float x, y;
		float rHeight = 50;
		float rStart, rEnd, rWidth;
		float color;

		stroke(255);
		fill(255);
		textAlign(CENTER);

		for(int i = 1; i <= numDays; i++)
		{
			x = map(i, 1, 30, leftMargin, width - margin);
			line(x, margin, x, height - margin);

			text(i, x, margin * 0.8f);
		}

		for(int i = 0; i < tasks.size(); i++)
		{
			fill(255);
			y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			text(tasks.get(i).getName(), margin, y);

			noStroke();
			color = map(i, 0, tasks.size(), 0, 255);
			fill(color, 255, 255);
			
			rStart = map(tasks.get(i).getStartDate(), 1, 30, leftMargin, width - margin);
			rEnd = map(tasks.get(i).getEndDate(), 1, 30, leftMargin, width - margin);
			rWidth = rEnd - rStart;

			rect(rStart, y - rHeight / 2, rWidth, rHeight);
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		
	}
	
	public void draw()
	{			
		colorMode(HSB);
		background(0);
		displayTasks();
	}
}
