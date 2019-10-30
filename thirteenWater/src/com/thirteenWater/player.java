package com.thirteenWater;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.thirteenWater.Cards;
public class player 
{
	String username="d";
	String password="da";
	String token;
	public void getrank() 
	{
        try 
        {
            URL url = new URL("http://api.revth.com/rank");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
           // connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) 
            {
                result.append(line + "\n");
            }
            connection.disconnect();
            System.out.println(result.toString());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	public void valid(String token) 
	{
        try 
        {
            URL url = new URL("http://api.revth.com/auth/validate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestProperty("X-Auth-Token",token);
            connection.setRequestMethod("GET"); // 设置请求方式
           // connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) 
            {
                result.append(line + "\n");
            }
            connection.disconnect();
            //System.out.println(result.toString());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	public String history(int num,String token) 
	{
		String re="";
        try 
        {
            String u=" http://www.revth.com:12300/history/";
            u=u+num;
            //u=u+"}";
            System.out.println(u);
            URL url = new URL(u);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestProperty("X-Auth-Token",token);
            connection.setRequestMethod("GET"); // 设置请求方式
           // connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) 
            {
                result.append(line + "\n");
            }
            connection.disconnect();
            re=result.toString();
            System.out.println(result.toString());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return re;
    }
    public  String postlogin(String data) 
    {
    	String info="";
        try 
        {
            URL url = new URL("http://api.revth.com/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestMethod("POST");
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
            pw.write(data);
            pw.flush();
            pw.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) 
            {
                  lines = new String(lines.getBytes(), "utf-8");
                  sb.append(lines);
            }
            System.out.println(sb.toString()); 
            info=sb.toString();
            conn.disconnect();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return info;
       
    }
    public  String startGame(String token) 
    {
    	String pok="";
        try 
        {
            URL url = new URL("http://api.revth.com/game/open");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("X-Auth-Token",token);
            conn.setRequestMethod("POST");//GET和POST必须全大写
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) 
            {
                  lines = new String(lines.getBytes(), "utf-8");
                  sb.append(lines);
            }
            pok=sb.toString();
            conn.disconnect();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return pok;
    }
    public  String sendp(String s,String token) 
    {
    	String pok="";
        try 
        {
            URL url = new URL("http://api.revth.com/game/submit");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("X-Auth-Token",token);
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            conn.setRequestMethod("POST");//GET和POST必须全大写
            
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
            pw.write(s);
            pw.flush();
            pw.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) 
            {
                  lines = new String(lines.getBytes(), "utf-8");
                  sb.append(lines);
            }
            pok=sb.toString();
            System.out.println("summit "+pok);
            conn.disconnect();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return pok;
    }
    public  String zhuxiao(String token) 
    {
    	String s="";
        try 
        {
            URL url = new URL("http://api.revth.com/auth/logout");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("X-Auth-Token",token);
            conn.setRequestMethod("POST");//GET和POST必须全大写
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) 
            {
                  lines = new String(lines.getBytes(), "utf-8");
                  sb.append(lines);
            }
            s=sb.toString();
            conn.disconnect();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return s;
    }
    public  JSONObject tran(String str,String get)
    {

    	JSONObject resultPrase = (JSONObject) JSONValue.parse(str);
    	JSONObject s=(JSONObject) resultPrase.get(get);
    	return s;
    }
    public  static void main(String[] args) throws Exception
    {
    	player p=new player();
    	p.go();
    }
    public void go() throws Exception
    {
         Map<String, String> m1 = new LinkedHashMap<String, String>();
    	 m1.put("username",username);
		 m1.put("password",password);
		 String s = JSONValue.toJSONString(m1);
    	 String name=postlogin(s);
    	 System.out.println("login");
    	 JSONObject data1=tran(name,"data");
    	 token=data1.get("token").toString();//拿到token

    	  for(int i=0;i<200;i++)
    	  {
    		 
    		 
    		 System.out.println("第几局："+i);
    		 valid(token);
    		 
    		 String pok=startGame(token);
        	 System.out.println("start");
        	 
        	
        	 JSONObject data2=tran(pok,"data");
        	 String poker=data2.get("card").toString();
        	 
        	 
        	 poker=poker.replace(" ","");
        	 
        	 Cards c=new Cards(poker);
       	     c.sort();
      	      c.setPork();
      	      String [] p=new String[3];
      	      p[0]=c.frontS();
      	      p[1]=c.midS();
      	      p[2]=c.afterS();
        	  String Id=data2.get("id").toString();
        	  int id=Integer.parseInt(Id);
        	  Map m = new LinkedHashMap();
        	  //m.put("id",id);
        	  m.put("id",Id);
        	  JSONArray li = new JSONArray();
        	  li.add(p[0]);
        	  li.add(p[1]);
        	  li.add(p[2]);
        	  m.put("card", li);
        	  String ss=JSONValue.toJSONString(m).toString();
        	  System.out.println(ss);
        	  sendp(ss,token); 
        	
             /* 查询历史对局详情
              * String filename="D:\\E\\javapra\\2.txt";
              BufferedReader in = new BufferedReader(new FileReader(filename));
              String str=null;
              while ((str = in.readLine())!= null) 
              {
                   String st=str;
                   int n=Integer.parseInt(st);
                   String his=history(n,token);
              } 
              in.close();*/
        	  Thread.sleep(5000);
        		 
          }
        	  
        	 /* 把对局ID记录下来
        	  * String filename="D:\\E\\javapra\\2.txt";
        	  * File file=new File(filename);
		        FileOutputStream out=new FileOutputStream(file,true);
		        StringBuffer sb=new StringBuffer();
		        sb.append(id);
		        sb.append("\n");
		        out.write(sb.toString().getBytes("utf-8"));
		        out.close();*/
    	
    	        zhuxiao(token);
      }
}