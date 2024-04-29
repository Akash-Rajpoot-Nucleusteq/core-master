package in.nucleusteq.plasma.dto.in.leave;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object (DTO) representing a holiday to be added.
 */
public class HolidayInDTO {
    /**
     * Holiday date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * Holiday Name.
     */
    private String holidayName;
    /**
     * Retrieves the date of the holiday.
     * @return The date of the holiday.
     */
    public Date getDate() {
        return date;
    }
    /**
     * Sets the date of the holiday.
     * @param date The date of the holiday to be set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Retrieves the name of the holiday.
     * @return The name of the holiday.
     */
    public String getHolidayName() {
        return holidayName;
    }
    /**
     * Sets the name of the holiday.
     * @param holidayName The name of the holiday to be set.
     */
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
}
