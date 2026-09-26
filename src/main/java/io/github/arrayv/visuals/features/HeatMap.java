package io.github.arrayv.visuals.features;

import java.awt.Color;

import com.scrtwpns.Mixbox;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.utils.Highlights;
import io.github.arrayv.visuals.VisualFeature;

public class HeatMap extends VisualFeature {
	private static Highlights HIGHLIGHTS;
	
    // loosely based on :matter
    private static Color[] STATES = {
    	new Color(25, 12, 50),
    	new Color(127, 32, 95),
        new Color(255, 127, 127),
        new Color(255, 255, 191),
        new Color(240, 255, 240)
    };
    
    private static float[] CUTS = {
    	1f/6f, 1f/2f, 1f/3f, 1f
    };
	
    public HeatMap(ArrayVisualizer arrayVisualizer) {
    	super(arrayVisualizer);
    	HIGHLIGHTS = arrayVisualizer.getHighlights();
    	
        this.setListID("heat");
        this.setListName("Heat Map");
        this.setFlipsDown("$color");
    }
    
    public static Color getColor(int[] array, int pos) {
		float heat = HIGHLIGHTS.heatAt(array, pos);
		if (heat >= 0f) {
    		int idx = 0;
    		float cutval = 0f;
    		while(cutval <= heat) {
    			cutval += CUTS[idx++];
    		}
    		return new Color(Mixbox.lerp(
                STATES[idx-1].getRGB(),
                STATES[idx].getRGB(),
            	(heat - cutval + CUTS[idx-1]) / CUTS[idx-1]
            ));
		} else return null;
    }
    
    public void localPostrender(int[] array, int n) {
    	HIGHLIGHTS.coolDown(array, n);
    }
}