package com.hoteleria_app.hoteleria_app.service.Email;

import com.hoteleria_app.hoteleria_app.dto.Reservation.ResponseCreateReservationForHtml;
import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomReservation;
import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomsDtoForEmail;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.Reservation.ReservationService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
                          ResponseCreateReservationForHtml htmlDto) throws Exception {
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
                        generateRoomRows(htmlDto.getRoomsDtoForEmails()));

        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

    private String generateRoomRows(List<RoomsDtoForEmail> details) {
        StringBuilder rows = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (RoomsDtoForEmail detail : details) {
            long quantityDaysReserved = ChronoUnit.DAYS.between(
                    detail.getInitial_reservation_date().toLocalDate(),
                    detail.getFinal_reservation_date().toLocalDate()
            );
            Float subTotal = detail.getPriceRoom() * quantityDaysReserved;
            rows.append("<tr>")
                    .append("<td>").append(detail.getId_room()).append("</td>")
                    .append("<td>").append(detail.getInitial_reservation_date().format(dateFormatter)).append("</td>")
                    .append("<td>").append(detail.getFinal_reservation_date().format(dateFormatter)).append("</td>")
                    .append("<td>").append(quantityDaysReserved).append("</td>")
                    .append("<td>").append(detail.getPriceRoom()).append("</td>")
                    .append("<td>").append(subTotal).append("</td>")
                    .append("</tr>");
        }
        return rows.toString();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void registerReservation(Long id_user, List<RoomReservation> rooms) {
        try {
            UserModel user = userService.findById(id_user);
            ResponseCreateReservationForHtml roomInformationForEmail =
                    reservationService.createReservation(id_user,
                    rooms);
            sendEmail(user, "Reservación creada exitosamente", "Reservación creada",
                    new ResponseCreateReservationForHtml(roomInformationForEmail.totalPrice,
                    roomInformationForEmail.getRoomsDtoForEmails()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
