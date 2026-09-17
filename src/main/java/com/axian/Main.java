package com.axian;

import com.axian.materials.BranchMapHandler;
import com.axian.materials.RootMapHandler;
import com.axian.ui.Window;

import java.io.File;
import java.io.IOException;

public class Main {

    // Folders for the root and branch json files
    static final File rootsFolder = new File("src/main/resources/materials/root");
    static final File branchesFolder = new File("src/main/resources/materials/branch");

    static void main() throws IOException {

        RootMapHandler rootMapHandler = new RootMapHandler(rootsFolder);

        BranchMapHandler branchMapHandler = new BranchMapHandler(branchesFolder);

            JSONTokener json = new JSONTokener(openFile.readAllAsString());
            JSONObject jsonFile = new JSONObject(json);


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
