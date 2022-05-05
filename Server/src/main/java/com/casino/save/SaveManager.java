package com.casino.save;

import java.io.File;

public class SaveManager {

    public final static File SAVE_FOLDER = new File("saves");
    public final static File SAVE_PLAYER_FOLDER = new File(SAVE_FOLDER.getPath() + "/players");

    public SaveManager() {
        if (!SAVE_PLAYER_FOLDER.exists()) {
            SAVE_PLAYER_FOLDER.mkdirs();
        }
    }

    public File getPlayerSave(String player) {
        for(File file : SAVE_PLAYER_FOLDER.listFiles()) {
            if (file.getName().replace(".json", "").equals(player))
            {
                return file;
            }
        }

        return null;
    }
}
