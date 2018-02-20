package net.gfu.seminar.spring.batch.guestimport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        List<Guest> results = ctx.getBean(JdbcTemplate.class).query("SELECT first_name, last_name FROM guests", new RowMapper<Guest>() {
            @Override
            public Guest mapRow(ResultSet rs, int row) throws SQLException {
                return new Guest(rs.getString(1), rs.getString(2));
            }
        });

        for (Guest guest : results) {
            System.out.println("Found <" + guest + "> in the database.");
        }
    }
}