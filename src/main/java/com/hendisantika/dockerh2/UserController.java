package com.hendisantika.dockerh2;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/16/17
 * Time: 7:36 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * /create --> Create a new user and save it in the database.
     *
     * @param email
     *            User's email
     * @param name
     *            User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name) {
        logger.info("received create-request with email " + email + " and name " + name);
        User user = null;
        try {
            user = new User(email, name);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete --> Delete the user having the passed id.
     *
     * @param id
     *            The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        logger.info("received delete-request with id " + id);
        try {
            User user = new User(id);
            userRepository.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /show --> show all users in the database. just for demonstration. Of
     * course this could be made nicer!
     *
     * @return A string that contains all the users in the database.
     */
    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        logger.info("received show-request");
        Iterable<User> users = null;
        try {
            users = userRepository.findAll();
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }

        String usersStringified = "<table border='1'><tr><td>id</td><td>name</td><td>email</td></tr>";
        for (User user : users) {
            usersStringified += "<tr><td>" + user.getId() + "</td><td>" + user.getName() + "</td><td>" + user.getEmail()
                    + "</td></tr>";
        }
        usersStringified += "</table>";
        return usersStringified;
    }
}
