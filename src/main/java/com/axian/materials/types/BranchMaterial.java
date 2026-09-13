package com.axian.materials.types;

import com.axian.interfaces.Ingredients;
import com.axian.materials.MaterialLists;

import java.util.Map;

public class BranchMaterial extends BaseMaterial{
    Map<String, Float> ingredients;

    public BranchMaterial(String givenId, float givenCraftedAmount, Ingredients givenIngredients, String givenReadableName) {
        super(givenId, givenCraftedAmount, givenReadableName);
        ingredients = givenIngredients.setIngredients();
        MaterialLists.branchMaterialUsages.put(this, 0f);
    }

    public void printIngredients(){
        ingredients.forEach((ingredientId, amount) -> {
            BaseMaterial ingredient = MaterialLists.materials.get(ingredientId);
            IO.println(ingredient.id + ", " + amount);
        });
    }

    @Override
    public void printTree(int depth, float used){
        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        IO.println(indents + readableName + ", " + used);

        ingredients.forEach((ingredientId, amount) -> {
            BaseMaterial ingredient = MaterialLists.materials.get(ingredientId);
            ingredient.printTree(depth + 1,
                    amount * (float) Math.ceil(used / craftedAmount));
        });

        if (depth == 0){
            IO.println("\nTotals: ");
            MaterialLists.rootMaterialUsages.forEach((material, amount) ->{
                if (amount > 0) {
                    IO.println(material.getReadableName() + ", " + amount);
                    MaterialLists.rootMaterialUsages.put(material, 0f);
                }
            });
        }
    }




}
