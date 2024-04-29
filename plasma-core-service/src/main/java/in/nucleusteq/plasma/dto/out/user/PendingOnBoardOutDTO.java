package in.nucleusteq.plasma.dto.out.user;

import lombok.Data;

/**
 * Data transfer object (DTO) for representing a Pending OnBoard Out DTO.
 */
@Data
public class PendingOnBoardOutDTO {
    /**
     * ID.
     */
    private int id;
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
    private String startDate;
    /**
     * Employee's Phone Number.
     */
    private String phoneNumber;
    /**
     * Employee's State.
     */
    private String state;
    /**
     * Employee's City.
     */
    private String city;
}
