package ru.geekbrains.atm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.atm.service.interdafaces.LogServiceInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class FileLogService implements LogServiceInterface {

    @Value( "${file-log}" )
    private String path;

    @Override
    public ArrayList<String>  readLogs(int lines) {

        ArrayList<String> logs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = br.readLine()) != null) {
                logs.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
