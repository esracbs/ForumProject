package dao;

import entity.Message;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CASPER
 */
public class MessageDao extends DBConnection{
    public void create(Message m){
        try{
            Statement st=this.getConnection().createStatement();
            String query="insert into messages (id,sender,receiver,subject,details,messages_date,status) values("+m.getId()+",'"+m.getSender()+"','"+m.getReceiver()+"','"+m.getSubject()+"','"+m.getDetails()+"','"+new Timestamp(System.currentTimeMillis())+"','"+m.getStatus()+"')";
            st.executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void update(Message m){
        try{
            Statement st=this.getConnection().createStatement();
            String query="update messages set sender='"+m.getSender()+"',receiver='"+m.getReceiver()+"',subject='"+m.getSubject()+"', details='"+m.getDetails()+"',messages_date='"+new Timestamp(System.currentTimeMillis())+"',status='"+m.getStatus()+"'where id="+m.getId();
            st.executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(Message m){
        try{
            Statement st=this.getConnection().createStatement();
            String query="delete from messages where id="+m.getId();
            st.executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<Message> getList(){
        List<Message> list=new ArrayList<>();
        try{
            Statement st=this.getConnection().createStatement();
            String query="select * from messages";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                list.add(new Message(rs.getInt("id"),rs.getString("sender"),rs.getString("receiver"),
                        rs.getString("subject"),rs.getString("details"),rs.getTimestamp("messages_date"),rs.getBoolean("status")));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public Message findById(int id){
        Message m=null;
        try{
            Statement st=this.getConnection().createStatement();
            String query="select * from messages where id="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                m=new Message(rs.getInt("id"),rs.getString("sender"),rs.getString("receiver"),
                        rs.getString("subject"),rs.getString("details"),rs.getTimestamp("messages_date"),rs.getBoolean("status"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return m;
    }
}