package judgement;

import java.util.HashMap;
import java.util.*;
import java.io.*;
public class main {
public static void main(String[] args)
	{/*HashMap<Integer, String> hm = new HashMap<Integer, String>();
	String s="a";
	int a=0;
	hm.put(a,s);
	int d=2;
	hm.put(d,s);
	for(int i=0;i<hm.size();i++)
	{
		System.out.println(hm.values());
	}
	ArrayList<card> arr=new ArrayList<card>(100);
	arr.add(new card(7,"a"));*/
	Scanner s=new Scanner(System.in);//B,C,D
	String str=s.nextLine();
	s.close();
    queue_of_cards q=new queue_of_cards(str);
    q.show();
    System.out.println("-----------------------------------");
    q.sort();
    q.show();
	}
}
