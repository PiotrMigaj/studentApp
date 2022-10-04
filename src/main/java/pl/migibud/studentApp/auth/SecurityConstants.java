package pl.migibud.studentApp.auth;

interface SecurityConstants {
    String TOKEN_SIGNING_KEY = "nEoStRaDa";
    String ROLE_CLAIM = "roles";
    String HEADER_EXPIRATION = "expires_at";
    String HEADER_AUTH = "Authorization";
    String HEADER_AUTH_BEARER_PREFIX = "Bearer ";
    String HEADER_EXPOSED_HEADERS = "Access-Control-Expose-Headers";
    int TOKEN_VALID_DURATION_IN_DAYS = 10;
}
