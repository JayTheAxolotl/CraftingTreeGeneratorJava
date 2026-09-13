package com.axian.materials.types;

public abstract class BaseMaterial {
    protected String id;
    protected String readableName; // Human-readable name
    protected float craftedAmount; // The amount of the material that is crafted

    public BaseMaterial(String givenId, float givenCraftedAmount, String givenReadableName){
        id = givenId;
        craftedAmount = givenCraftedAmount;
        readableName = givenReadableName;
    }

    public String getId(){
        return id;
    }

    public float getCraftedAmount(){
        return craftedAmount;
    }

    public String getReadableName() {
        return readableName;
    }

    public void printTree(int depth, float used) {}

    public void printTree(int depth){
        printTree(depth, 1f);
    }

    public void printTree(){
        printTree(0, 1f);
    }

}
