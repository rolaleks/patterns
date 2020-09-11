package ru.geekbrains.atm.repo;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap<entity> {


    private Map<Long, entity> map;

    public IdentityMap() {
        map = new HashMap<Long, entity>();
    }

    public entity findById(Long id) {

        return map.get(id);
    }

    public void putEntity(Long id, entity entity) {

        map.put(id, entity);
    }
}
