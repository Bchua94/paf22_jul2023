package sg.edu.nus.iss.paf22_jul2023.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSVP {
    private int id;

    private String fullName;

    private String email;

    private Integer phone;

    private Date confirmationDate;

    private String comment;
}
