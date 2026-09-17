package com.axian.materials;

import com.axian.materials.types.BranchMaterial;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// Handles creating the map for the branch recipes

public class BranchMapHandler {
    public BranchMapHandler(File branchesFolder) throws IOException {
        File[] branchFiles = branchesFolder.listFiles();
        for (File file : branchFiles) {

            BufferedReader openFile = new BufferedReader(new FileReader(file));

            JSONTokener json = new JSONTokener(openFile.readAllAsString());
            JSONObject jsonFile = new JSONObject(json);

            MaterialMaps.materials.put(
                    file.getName().replaceAll(".json", ""),
                    new BranchMaterial(
                            file.getName().replaceAll(".json", ""),
                            jsonFile.getFloat("crafted_amount"),
                            () -> {
                                Map<String, Float> ingredientsBuffer = new LinkedHashMap<>();

                                // Get data from the json file's ingredients key
                                jsonFile.getJSONObject("ingredients").toMap().forEach((id, amount) -> {
                                    // Divide by 1f to convert int to float, because it's dumb
                                    ingredientsBuffer.put(id, (int) amount / 1f);
                                });

                                return ingredientsBuffer;
                            },
                            jsonFile.getString("name"))
            );

            openFile.close();

        }
    }
}
