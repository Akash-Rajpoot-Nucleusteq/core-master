package in.nucleusteq.plasma.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TokenUser {
    private String email;
    private String role;
}
