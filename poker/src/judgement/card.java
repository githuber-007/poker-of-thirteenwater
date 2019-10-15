package judgement;
import java.util.*;
public class card {
	int point;
	char color;
	public card(){}
	public card(String x,char color)
	{
		switch(x)
		{
		case "A":point=14;break;
		case "J":point=11;break;
		case "Q":point = 12;break;
		case "K":point =13;break;
		default:point=Integer.parseInt(x);break;
		}
		this.color=color;
	}
	public card(card e)
	{
		point=e.point;
		color=e.color;
	}
	public void set(card e)
	{
		point=e.point;
		color=e.color;
	}
	public void show()
	{
		System.out.print("point "+point);
		System.out.println(" color "+color);
	}
	public boolean bigger(card e)
	{
		if(e.point > this.point)
			return false;
		else
			return true;
	} 
}
