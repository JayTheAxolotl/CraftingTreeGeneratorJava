package com.axian.materials;

import com.axian.materials.types.RootMaterial;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RootMapHandler {
    public RootMapHandler(File rootsFolder) throws IOException {
        File[] rootFiles = rootsFolder.listFiles();
        for (File file : rootFiles) {

            BufferedReader openFile = new BufferedReader(new FileReader(file));

            JSONTokener json = new JSONTokener(openFile.readAllAsString());
            JSONObject jsonFile = new JSONObject(json);

            MaterialMaps.materials.put(
                    // .replaceAll is to remove the .json file extension
                    file.getName().replaceAll(".json", ""),
                    new RootMaterial(
                            file.getName().replaceAll(".json", ""),
                            jsonFile.getString("name")
                    )
            );

            openFile.close();

        }
    }
}
