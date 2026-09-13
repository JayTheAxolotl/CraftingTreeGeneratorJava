package com.axian.materials.types;

import com.axian.materials.MaterialLists;

public class RootMaterial extends BaseMaterial{

    public RootMaterial(String givenId, String givenReadableName) {
        super(givenId, 1f, givenReadableName);
        MaterialLists.rootMaterialUsages.put(this, 0f);
    }

    @Override
    public void printTree(int depth, float used){
        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        IO.println(indents + readableName + ", " + used);

        MaterialLists.rootMaterialUsages.put(this, used +
                MaterialLists.rootMaterialUsages.get(this));
    }
}
