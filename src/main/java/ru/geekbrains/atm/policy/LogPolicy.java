package ru.geekbrains.atm.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.atm.service.interdafaces.LogServiceInterface;

import java.util.ArrayList;

@Service
public class LogPolicy {


    private LogServiceInterface logService;

    private int logSize = 10;

    @Autowired
    public LogPolicy(LogServiceInterface logService) {
        this.logService = logService;
    }


    public ArrayList<String> getLogs() {

        return logService.readLogs(logSize);
    }

    public int getLogSize() {
        return logSize;
    }

    public void setLogSize(int logSize) {
        this.logSize = logSize;
    }
}
