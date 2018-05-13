package hu.psprog.leaflet.cli.service.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Initialization response domain.
 *
 * @author Peter Smith
 */
public class InitResponse {

    private Long userID;
    private String generatedPassword;

    public Long getUserID() {
        return userID;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InitResponse that = (InitResponse) o;

        return new EqualsBuilder()
                .append(userID, that.userID)
                .append(generatedPassword, that.generatedPassword)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userID)
                .append(generatedPassword)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userID", userID)
                .append("generatedPassword", generatedPassword)
                .toString();
    }

    public static InitResponseBuilder getBuilder() {
        return new InitResponseBuilder();
    }

    /**
     * Builder for {@link InitResponse}.
     */
    public static final class InitResponseBuilder {
        private Long userID;
        private String generatedPassword;

        private InitResponseBuilder() {
        }

        public InitResponseBuilder withUserID(Long userID) {
            this.userID = userID;
            return this;
        }

        public InitResponseBuilder withGeneratedPassword(String generatedPassword) {
            this.generatedPassword = generatedPassword;
            return this;
        }

        public InitResponse build() {
            InitResponse initResponse = new InitResponse();
            initResponse.userID = this.userID;
            initResponse.generatedPassword = this.generatedPassword;
            return initResponse;
        }
    }
}
