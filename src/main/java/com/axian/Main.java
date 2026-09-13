package com.axian;

import com.axian.materials.types.BaseMaterial;
import com.axian.materials.types.BranchMaterial;
import com.axian.materials.types.RootMaterial;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    static void main() {
        // An example of print
        // IO.println(String.format("Hello and welcome!"));

        RootMaterial oakLog = new RootMaterial("oak_log");

        RootMaterial cobblestone = new RootMaterial("cobblestone");

        RootMaterial diamond = new RootMaterial("diamond");

        RootMaterial ancientDebris = new RootMaterial("ancient_debris");

        RootMaterial rawGold = new RootMaterial("raw_gold");

        BranchMaterial oakPlank = new BranchMaterial("oak_plank", 4f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(oakLog, 1f);
                    return ingredientsBuffer;
                });

        BranchMaterial stick = new BranchMaterial("stick", 4f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(oakPlank, 2f);
                    return ingredientsBuffer;
                });

        BranchMaterial woodenPickaxe = new BranchMaterial("wooden_pickaxe", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(oakPlank, 3f);
                    ingredientsBuffer.put(stick, 2f);
                    return ingredientsBuffer;
                });

        BranchMaterial stonePickaxe = new BranchMaterial("stone_pickaxe", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(cobblestone, 3f);
                    ingredientsBuffer.put(stick, 2f);
                    return ingredientsBuffer;
                });

        BranchMaterial goldIngot = new BranchMaterial("gold_ingot", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(rawGold, 1f);
                    return ingredientsBuffer;
                });

        BranchMaterial netheriteScrap = new BranchMaterial("netherite_scrap", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(ancientDebris, 1f);
                    return ingredientsBuffer;
                });

        BranchMaterial netheriteIngot = new BranchMaterial("netherite_ingot", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(goldIngot, 4f);
                    ingredientsBuffer.put(netheriteScrap, 4f);
                    return ingredientsBuffer;
                });

        BranchMaterial diamondPickaxe = new BranchMaterial("diamond_pickaxe", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(diamond, 3f);
                    ingredientsBuffer.put(stick, 2f);
                    return ingredientsBuffer;
                });

        BranchMaterial netheritePickaxe = new BranchMaterial("netherite_pickaxe", 1f,
                () -> {
                    Map<BaseMaterial, Float> ingredientsBuffer = new LinkedHashMap<>();
                    ingredientsBuffer.put(diamondPickaxe, 1f);
                    ingredientsBuffer.put(netheriteIngot, 1f);
                    return ingredientsBuffer;
                });

        netheritePickaxe.printTree();
    }
}
