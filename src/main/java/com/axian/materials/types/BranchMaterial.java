package com.axian.materials.types;

import com.axian.interfaces.Ingredients;
import com.axian.materials.MaterialLists;

import java.util.Map;

public class BranchMaterial extends BaseMaterial{
    Map<BaseMaterial, Float> ingredients;

    public BranchMaterial(String givenId, float givenCraftedAmount, Ingredients givenIngredients) {
        super(givenId, givenCraftedAmount);
        ingredients = givenIngredients.setIngredients();
        MaterialLists.branchMaterials.add(this);
    }

    public void printIngredients(){
        ingredients.forEach((ingredient, amount) -> {
            IO.println(ingredient.id + ", " + amount);
        });
    }

    @Override
    public void printTree(int depth, float used){
        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        IO.println(indents + id + ", " + used);

        ingredients.forEach((ingredient, amount) -> {
            ingredient.printTree(depth + 1,
                    amount * (float) Math.ceil(used / craftedAmount));
        });

        if (depth == 0){
            IO.println("\nTotals: ");
            MaterialLists.rootMaterials.forEach((material, amount) ->{
                if (amount > 0) {
                    IO.println(material.id + ", " + amount);
                    MaterialLists.rootMaterials.put(material, 0f);
                }
            });
        }
    }




}
