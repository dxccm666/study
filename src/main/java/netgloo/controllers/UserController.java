package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
public class UserController {

	private String userEmail;
	private String userCity;
	private String userId;
	private String output;
	private String user1Id;
	private String user1Email;
	private String user1City;
	private String userName;
	private String user1Name;

	// ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	

	/**
	   * /create  --> Create a new user and save it in the database.
	   * 
	   * @param email User's email
	   * @param name User's name
	   * @param city User's city
	   * @return A string describing if the user is succesfully created or not.
	   */
	  @RequestMapping("/create")
	  @ResponseBody
	  public String create(String email, String name,String city) {
	    User user = null;
	    try {
	      user = new User(email, name, city);
	      userDao.save(user);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created! (id = " + user.getId() + ")";
	  }
  
  
  
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
 
 
  
  
  
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    String output="\t";
    try {
      
      List<User> list =userDao.findByEmail(email);
      for(int i=0;i<list.size();i++){
    	  User user = list.get(i);
    	  userId = String.valueOf(user.getId());
          System.out.println("User Id is:\t"+userId);
          output+="UserId is:\t  "  +userId;
          output+="<br>";
        		  }
    }
    catch (Exception ex) {
    	ex.printStackTrace();
      return "User not found";
    }
    return output ;
    
  }
 
  @RequestMapping("/get-by-name")
  @ResponseBody
  public String getByName(String name) {
    String userId;
    String output="\t";
    try {
      
      List<User> list =userDao.findByName(name);
      for(int i=0;i<list.size();i++){
    	  User user = list.get(i);
    	  userId = String.valueOf(user.getId());
    	  userEmail=String.valueOf(user.getEmail());
    	  userCity=String.valueOf(user.getCity());
    	  userName=String.valueOf(user.getName());
          System.out.println("User Id is:\t"+userId);
          System.out.println("User Email is:\t"+userEmail);
          System.out.println("User City is:\t"+userCity);
          output+="UserId is:\t\n  "+userId;
          output+=" UserEmail is:\t\n"+userEmail;
          output+="UserName is:\t\n"+userName;
          output+=" UserCity is:\t\n  "+userCity+"<br>";
        		  }
    }
    catch (Exception ex) {
    	ex.printStackTrace();
    	output+="user not found"+"<br>";
     
    }
    return output ;
    
  }
  
  @RequestMapping("/get-by-city")
  @ResponseBody
  public String getByCity(String city) {
    String userId;
    String output="\t";
    try {
      
      List<User> list =userDao.findByCity(city);
      for(int i=0;i<list.size();i++){
    	  User user = list.get(i);
    	  userId = String.valueOf(user.getId());
          System.out.println("User Id is:"+userId);
          System.out.println(" User Email is"+userEmail);
          System.out.println(" User Name is:\t"+userName);
          output+=" UserId is:\t\n  "+userId;
          output+=" UserEmail is:\t\n "+userEmail;
          output+=" UserCity is:\t\n "+userCity;
          output+="<br>";
        		  }
    }
    catch (Exception ex) {
    	ex.printStackTrace();
      return "User not found";
    }
    return output ;
    
  }  
  
  
  

  
  /**
   * /update  --> Update the email , the name and the city for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update-by-email")
  
  @ResponseBody
 
  public String updateByEmailUser(long id, String email) {
    try {
      User user = userDao.findOne(id);
      user.setEmail(email);
      userDao.save(user);
      
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
 
@RequestMapping("/update-by-name")
  
  @ResponseBody
  public String updateByNameUser(long id, String name) {
	String output="\t";
	try {
	      User user = userDao.findOne(id);
	      user.setName(name);
	      userDao.save(user);
	     
	     
	      List<User> list =userDao.findByName(name);
	      for(int i=0;i<list.size();i++){
	    	  User user1 = list.get(i);
	    	  user1Id = String.valueOf(user1.getId());
	    	  user1Email=String.valueOf(user1.getEmail());
	    	  user1City=String.valueOf(user1.getCity());
	    	  user1Name = String.valueOf(user1.getName());
	          System.out.println("User Id is:\t"+user1Id);
	          System.out.println("User Email is:\t"+user1Email);
	          System.out.println("User City is:\t"+user1City);
	          System.out.println("User Name is:\t"+user1Name);
	          output+="UserId is:\t\n  "+user1Id ;
	          output+=" UserName is:\t\n "+user1Name ;
	          output+=" UserEmail is:\t\n"+user1Email;
	          output+=" UserCity is:\t\n  "+user1City+"<br>";
	         
	        		  }
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	   
	    return output +"User succesfully updated!";
	
}

@RequestMapping("/update-by-city")

@ResponseBody
public String updateByCityUser(long id, String city) {
	try {
	      User user = userDao.findOne(id);
	      user.setCity(city);
	      userDao.save(user);
	      
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	    return "User succesfully updated!";
	
}

@RequestMapping("/update")
@ResponseBody
public String updateUser(long id, String email, String name,String city) {
  try {
    User user = userDao.findOne(id);
    user.setEmail(email);
    user.setName(name);
    user.setCity(city);
    userDao.save(user);
  }
  catch (Exception ex) {
    return "Error updating the user: " + ex.toString();
  }
  return "User succesfully updated!";
}

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserDao userDao;



  
} 
