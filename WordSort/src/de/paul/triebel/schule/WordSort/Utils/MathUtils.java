package de.paul.triebel.schule.WordSort.Utils;

public class MathUtils {
	
	public static float remap(float inMin, float inMax, float outMin, float outMax, float value) {
		return outMin + (value - inMin) * (outMax - outMin) / (inMax - inMin);
	}
	
}
