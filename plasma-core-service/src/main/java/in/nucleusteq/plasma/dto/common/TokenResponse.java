package in.nucleusteq.plasma.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TokenResponse {
    /**
     * The access token.
     */
    private String accessCode;

    /**
     * The access token expiry.
     */
    private Long accessCodeExpiry;

    /**
     * The email id.
     */
    private String email;

    /**
     * The refresh token.
     */
    private String refreshToken;
}
