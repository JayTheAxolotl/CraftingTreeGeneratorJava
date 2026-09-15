package com.axian.materials.types;

import com.axian.interfaces.IngredientBundles;
import com.axian.materials.MaterialMaps;

import java.util.Map;

public class BranchMaterial extends BaseMaterial{
    Map<String, Float> ingredients;

    public BranchMaterial(String givenId, float givenCraftedAmount, IngredientBundles givenIngredients, String givenReadableName) {
        super(givenId, givenCraftedAmount, givenReadableName);
        ingredients = givenIngredients.setIngredientIds();
        MaterialMaps.branchMaterialUsages.put(this, 0f);
    }

    public void printIngredients(){
        ingredients.forEach((ingredientId, amount) -> {
            BaseMaterial ingredient = MaterialMaps.materials.get(ingredientId);
            IO.println(ingredient.id + ", " + amount);
        });
    }

    @Override
    public void printTree(int depth, float used){
        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        IO.println(indents + readableName + ", " + used);

        ingredients.forEach((ingredientId, amount) -> {
            BaseMaterial ingredient = MaterialMaps.materials.get(ingredientId);
            ingredient.printTree(depth + 1,
                    amount * (float) Math.ceil(used / craftedAmount));
        });

        // If this is the first printTree call recursively
        if (depth == 0){
            IO.println("\nTotals: ");
            // Go through the root material usage map to print out what has been used for this call
            MaterialMaps.rootMaterialUsages.forEach((material, amount) ->{
                if (amount > 0) {
                    IO.println(material.getReadableName() + ", " + amount);
                    // Reset root material usage in its map
                    MaterialMaps.rootMaterialUsages.put(material, 0f);
                }
            });
        }
    }

    @Override
    public String getTree(int depth, float used) {
        String tree = "";

        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        tree += "\n" + indents + readableName + ", " + used;

        // Use this forEach loop instead of .forEach(()->{}) because I need to change tree
        for (Map.Entry<String, Float> ingredientEntry : ingredients.entrySet() ) {
            BaseMaterial ingredient = MaterialMaps.materials.get(ingredientEntry.getKey());
            tree += ingredient.getTree(depth + 1,
                    ingredientEntry.getValue() * (float) Math.ceil(used / craftedAmount));
        }

        // If this is the first printTree call recursively
        if (depth == 0){
            tree += "\nTotals:";

            // Use this forEach loop instead of .forEach(()->{}) because I need to change tree
            for (Map.Entry<RootMaterial, Float> usageEntry : MaterialMaps.rootMaterialUsages.entrySet()) {
                if (usageEntry.getValue() > 0) {
                    tree += "\n" + usageEntry.getKey().getReadableName() + ", " + usageEntry.getValue();
                    // Reset root material usage in its map
                    MaterialMaps.rootMaterialUsages.put(usageEntry.getKey(), 0f);
                }
            }
        }

        return tree;
    }



}
