package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	public ArrayList<Task> tasks = new ArrayList<Task>();
	private float leftMargin;
	private float margin;

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
		float rHeight = 50;
		float tx1, tx2, ty1, ty2;
		boolean leftSelected = false;
		boolean rightSelected = false;
		println("Mouse pressed");	
		//for loop to check if the mouse was clicked next to any task
			// if mouseY is between y1 and y2 and 
				//if MouseX is +- 20 px from tx1
					//selected = 1
				//if Mouse X is +- 20 px from tx2
					//selected = 2
				//else
					//selected = 0
			// else selected = 0

		for(int i = 0; i < tasks.size(); i++)
		{
			Task task = tasks.get(i);
			tx1 = map(task.getStartDate(), 1, 30, leftMargin, width - margin);
			tx2 = map(task.getEndDate(), 1, 30, leftMargin, width - margin);
			ty1 = map(i, 0, tasks.size(), 2 * margin, height - margin);
			ty2 = map(i, 0, tasks.size(), 2 * margin, height - margin) + rHeight;

			if(mouseY >= ty1 && mouseY <= ty2)
			{
				if(mouseX > tx1 + 20 || mouseX < tx1 + 20)
				{
					println("right");
					rightSelected = true;
					leftSelected = false;
				}
				else if(mouseX > tx2 + 20 || mouseX < tx2 + 20)
				{
					println("left");
					leftSelected = true;
					rightSelected = false;
				}
			}
		}
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		leftMargin = width / 6;
		margin = width / 20;
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
