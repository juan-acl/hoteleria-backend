package com.hoteleria_app.hoteleria_app.service.Email;

import com.hoteleria_app.hoteleria_app.dto.Email.EmailHtmlDto;
import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomReservation;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.Reservation.ReservationService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.text.DecimalFormat;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final ReservationService reservationService;

    private final String CURRENT_PATH = "src/main/resources/templates/index" +
            ".html";

    public EmailService(JavaMailSender mailSender, UserService userService,
                        ReservationService reservationService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    public String loadTemplate() throws Exception {
        return new String(Files.readAllBytes(Paths.get(CURRENT_PATH)));
    }

    public void sendEmail(UserModel user, String subject, String body,
                          EmailHtmlDto htmlDto) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
                "UTF-8");
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedPrice = df.format(htmlDto.getTotalPrice());


        String htmlTemplate = loadTemplate();

        String htmlContent = htmlTemplate
                .replace("{{username}}",
                        user.getName() + " " + user.getLastname())
                .replace("{{totalPrice}}", formattedPrice)
                .replace("{{roomRows}}",
                        generateRoomRows(htmlDto.getDescriptions()));

        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

    private String generateRoomRows(List<RoomReservation> details) {
        StringBuilder rows = new StringBuilder();
        for (RoomReservation detail : details) {
            rows.append("<tr>")
                    .append("<td>").append(detail.getId_room()).append("</td>")
                    .append("<td>").append(detail.getInitial_reservation_date()).append("</td>")
                    .append("<td>").append(detail.getFinal_reservation_date()).append("</td>")
                    .append("</tr>");
        }
        return rows.toString();
    }

    @Transactional
    public void createReservation(Long id_user, List<RoomReservation> rooms) {
        try {
            UserModel user = userService.findById(id_user);
            Float totalPrice = reservationService.createReservation(id_user,
                    rooms);
            sendEmail(user, "Reservation created", "Your reservation has been" +
                    " created successfully", new EmailHtmlDto(totalPrice,
                    rooms));
        } catch (Exception e) {
            throw new RuntimeException("Error on creating reservation: " + e.getMessage());
        }
    }
}
