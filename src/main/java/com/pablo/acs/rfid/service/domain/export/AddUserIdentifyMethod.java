package com.pablo.acs.rfid.service.domain.export;

import java.util.ArrayList;
import java.util.Collection;

public class AddUserIdentifyMethod {

    private Collection<UserIdentificationMethod> identifiers = new ArrayList<>();

    public AddUserIdentifyMethod(final long userId, final String uuid) {
        identifiers.add(new UserIdentificationMethod(userId, uuid));
    }

    public class UserIdentificationMethod {
        private long userId;
        private int identificationMethodId;
        private String uuid;

        private UserIdentificationMethod() { }

        public UserIdentificationMethod(final long userId, final String uuid) {
            this.userId = userId;
            identificationMethodId = 2;
            this.uuid = uuid;
        }

        public long getUserId() {
            return userId;
        }

        public int getIdentificationMethodId() {
            return identificationMethodId;
        }

        public String getUuid() {
            return uuid;
        }
    }
}
