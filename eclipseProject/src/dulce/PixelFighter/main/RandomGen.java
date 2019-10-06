package dulce.PixelFighter.main;

import java.util.*;

public class RandomGen {
	public static int intGen(int highest, int lowest) {
		Random random = new Random();
		return (random.nextInt(highest - lowest)) - lowest;
	}
}
