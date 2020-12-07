package tech.yojigen.pivisionR.network.dto;

public class OAuthDTO {
    private String access_token;

    @Override
    public String toString() {
        return "OauthDTO{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}
