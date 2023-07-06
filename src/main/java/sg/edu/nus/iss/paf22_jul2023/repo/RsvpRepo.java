package sg.edu.nus.iss.paf22_jul2023.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf22_jul2023.model.RSVP;

@Repository
public class RsvpRepo {
    @Autowired
    JdbcTemplate template;

    private final String countSQL = "select count(*) from rsvp";

    private final String findAllSQL = "select * from rsvp";

    private final String findByIdSQL = "select * from rsvp where id = ?";

    private final String insertSQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";

    private final String updateSQL = "update rsvp set email = ?, phone = ?, confirmation_date = ? where id = ?";

    public int count() {
        int iResult = template.queryForObject(countSQL, Integer.class);
        return iResult;
    }

    public List<RSVP> findAll() {
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = template.query(findAllSQL, BeanPropertyRowMapper.newInstance(RSVP.class));
        return rsvps;
    }

    public RSVP findById(int id) {
        RSVP rsvp = template.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);

        return rsvp;
    }

    public Boolean save(RSVP rsvp) {
        int iResult = 0;
        iResult = template.update(insertSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComment());
        return iResult > 0 ? true : false;
    }

    // "update rsvp set email = ?, phone = ?, confirmation_date = ? where id = ?"
    public Boolean update(RSVP rsvp) {
        int iResult = 0;
        iResult = template.update(updateSQL, rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getId());
        return iResult > 0 ? true : false;
    }

    // write a function for batchInsert
    // refer to slide 17 of day22


}
