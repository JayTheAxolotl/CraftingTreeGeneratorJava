package com.axian.materials.types;

import com.axian.materials.MaterialLists;

public class RootMaterial extends BaseMaterial{

    public RootMaterial(String givenId) {
        super(givenId, 1f);
        MaterialLists.rootMaterials.put(this, 0f);
    }

    @Override
    public void printTree(int depth, float used){
        String indents = "";
        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        IO.println(indents + id + ", " + used);

        MaterialLists.rootMaterials.put(this, used +
                MaterialLists.rootMaterials.get(this));
    }
}
