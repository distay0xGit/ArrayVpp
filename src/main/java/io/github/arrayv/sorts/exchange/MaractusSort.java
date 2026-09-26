package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

final public class MaractusSort extends Sort {
    public MaractusSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Maractus");
        this.setRunAllSortsName("Maractus Sort");
        this.setRunSortName("Maractus Sort");
        this.setCategory("Exchange Sorts");
		this.setAuthors("Distray");
        this.setConstant("n^3");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
    
    private int find(int[] array, int a, int l, int r, int b, int k) {
    	int min = -1;
    	for(int i = l; i < r; i++) {
    		if(Reads.compareIndices(array, i % (b - a) + a, k, 0.1, true) < 0 && (min == -1 || Reads.compareIndices(array, min % (b - a) + a, i % (b - a) + a, 0.1, true) <= 0)) {
    			min = i;
    		}
    	}
    	return min;
    }

    @Override
    public void runSort(int[] array, int currentLength, int bucketCount) {
    	int A = 0, B = currentLength;
    	for (; A < B - 1;) {
			for(int i = A; i < B; i++) {
				int v, R = B - A, L = 1;
				do {
					v = find(array, A, L + i - A, R + i - A, B, i);
					R /= 2; L /= 2;
				} while (L > 0 && v == -1);
				if (v == -1) Writes.swap(array, A++, i, 0.25, true, false);
				else Writes.swap(array, (v + 1) % (B - A) + A, i, 0.25, true, false);
			}
    	}
    }
}