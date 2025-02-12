    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.Blog;
import entity.Blog_Rating;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gökhan
 */
public class Blog_RatingDao extends DBConnection{
    
    private BlogDao blogDao;

    public BlogDao getBlogDao() {
        if(this.blogDao == null){
            blogDao = new BlogDao();
        }
        return blogDao;
    }

    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
    
    
    public Blog_Rating findById(int id){
        Blog_Rating c = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from blog_ratings where id="+id;
            
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                c = new Blog_Rating(rs.getInt("id"),this.getBlogDao().findById(rs.getInt("blog_id")),rs.getInt("total_score"));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
        
    public void create(Blog_Rating c){
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into blog_ratings(id,blog_id,total_score) values "
                    + "("+c.getId()+","+c.getBlog().getId()+","+c.getTotal_score()+")";
            st.executeUpdate(query);
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void delete(Blog_Rating c){
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from blog_ratings where id="+c.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void update(Blog_Rating c){
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update blog_ratings set blog_id="+c.getBlog().getId()+", total_score="+c.getTotal_score()+" where id="+c.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Blog_Rating> getList(){
        List<Blog_Rating> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from blog_ratings";
            
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Blog b = this.getBlogDao().findById(rs.getInt("blog_id"));
                list.add( new Blog_Rating(rs.getInt("id"),b,rs.getInt("total_score")));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
