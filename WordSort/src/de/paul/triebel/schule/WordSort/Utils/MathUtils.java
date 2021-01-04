package de.paul.triebel.schule.WordSort.Utils;

import java.awt.Rectangle;

public class MathUtils {

	public static float remap(float inMin, float inMax, float outMin, float outMax, float value) {
		return outMin + (value - inMin) * (outMax - outMin) / (inMax - inMin);
	}

	public static boolean[] inside(Rectangle object, Rectangle inside) {
		boolean width = true;
		boolean height = true;
		
		if (inside.x < object.x && object.x+object.width < inside.x+inside.width) {
			width = false;
		}
		
		if (inside.y < object.y && object.y+object.height < inside.y+inside.height) {
			height = false;
		}
		
		return new boolean[] {width, height};
	}

	public static float clamp(float min, float max, float value) {
		return Math.max(min, Math.min(max, value));
	}
	
}
