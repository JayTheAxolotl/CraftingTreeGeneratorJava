package com.axian.materials;

import com.axian.materials.types.BaseMaterial;
import com.axian.materials.types.BranchMaterial;
import com.axian.materials.types.RootMaterial;

import java.util.HashMap;
import java.util.Map;

public class MaterialMaps {
    // Maps for the total usages of materials
    public static Map<RootMaterial, Float> rootMaterialUsages = new HashMap<>();
    public static Map<BranchMaterial, Float> branchMaterialUsages = new HashMap<>();

    public static Map<String, BaseMaterial> materials = new HashMap<>(); // Map for all materials
}
