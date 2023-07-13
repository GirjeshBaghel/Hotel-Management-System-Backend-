package com.entity;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private final String senderEmail;
    private final String senderPassword;
    private final Properties emailProperties;

    public EmailService(String senderEmail, String senderPassword, String smtpHost, String smtpPort) {
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;

        emailProperties = new Properties();
        emailProperties.put("mail.smtp.host", smtpHost);
        emailProperties.put("mail.smtp.port", smtpPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void sendBookingConfirmationEmail(String recipientEmail, Booking booking) {
        // Create a session with the sender's credentials
        Session session = Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new message
            MimeMessage message = new MimeMessage(session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set email subject
            message.setSubject("Room Booking Confirmation");

            // Construct the email content
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append("Dear ").append(booking.getCustomer().getCusName()).append(",\n\n");
            contentBuilder.append("Your room booking has been accepted. Here are the booking details:\n\n");
            // Include relevant booking information (check-in, check-out dates, room details, etc.)
            contentBuilder.append("Booking ID: ").append(booking.getBooking_id()).append("\n");
            
            // Add more booking details as needed

            // Set email content
            message.setText(contentBuilder.toString());

            // Send the email
            Transport.send(message);
            System.out.println("Booking confirmation email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send booking confirmation email: " + e.getMessage());
        }
    }
}
