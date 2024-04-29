package in.nucleusteq.plasma.dto.out.user;

import java.util.Date;

import lombok.Data;

/**
 * Data transfer object (DTO) for representing a Recent OnBoarding List Out DTO.
 */
@Data
public class RecentOnBoardingListOutDTO {
    /**
     * Employee ID.
     */
    private String employeeId;
    /**
     * Employee's First Name.
     */
    private String firstName;
    /**
     * Employee's Last Name.
     */
    private String lastName;
    /**
     * Employee's Start Date.
     */
    private Date startDate;
    /**
     * Employee's Phone Number.
     */
    private String phoneNumber;
    /**
     * Employee's City.
     */
    private String city;
    /**
     * Employee's State.
     */
    private String state;
}
