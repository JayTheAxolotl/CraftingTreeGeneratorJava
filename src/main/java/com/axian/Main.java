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
    static final File iconsFolder = new File("src/main/resources/textures");

    static void main() throws IOException {

        RootMapHandler rootMapHandler = new RootMapHandler(rootsFolder);

        BranchMapHandler branchMapHandler = new BranchMapHandler(branchesFolder);

        Window window = new Window(iconsFolder);

        /*/ DEBUG
        com.axian.materials.MaterialMaps.materials.forEach((id, material) ->{
            IO.println(material.getId() + ", " + material.getClass());
        });

        IO.println("");
        com.axian.materials.MaterialMaps.materials.get("stone_pickaxe").printTree();
         /**/

    }

}
