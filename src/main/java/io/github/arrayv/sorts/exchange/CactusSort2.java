package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

final public class CactusSort2 extends Sort {
    public CactusSort2(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Cactus II");
        this.setRunAllSortsName("Cactus Sort II");
        this.setRunSortName("Cactus Sort II");
        this.setCategory("Exchange Sorts");
		this.setAuthors("Distray");
        this.setConstant("n^3");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
    // based off an earlier version of the Cactus loop that had a high chance of never sorting
    
    private int find(int[] array, int a, int b, int k) {
    	int min = -1;
    	for(int i = a; i < b; i++) {
    		if(i != k && Reads.compareIndices(array, i, k, 0.1, true) < 0 && (min == -1 || Reads.compareIndices(array, min, i, 0.1, true) <= 0)) {
    			min = i;
    		}
    	}
    	return min;
    }

    @Override
    public void runSort(int[] array, int currentLength, int bucketCount) {
    	int A = 0, B = currentLength;
    	for (; A < B - 1;) {
    		
			int v, Ad = 0, Pd = 0, I = (A + B) / 2;
			for (int i = A; i < I; i++) {
				int L = I, M = B;
				do {
					v = find(array, L, M, i);
					L = (A + L) / 2; M = (A + M) / 2;
				} while (v == -1 && M > A);
				if (v == B - 1) {
					Writes.multiSwap(array, B-1, i, 0.125, true, false);
					Pd++;
				} else {
					if (Pd > 1) Writes.reversal(array, i-Pd, i-1, 0.5, true, false);
					Pd = 0;
					if (Math.max(v+1, A) != i) {
						if (Reads.compareIndices(array, Math.max(v+1, A), i, 0.05, true) == 0) {
							Writes.multiSwap(array, i, Math.max(v+1, A), 0.025, true, false);
						} else Writes.swap(array, Math.max(v + 1, A), i, 0.125, true, false);
					}
				}
				if (M == A || v == A + Ad - 1) Ad++;
			}
			if (Pd > 1) Writes.reversal(array, I-Pd, I-1, 0.5, true, false);
			A += Ad;
    	}
    }
}