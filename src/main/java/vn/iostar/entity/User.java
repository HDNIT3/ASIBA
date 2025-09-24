package vn.iostar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
    private String email;
    private String fullname;
    private int phone;
    private String passwd;
    private Date signupDate;
    private Date lastLogin;
    private boolean isAdmin;
}