
/*
 * 1��ͬ��˳
 * 2��ը��
 * 3: ��«
 * 4��ͬ��
 * 5��˳��
 * 6������
 * 7������
 * 8������
 * 9������
 * 10������
 * */
package com.thirteenWater;
import java.util.ArrayList;
public class water 
{
    public static void main(String[] args) throws Exception
    {
    	
    	String st[]=new String[]{ "$2&2*2$3&4&6$9*9*10&Q$Q&A$A",
    			                   "#A#K#Q#J#10*5*6*2*9*A&7$7",//ͬ��˳��ͬ��
    								"*A#K#Q#J#10*5#5&5$5*K&7$7&7",//˳�ӣ�ը��
    			                    "*A#A#K*K#10*5#5&5$3*Q&7$2&9",//����������
    								"*A#A#Q*Q#10*5#5&5$3*3&7$2&2",//��«������
    			                    "*A#A#Q*Q#10*5#5&5$3*3&7$2&6"//��«������
    			
    			
    			
    	};
    	String s;
    	for(int i=0;i<6;i++)
    	{
    		s=st[i];
    		Cards c=new Cards(s);
        	c.show();
        	c.sort();
        	c.show();
        	c.set_after();
        	c.set_mid();
        	c.set_front();
        	ArrayList<Card> l=c.after;
        	ArrayList<Card> l2=c.mid;
        	ArrayList<Card> l3=c.front;
        	c.showpoker();//Thread.sleep(2111110000);
    	}
    }
}
class Card 
{
	int point=2;
	char color='#';//#����   &����     $����     *÷��
	public Card(){}
	public Card(int p,char c)
	{
		point=p;
		color=c;
	}
	public Card(Card e)
	{
		point=e.point;
		color=e.color;
	}
	public void set(Card e)
	{
		point=e.point;
		color=e.color;
	}
	public void show()
	{
		System.out.print("point "+point);
		System.out.println(" color "+color);
	}
	public boolean bigger(Card e)//���Ƿ���ڴ�������
	{
		if(e.point > this.point)
			return false;
		else
			return true;
	}
}
class Cards 
{
	ArrayList<Card> arr=new ArrayList<Card>();//ʮ������
	ArrayList<Card> front=new ArrayList<Card>();//ǰ��
	ArrayList<Card> mid=new ArrayList<Card>();//�ж�
	ArrayList<Card> after=new ArrayList<Card>();//���
	int a_level=0;//����Ƽ�
	int m_level=0;//�ж��Ƽ�
	int f_level=0;//ǰ���Ƽ�
	public Cards(){}
	public Cards(String s)
	{
		strTocards(s);
	}
	public void strTocards(String str)//��13���Ƶ��ַ���ת����ʮ�����ƶ���
	{
		String p="";
		int po;
		int j=0;
		for(int i=0;i<str.length();i++)
		{
			if(isColor(str.charAt(i)))
			{
				p="";
				for(j=i+1;(j<str.length()&&!isColor(str.charAt(j)));j++)
				{
					p=p+str.charAt(j);
				}
				if(isAtoJ(p.charAt(0)))
				{
					po=AJtoN(p.charAt(0));
				}
				else
				{
					po=Integer.parseInt(p);
				}
				Card c=new Card(po,str.charAt(i));
				arr.add(c);
			}
		}
	}
	public void show()
	{
		System.out.println("arr.size : "+arr.size());
		for(int i=0;i<arr.size();i++)
		{
			Card c=arr.get(i);
			c.show();
		}
	}
	public void showpoker()
	{
		System.out.println("��գ�");
		for(int i=0;i<after.size();i++)
		{
			after.get(i).show();
		}
		System.out.println("�жգ�");
		for(int i=0;i<mid.size();i++)
		{
			mid.get(i).show();
		}
		System.out.println("ǰ�գ�"+front.size());
		for(int i=0;i<front.size();i++)
		{
			front.get(i).show();
		}
	}
	private void swap(int i,int j)
	{
		Card c=new Card(arr.get(i));
		arr.get(i).set(arr.get(j));
		arr.get(j).set(c);
		c=null;
	}
	private void swap_p(int i,int j,ArrayList<Card> p)
	{
		Card c=new Card(p.get(i));
		p.get(i).set(p.get(j));
		p.get(j).set(c);
		c=null;
	}
	public void sort()
	{
		for(int i=0;i<arr.size();i++)
		{
			Card a =arr.get(i);
			for(int j=i+1;j<arr.size();j++)
			{
				Card b=arr.get(j);
				if(a.bigger(b))
				{
					swap(i,j);
				}
			}
		}
		//System.out.println("\nafter sort");
	}
	public void sort(ArrayList<Card> pok)
	{
		//System.out.println("leng "+pok.size());
		for(int i=0;i<pok.size();i++)
		{
			Card a =pok.get(i);
			for(int j=i+1;j<pok.size();j++)
			{
				Card b=pok.get(j);
				if(a.bigger(b))
				{
					swap_p(i,j,pok);
				}
			}
		}
		//System.out.println("\nafter sort");
	}
	public String tranChar(int pok)
	{
			if(pok==11)
			{
				return "J";
			}
			if(pok==12)
			{
				return "Q";
			}
			if(pok==13)
			{
				return "K";
			}
			if(pok==14)
			{
				return "A";
			}
			return "";
	}
	public static boolean isColor(char co)//�ǲ��ǻ�ɫ�ķ���
	{
		return (co=='#'||co=='$'||co=='&'||co=='*');
	}
	public static boolean isAtoJ(char p)//�ǲ���A��J
	{
		return (p=='A'||p=='K'||p=='Q'||p=='J');
	}
	public static int AJtoN(char c)//A-Jת����ֵ11-14
	{
		int num;
		if(c=='J') 
		{
			num=11;
		}
		else if(c=='Q')
		{
			num=12;
		}
		else if(c=='K')
		{
			num=13;
		}
		else
		{
			num=14;
		}
		return num;
	}
	public void setPork()//Ū��������
	{
		sort();
		set_after();
		set_mid();
		set_front();
		sort(after);
		sort(mid);
		sort(front);
	}
	public String frontS()
	{
		String s="";
		String c="";
		for(int i=0;i<3;i++)
		{
			
			s=s+front.get(i).color;
			if(front.get(i).point>=11)
			{
				c=tranChar(front.get(i).point);
				s=s+c;
			}
			else
			{
				s=s+front.get(i).point;
			}
			if(i<2)
			{
				s=s+" ";
			}
		}
		return s;
	}
	public String midS()
	{
		String s="";
		String c="";
		for(int i=0;i<5;i++)
		{
			s=s+mid.get(i).color;
			if(mid.get(i).point>=11)
			{
				c=tranChar(mid.get(i).point);
				s=s+c;
			}
			else
			{
				s=s+mid.get(i).point;
			}
			if(i<4)
			{
				s=s+" ";
			}
		}
		return s;
	}
	public String afterS()
	{
		String s="";
		String c="";
		for(int i=0;i<5;i++)
		{
			s=s+after.get(i).color;
			if(after.get(i).point>=11)
			{
				c=tranChar(after.get(i).point);
				s=s+c;
			}
			else
			{
				s=s+after.get(i).point;
			}
			if(i<4)
			{
				s=s+" ";
			}
		}
		return s;
	}
	public void set_after()//���ú�յ���
	{
		ArrayList<Card> l=new ArrayList<Card>();
		if(isTHS()!=null)
		{
			after=isTHS();
			a_level=1;
			delete(after,arr);
		}
		else if(isZD()!=null)
		{
			after=isZD();
			a_level=2;
			delete(after,arr);
		}
		else if(isST()!=null)
		{
			after=isST();
			copy(arr,l);
			delete(after,arr);
			if(is_minDZ()!=null)
			{
				a_level=3;
			}
			else
			{
				a_level=6;
			}
		}
		if(a_level==6)
		{
			while(!arr.isEmpty())
			{
				arr.remove(0);
			}
			copy(l,arr);
			if(isTH()!=null)
			{
				after=isTH();
				delete(after,arr);
				a_level=4;
			}
			else if(isSZ()!=null)
			{
				after=isSZ();
				delete(after,arr);
				a_level=5;
			}
			else
			{
				delete(isST(),arr);
			}
		}
		else if(a_level==0)
		{
			if(isTH()!=null)
			{
				after=isTH();
				delete(after,arr);
				a_level=4;
			}
			else if(isSZ()!=null)
			{
				after=isSZ();
				delete(after,arr);
				a_level=5;
			}
			else//ֻʣ������,����һ�����ӣ�������������,��Ҫ��������
			{
				while(is_maxDZ()!=null)
				{
					after.addAll(is_maxDZ());
					delete(is_maxDZ(),arr);
					if(after.size()==4)
					{
						a_level=8;
						break;
					}
				}
				if(after.size()==4)//��������������
				{
					if(after.get(0).point==(after.get(2).point-1))
					{
						a_level=7;
					}
					else//��������
					{
						ArrayList<Card> l1=new ArrayList<Card>();
						ArrayList<Card> tes=new ArrayList<Card>();//װ����
						arr.add(after.get(3));
						arr.add(after.get(2));
						arr.add(after.get(1));
						arr.add(after.get(0));
						copy(arr,l1);
						while(is_minDZ()!=null)
						{
							tes.addAll(is_minDZ());
							delete(is_minDZ(),arr);
						}
						copy(l1,arr);
						int p=0;
						while(!after.isEmpty())
						{
							after.remove(0);
						}
						for(int i=tes.size()-1;i>=3;i=i-2)
						{
							p=tes.get(i).point;
							if(p==tes.get(i-2).point+1)//������
							{
								after.add(tes.get(i));
								after.add(tes.get(i-1));
								after.add(tes.get(i-2));
								after.add(tes.get(i-3));
								delete(after,arr);
								a_level=7;
								break;
							}
						}
						if(after.size()==0)//������
						{
							after.addAll(is_maxDZ());
							delete(is_maxDZ(),arr);
							after.addAll(is_minDZ());
							delete(is_minDZ(),arr);
							if(after.size()==4)
							{
								a_level=8;
							}
						}
					}
				}
				else if(after.size()==2)//��ߴ����� set_front
				{
					a_level=9;
					after.add(arr.get(0));
					after.add(arr.get(1));
					after.add(arr.get(2));
					arr.remove(0);
					arr.remove(0);
					arr.remove(0);
				}
				else//���ֲ����ܴ��ڣ�������дһ�°�
				{
					a_level=10;
					for(int i=0;i<5;i++)
					{
						mid.add(arr.get(0));
						arr.remove(0);
					}
				}
			}//
		}
	}
	public void set_mid()//ѡ���ж���
	{
		ArrayList<Card> l=new ArrayList<Card>();
		if(isTHS()!=null)
		{
			mid=isTHS();
			m_level=1;
			delete(mid,arr);
		}
		else if(isZD()!=null)
		{
			mid=isZD();
			m_level=2;
			delete(mid,arr);
		}  
		else if(isST()!=null)//������ºܸ��ӣ�Ҫ���飬��������Ҳ����
		{
			if(a_level==3)//��֤����ж����ã���ע��������3*3+1*2��3*3+2*2��ʱ��Ҫ��������õĲ���
			{ 
				//�����ж��������Ͷ���
				copy(arr,l);
				int ST_sum=0;
				int DZ_sum=0;
				while(isST()!=null)
				{
					delete(isST(),arr);
					ST_sum++;
				}
				while(is_maxDZ()!=null)
				{
					delete(is_maxDZ(),arr);
					DZ_sum++;
				}
				copy(l,arr);
				if(ST_sum>=3)//���������4*3
				{
					mid=isST();
					delete(mid,arr);
					after.addAll(is_maxDZ());
					delete(is_maxDZ(),arr);
					mid.addAll(is_maxDZ());
					delete(is_maxDZ(),arr);
					m_level=3;
				}
				else if(ST_sum==2)//���������3*3
				{
					if(DZ_sum==2)//���������3*3+2*2,˳������ǰ��
					{
						mid=isST();
						delete(mid,arr);
						after.addAll(is_maxDZ());
						delete(is_maxDZ(),arr);
						mid.addAll(is_maxDZ());
						delete(is_maxDZ(),arr);
						m_level=3;
						f_level=6;
					}
					else if(DZ_sum==1)//���������3*3+1*2
					{
						mid=isST();
						delete(mid,arr);
						after.addAll(is_maxDZ());
						delete(is_maxDZ(),arr);
						mid.addAll(is_maxDZ());
						delete(is_maxDZ(),arr);
						m_level=3;
					}
					else if(DZ_sum==0)//���������3*3,�޶���,���������ӣ��򻯴����ˣ������Ǻ�ն��жտ�����ͬ����˳�ӵ�Ӱ����
					{
						//��ն����Ȳ��ã����洦��
						m_level=6;//��ʾ�������Ƽ���6
						mid=isST();
						copy(arr,l);
						delete(mid,arr);
					}
				}
				else if(ST_sum==1)//���������2*3
				{
					if(DZ_sum==0)
					{
						after.addAll(is_maxDZ());
						delete(is_maxDZ(),arr);
					}
					else if(DZ_sum==1)//���������ӣ��򻯴����ˣ������Ǻ�ն��жտ�����ͬ����˳�ӵ�Ӱ����
					{
						//��ն����Ȳ��ã����洦��
						m_level=6;//��ʾ�������Ƽ���6
						mid=isST();
						copy(arr,l);
						delete(mid,arr);
					}
					else if(DZ_sum>=2)
					{
						mid=isST();
						delete(mid,arr);
						after.addAll(is_minDZ());
						delete(is_minDZ(),arr);
						mid.addAll(is_minDZ());
						delete(is_minDZ(),arr);
						m_level=3;
					}
				}
			}
			else//��ʾ���û�к�«��Ҳ��������
			{
				mid=isST();
				copy(arr,l);
				delete(mid,arr);
				if(is_minDZ()!=null)
				{
					//mid.addAll(is_minDZ());
					m_level=3;
					//delete(is_minDZ(),arr);
				}
				else
				{
					m_level=6;
				}
			}
		}
		if(m_level==6)
		{
			while(!arr.isEmpty())
			{
				arr.remove(0);
			}
			copy(l,arr);
			//show();
			if(isTH()!=null)
			{
				mid=isTH();
				delete(mid,arr);
				if((a_level==3)&&(after.size()==3))//�ж��������Ǻ�«��û���ö��ӣ���û�в������
				{
					if(is_maxDZ()==null)//���˶���
					{
						//System.out.println("chai");
						copy(l,arr);
						//�����ж��ٶ��Ӻ�����
						int ST_sum=0;
						int DZ_sum=0;
						while(isST()!=null)
						{
							delete(isST(),arr);
							ST_sum++;
						}
						while(is_maxDZ()!=null)
						{
							delete(is_maxDZ(),arr);
							DZ_sum++;
						}
						//show();
						copy(l,arr);
						if(ST_sum==2)//����һ����3
						{
							delete(is_minDZ(),arr);
							if(isTH()!=null)
							{
								m_level=4;
							}
							else
							{
								copy(l,arr);
								delete(is_maxDZ(),arr);
								if(isTH()!=null)
								{
									m_level=4;
								}
							}
						}
						else//ST_sum==2,1*2
						{
							delete(is_minDZ(),arr);
							if(isTH()!=null)
							{
								m_level=4;
							}
							else
							{
								copy(l,arr);
								delete(is_maxDZ(),arr);
								if(isTH()!=null)
								{
									m_level=4;
								}
							}
						}
					}
					else//û�����
					{
						//System.out.println(" no  chai");
						after.addAll(is_minDZ());
						delete(is_minDZ(),arr);
						m_level=4;
						//show();
					}
				}
				else
				{
				    m_level=4;
				}
				
			}
			if(isSZ()!=null)//
			{
				mid=isSZ();
				delete(mid,arr);
				if((a_level==3)&&(after.size()==3))//�ж��������Ǻ�«��û���ö��ӣ���û�в������
				{
					if(is_maxDZ()==null)//���˶���
					{
						copy(l,arr);
						//�����ж��ٶ��Ӻ�����
						int ST_sum=0;
						int DZ_sum=0;
						while(isST()!=null)
						{
							delete(isST(),arr);
							ST_sum++;
						}
						while(is_maxDZ()!=null)
						{
							delete(is_maxDZ(),arr);
							DZ_sum++;
						}
						copy(l,arr);
						if(ST_sum==2)//����һ����3
						{
							delete(is_minDZ(),arr);
							if(isSZ()!=null)
							{
								m_level=5;
							}
							else
							{
								copy(l,arr);
								delete(is_maxDZ(),arr);
								if(isSZ()!=null)
								{
									m_level=5;
								}
							}
						}
						else//ST_sum==2,1*2
						{
							delete(is_minDZ(),arr);
							if(isSZ()!=null)
							{
								m_level=5;
							}
							else
							{
								copy(l,arr);
								delete(is_maxDZ(),arr);
								if(isSZ()!=null)
								{
									m_level=5;
								}
							}
						}
					}
					else//û�����
					{
						after.addAll(is_minDZ());
						delete(is_minDZ(),arr);
						m_level=5;
					}
			    }
			}
			else if(arr.size()!=3)
			{
				delete(isST(),arr);
				m_level=6;
			}
		}
		else if(m_level==0)
		{
			if(a_level==3&&after.size()==3)
			{
				after.addAll(is_minDZ());
				delete(is_minDZ(),arr);
			}
			if(isTH()!=null)
			{
				mid=isTH();
				delete(mid,arr);
				m_level=4;
			}
			else if(isSZ()!=null)
			{
				mid=isSZ();
				delete(mid,arr);
				m_level=5;
			}
			else//ֻʣ������,����һ�����ӣ�������������,��Ҫ��������
			{
				while(is_maxDZ()!=null)
				{
					mid.addAll(is_maxDZ());
					delete(is_maxDZ(),arr);
					if(mid.size()==4)
					{
						m_level=8;
						break;
					}
				}
				if(mid.size()==4)//��������������
				{
					if(mid.get(0).point==(mid.get(2).point-1))
					{
						if(a_level==3&&after.size()==3)
						{
							if(is_minDZ()==null)
							{
							    after.add(mid.get(3));
							    after.add(mid.get(2));
							    mid.remove(3);
							    mid.remove(2);
							    m_level=9;
							}
							else
							{
							    after.addAll(is_minDZ());
							}
						}
						m_level=7;
					}
					else//��������
					{
						ArrayList<Card> l1=new ArrayList<Card>();
						ArrayList<Card> tes=new ArrayList<Card>();//װ����
						arr.add(mid.get(3));
						arr.add(mid.get(2));
						arr.add(mid.get(1));
						arr.add(mid.get(0));
						copy(arr,l1);
						while(is_minDZ()!=null)
						{
							tes.addAll(is_minDZ());
							delete(is_minDZ(),arr);
						}
						copy(l1,arr);
						int p=0;
						while(!mid.isEmpty())
						{
							mid.remove(0);
						}
						if(a_level==3&&tes.size()==4&&after.size()==3)//���hulu��Ҫ�߶���
						{
							after.addAll(is_minDZ());
							delete(is_minDZ(),arr);
							m_level=9;
							mid.addAll(is_maxDZ());
							delete(is_maxDZ(),arr);
							for(int i=0;i<3;i++)
							{
								mid.add(arr.get(0));
								arr.remove(0);
							}
						}
						else
						{
							for(int i=tes.size()-1;i>=3;i=i-2)
						    {
							    p=tes.get(i).point;
							    if(p==tes.get(i-2).point+1)//������
							    {
								
								    mid.add(tes.get(i));
								    mid.add(tes.get(i-1));
								    mid.add(tes.get(i-2));
								    mid.add(tes.get(i-3));
								    delete(mid,arr);
								    m_level=7;
								    break;
							    }
						    }
							if(mid.size()==0)//������
						    {
							    mid.addAll(is_maxDZ());
							    delete(is_maxDZ(),arr);
							    mid.addAll(is_minDZ());
						    	delete(is_minDZ(),arr);
							    if(mid.size()==4)
							    {
								    m_level=8;
							    }
						    }
							if(a_level==3&&after.size()==3)//
							{
								after.addAll(is_minDZ());
								delete(is_minDZ(),arr);
							}
					     }
					}
						
				}
				else if(mid.size()==2)//��ߴ����� set_front
				{
					if(a_level==3&&after.size()==3)
					{
						after.addAll(is_minDZ());
						delete(is_minDZ(),arr);
						m_level=10;
						mid.add(arr.get(arr.size()-1));
						arr.remove(arr.get(arr.size()-1));
						for(int i=0;i<4;i++)
						{
							mid.add(arr.get(0));
							arr.remove(0);
						}
					}
					else
					{
						m_level=9;
					    mid.add(arr.get(0));
					    mid.add(arr.get(1));
					    mid.add(arr.get(2));
					    arr.remove(0);
					    arr.remove(0);
					    arr.remove(0);
					}
				}
				else
				{
					m_level=10;
					mid.add(arr.get(arr.size()-1));
					arr.remove(arr.get(arr.size()-1));
					for(int i=0;i<4;i++)
					{
						mid.add(arr.get(0));
						arr.remove(0);
					}
				}
			}//
		}
	}
	public void set_front()
	{
		if(a_level==3&&after.size()==3)
		{
			if(is_minDZ()!=null)
			{
				after.addAll(is_minDZ());
				delete(is_minDZ(), arr);
			}
			else//ǰ������
			{
				after.add(arr.get(0));
				arr.remove(0);
				after.add(arr.get(0));
				arr.remove(0);
				a_level=6;
			}
		}
		if(m_level==3&&mid.size()==3)
		{
			if(is_minDZ()!=null)
			{
				mid.addAll(is_minDZ());
				delete(is_minDZ(), arr);
			}
			else//ǰ������
			{
				mid.add(arr.get(0));
				arr.remove(0);
				mid.add(arr.get(0));
				arr.remove(0);
				m_level=6;
			}
		}
		if(a_level==6&&after.size()==3)
		{
			after.add(arr.get(0));
			arr.remove(0);
			after.add(arr.get(0));
			arr.remove(0);
		}
		if(m_level==6&&mid.size()==3)
		{
			mid.add(arr.get(0));
			arr.remove(0);
			mid.add(arr.get(0));
			arr.remove(0);
		}
		if((a_level==7||a_level==8)&&after.size()==4)
		{
			while(is_maxDZ()!=null)
			{
				front.addAll(is_maxDZ());
				delete(is_maxDZ(),arr);
			}
			after.add(arr.get(0));
			arr.remove(0);
		}
		if((m_level==7||m_level==8)&&mid.size()==4)
		{
			while(is_maxDZ()!=null)
			{
				front.addAll(is_maxDZ());
				delete(is_maxDZ(),arr);
			}
			mid.add(arr.get(0));
			arr.remove(0);
		}
		if(a_level==2&&after.size()==4)
		{
			while(is_maxDZ()!=null)
			{
				front.addAll(is_maxDZ());
				delete(is_maxDZ(),arr);
			}
			after.add(arr.get(0));
			arr.remove(0);
		}
		if(m_level==2&&mid.size()==4)
		{
			while(is_maxDZ()!=null)
			{
				front.addAll(is_maxDZ());
				delete(is_maxDZ(),arr);
			}
			mid.add(arr.get(0));
			arr.remove(0);
		}
		if(a_level==9&&after.size()==2)
		{
			
		}
		if(m_level==9&&mid.size()==2)
		{
			
		}
		while(!arr.isEmpty())
		{
			front.add(arr.get(0));
			arr.remove(0);
		}
		//check();
	}
	ArrayList<Card> isTHS()//ͬ��˳
	{
		ArrayList<Card> l=new ArrayList<Card>();
		char c;
		int p;
		for(int i=arr.size()-1;i>=4;i--)
		{
			c=arr.get(i).color;
			p=arr.get(i).point;
			while(!l.isEmpty())
			{
				l.remove(0);
			}
			l.add(arr.get(i));
			for(int j=i-1;j>=0;j--)
			{
				if(arr.get(j).color==c)
				{
					if(arr.get(j).point==(p-1))
					{
						l.add(arr.get(j));
						p=arr.get(j).point;
						if(l.size()>=5)
						{
							return l;
						}
					}
					else
					{ 
						if(l.size()>=5)
						{
						     return l;
						}
						else
						{
							while(!l.isEmpty())
							{
								l.remove(0);
							}
							 break;
						}
					}
				}
				else//��ͬ��ɫ
				{
					if(l.size()>=5)
					{
						return l;
					};
					if(arr.get(j).point<(p-1))
					{
						while(!l.isEmpty())
						{
							l.remove(0);
						}
						break;
					}
				}
			}
		}
		return null;
	}
	ArrayList<Card> isZD()//ը��
	{
		ArrayList<Card> l=new ArrayList<Card>();
		int p;
		l.add(arr.get(arr.size()-1));
		for(int i=arr.size()-2;i>=0;i--)
		{
			p=l.get(0).point;
			if(p==arr.get(i).point)
			{
				l.add(arr.get(i));
				if(l.size()==4)
				{
					return l;
				}
			}
			else
			{
				while(!l.isEmpty())
				{
					l.remove(0);
				}
				l.add(arr.get(i));
			}
		}
		return null;
	}
	ArrayList<Card> isSZ()//����˳��
	{
		ArrayList<Card> l=new ArrayList<Card>();
		l.add(arr.get(arr.size()-1));
		int i=0;
		for(i=arr.size()-2;i>=0;i--)
		{
			if(arr.get(i).point==(l.get(l.size()-1).point-1))
			{
				l.add(arr.get(i));
				if(l.size()>=5)
				{
					return l;
				}
			}
			else if(arr.get(i).point<(l.get(l.size()-1).point-1))
			{
				if(l.size()>=5)
				{
					return l;
				}
				while(!l.isEmpty())
				{
					l.remove(0);
				}
				l.add(arr.get(i));
			}
		}
		return null;
	}
	ArrayList<Card> isST()//����
	{
		ArrayList<Card> l=new ArrayList<Card>();
		int p;
		l.add(arr.get(arr.size()-1));
		for(int i=arr.size()-2;i>=0;i--)
		{
			p=l.get(0).point;
			if(p==arr.get(i).point)
			{
				l.add(arr.get(i));
				if(l.size()==3)
				{
					return l;
				}
			}
			else
			{
				while(!l.isEmpty())
				{
					l.remove(0);
				}
				l.add(arr.get(i));
			}
		}
		return null;
	}
	ArrayList<Card> isTH()//
	{
		ArrayList<Card> l=new ArrayList<Card>();
		ArrayList<Card> colo=new ArrayList<Card>();
		for(int i=arr.size()-1;i>=4;i--)
		{
			if(colo.size()==4)
			{
				break;
			}
			char c=arr.get(i).color;
			int j=0;
			l.add(arr.get(i));
			//System.out.println(c);
			for(j=0;j<colo.size();j++)//�鿴��ɫ�Ƿ��Ѿ����ҹ���
			{
				if(c==colo.get(j).color)
				{
					l.remove(0);
					break;
				}
			}
			if(j==colo.size())
			{
				colo.add(arr.get(i));
				for(j=i-1;j>=0;j--)
				{
					if(arr.get(j).color==c)
					{
						l.add(arr.get(j));
						if(l.size()>=5)
						{
							return l;
						}
					}
				}
				if(l.size()<5)
				{
					while(!l.isEmpty())
					{
						l.remove(0);
					}
				}
				else
				{
					return l;
				}
			}
		}
		colo=null;
		return null;
	}
	ArrayList<Card> is_maxDZ()
	{
		ArrayList<Card> l=new ArrayList<Card>();
		int p;
		l.add(arr.get(arr.size()-1));
		for(int i=arr.size()-2;i>=0;i--)
		{
			p=l.get(0).point;
			if(p==arr.get(i).point)
			{
				l.add(arr.get(i));
				if(l.size()==2)
				{
					return l;
				}
			}
			else
			{
				while(!l.isEmpty())
				{
					l.remove(0);
				}
				l.add(arr.get(i));
			}
		}
		return null;
	}
	ArrayList<Card> is_minDZ()
	{
		ArrayList<Card> l=new ArrayList<Card>();
		int p;
		l.add(arr.get(0));
		for(int i=1;i<arr.size();i++)
		{
			p=l.get(0).point;
			if(p==arr.get(i).point)
			{
				l.add(arr.get(i));
				if(l.size()==2)
				{
					return l;
				}
			}
			else
			{
				while(!l.isEmpty())
				{
					l.remove(0);
				}
				l.add(arr.get(i));
			}
		}
		return null;
	}
	public void delete(ArrayList<Card> l,ArrayList<Card> a)//���ߺ�����ɾ������
	{
		for(int i=0;i<l.size();i++)
		{
			for(int j=0;j<a.size();j++)
			{
				if((l.get(i).color==a.get(j).color)&&(l.get(i).point==a.get(j).point))
				{
					a.remove(j);
				}
			}
			//arr.remove(l.get(i));
		}
	}
	public void copy(ArrayList<Card> a,ArrayList<Card> l)
	{
		while(!l.isEmpty())
		{
			l.remove(0);
		}
		for(int i=0;i<a.size();i++)
		{
			l.add(a.get(i));
		}
	}
}