package com.axian.materials.types;

public abstract class BaseMaterial {
    protected String id;
    protected float craftedAmount; // The amount of the material that is crafted

    public BaseMaterial(String givenId, float givenCraftedAmount){
        id = givenId;
        craftedAmount = givenCraftedAmount;
    }

    public String getId(){
        return id;
    }

    public float GetCraftedAmount(){
        return craftedAmount;
    }

    public void printTree(int depth, float used){

    }

    public void printTree(int depth){
        printTree(depth, 1f);
    }

    public void printTree(){
        printTree(0, 1f);
    }
}
