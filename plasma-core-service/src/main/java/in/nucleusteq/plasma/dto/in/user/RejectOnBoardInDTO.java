package in.nucleusteq.plasma.dto.in.user;

import in.nucleusteq.plasma.enums.OnBoardingStatus;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Reject OnBorad In DTO.
 */
@Getter
@Setter
public class RejectOnBoardInDTO {
    /**
     * OnBoard Details Id.
     */
    private int id;
    /**
     * OnBoarding Status.
     */
    private OnBoardingStatus onBoardingStatus;
}
