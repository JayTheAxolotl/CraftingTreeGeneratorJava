package com.axian.materials.types;

import com.axian.materials.MaterialMaps;

public class RootMaterial extends BaseMaterial{

    public RootMaterial(String givenId, String givenReadableName) {
        super(givenId, 1f, givenReadableName);
        MaterialMaps.rootMaterialUsages.put(this, 0f);
    }

    @Override
    public String getTree(int depth, float used) {
        String tree = "";
        String indents = "";

        for(int i = 0; i < depth; i++) {indents = indents + "  ";}
        tree += "\n" + indents + readableName + ", " + used;

        // Add usages of this material to the usages map
        MaterialMaps.rootMaterialUsages.put(this, used +
                MaterialMaps.rootMaterialUsages.get(this));

        return tree;
    }
}
