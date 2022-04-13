package com.bridgelabz.bookstoreproject.service.user;

import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.entity.LoginUser;
import com.bridgelabz.bookstoreproject.entity.User;
import com.bridgelabz.bookstoreproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class UserService implements IUserService{

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {

        User userById = getUserById(id);
        System.out.println(userDTO.getPassword());
        modelMapper.map(userDTO, userById);
        System.out.println("user password after update completed=====>"+ userById.getPassword());
        userRepository.save(userById);
        return userById;
    }

    @Override
    public User deleteUser(Long id) {
        User userById = getUserById(id);
        userRepository.delete(userById);
        return userById;
    }

    //generated OTP
    static long otp =(long) Math.round(Math.random() * 1000000);

    @Override
    public void sendOTPToEmail(LoginUser loginUser) {


    }

    //send email
    public static void sendEmailToUser( String subject, String messageText){

        String from = "nikita.amar@bridgelabz.com";
        String to = "nikita.amar@bridgelabz.com";


        //Setting up the mail server
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");//needs to be compatible as per ssl security
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "nikita@123");
            }
        });

        try{

            //creating a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            //setting up recipient
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //setting up the subject of the mail
            message.setSubject(subject);
            //setting up the text of the message
            message.setText(messageText);

            System.out.println("Sending the message "+messageText);
            Transport.send(message);
            System.out.println("Message sent successfully");


        }catch (MessagingException e){
            e.printStackTrace();
        }

    }

}
