package in.nucleusteq.plasma.constants;

/**
 * This class is used for Token constant.
 * @author krishna
 *
 */
public final class TokenConstant {

    /**
     * Default constructor.
     */
    private TokenConstant() {
        super();
    }

    /**
     *  INTERNAL SERVER ERROR.
     */
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";

    /**
     * TOKEN EXPIRED.
     */
    public static final String TOKEN_EXPIRED = "Token expired";
    
    /**
     * TOKEN EXPIRED WITH UNAUTHORIZED.
     */
    public static final String TOKEN_EXPIRED_WITH_UNAUTHORIZED = "Invalid token / token has expired";

    /**
     * UNAUTHORIZED ACCESS.
     */
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";

    /**
     * IDENTITY UNAUTHORIZED EXCEPTION.
     */
    public static final String IDENTITY_UNAUTHORIZED_EXCEPTION = "Unauthorized User";
}
