package me.aandster.seng2050a2.persistence;


import me.aandster.seng2050a2.model.GtwGame;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GamePersistenceService extends ConcurrentHashMap<String, GtwGame> implements Serializable {

    private final String filePath;

    public GamePersistenceService(String filePath) throws IOException {
        if (filePath == null) throw new NullPointerException("No path provided.");
        this.filePath = filePath;

        updateFromFile();
    }

    public synchronized void writeToFile() throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(this);
        }
        updateFromFile();
    }

    public synchronized void updateFromFile() throws IOException {

        /* Read from file */
        Map<String, GtwGame> deserializedMap = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            deserializedMap = getClass().cast(inputStream.readObject());
            if (deserializedMap == null) throw new IOException("Could not deserialize");
        } catch (EOFException | InvalidClassException e) {
            writeToFile();
        } catch (ClassCastException | ClassNotFoundException e) {
            throw new IOException(e);
        }

        /* Update list */
        this.clear();
        if (deserializedMap != null) {
            this.putAll(deserializedMap);
        }
    }

}
