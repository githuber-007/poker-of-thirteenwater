package judgement;

import java.util.ArrayList;

public class queue_of_cards {

	private ArrayList<card> arr=new ArrayList<card>(13);
	public queue_of_cards(){}
	public  queue_of_cards(card e)
	{
		arr.add(e);
		System.out.println("arr.size= "+arr.size());
	}
	
	public queue_of_cards(String str)
	{
		String x;char s=str.charAt(0);
		int start=1;
		for(int i=1;i<str.length();i++)
		{
			char c=str.charAt(i);
			switch(c)
			{
			case 'B':
			{
				
				x=str.substring(start,i);
				arr.add(new card(x,s));
				s='B';
				start=i+1;
				break;
			}
				
			case 'C':
			{
				x=str.substring(start,i);
				arr.add(new card(x,s));
				s='C';
				start=i+1;
				break;
			}
			case 'D':
			{
				x=str.substring(start,i);
				arr.add(new card(x,s));
				s='D';
				start=i+1;
				break;
			}
			case 'F':
			{
				x=str.substring(start,i);
				arr.add(new card(x,s));
				s='F';
				start=i+1;
				break;
			}
			default:break;
			}
		}
		x=str.substring(start);
		arr.add(new card(x,s));
	}
	public void show()
	{
		System.out.println("arr.size : "+arr.size());
		for(int i=0;i<arr.size();i++)
		{
			card c=arr.get(i);
			c.show();
		}
	}
	
	private void swap(ArrayList<card> a,int i,int j)
	{
		card e=new card(a.get(i));
		System.out.print("before swap :");
		System.out.print("arr["+i+"]: ");
		a.get(i).show();
		System.out.print(" arr["+j+"]: \n");
		a.get(j).show();
		a.get(i).set(a.get(j));
		a.get(j).set(e);
		System.out.print("swap ");
		a.get(i).show();
		System.out.print(" and ");
		a.get(j).show();
	}
	public void sort()
	{
		for(int i=0;i<arr.size();i++)
		{
			card a =arr.get(i);
			for(int j=i+1;j<arr.size();j++)
			{
				card b=arr.get(j);
				if(a.bigger(b))
					swap(arr,i,j);
			}
		}
		System.out.println("\nafter sort");
	}
	
}
