package com.axian.materials;

import com.axian.materials.types.BaseMaterial;
import com.axian.materials.types.BranchMaterial;
import com.axian.materials.types.RootMaterial;

import java.util.HashMap;
import java.util.Map;

public class MaterialLists {
    public static Map<RootMaterial, Float> rootMaterialUsages = new HashMap<>();
    public static Map<BranchMaterial, Float> branchMaterialUsages = new HashMap<>();

    public static Map<String, BaseMaterial> materials = new HashMap<>();
}
