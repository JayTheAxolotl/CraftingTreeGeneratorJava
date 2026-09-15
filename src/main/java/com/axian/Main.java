package com.axian;

import com.axian.materials.MaterialMaps;
import com.axian.materials.types.BranchMaterial;
import com.axian.materials.types.RootMaterial;
import com.axian.ui.Window;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    // Folders for the root and branch json files
    static final File rootsFolder = new File("src/main/resources/materials/root");
    static final File branchesFolder = new File("src/main/resources/materials/branch");

    static void main() throws IOException {

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

        /*/ DEBUG
        MaterialMaps.materials.forEach((id, material) ->{
            IO.println(material.getId() + ", " + material.getClass());
        });

        IO.println("");
        MaterialMaps.materials.get("stone_pickaxe").printTree();
         /**/

        Window window = new Window();

    }

}
