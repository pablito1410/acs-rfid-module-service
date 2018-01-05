package com.pablo.acs.rfid.service.domain.rfid;

import com.pablo.acs.rfid.service.domain.command.Command;

public class EnrollCommand implements Command {

    private Integer userId;

    private EnrollCommand() { }

    public Integer getUserId() {
        return userId;
    }
}
