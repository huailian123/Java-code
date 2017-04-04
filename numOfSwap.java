n个数，打乱顺序的，排成降序，基本思想就是跟排好序的比较，位置没变的不考虑，剩下的减去这些数构成环的个数就行
比如2，3，4，5，1
应该是5，4，3，2，1
2，5一个环，3，4一个环，最后就是4-2=2

再比如3，5，4，2，1
3，5，4构成一个环，所以是3-1=2

// assume input are all integers, no duplictated numbers.

// tested
	public static int numOfSwap(int[] array) {
		// corner case
		if (array == null || array.length == 0) {
			return 0;
		}
		int[] notInorder = new int[] {0};  //count how many number no in correct order
		Map<Integer, Integer> graph = getMap(array, notInorder); //find all the <curIdx, correctIdx> pairs
		int numLoops = findLoops(graph); // find out how many loops for the nums not in the correct position
		return notInorder[0] - numLoops; // return the res
	}

	private static Map<Integer, Integer> getMap(int[] array, int[] notInorder) {
		//creat a map to store the sorted array nums and their idxs
		Map<Integer, Integer> map1 = new HashMap<>();   //<num, curIdx>
		for (int i = 0; i < array.length; i++) {
			map1.put(array[i], i);		
		}
		//sort the array 
		Integer[] newArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		//NOTE: can't use Collections.reverseOrder() for primitive types
		Arrays.sort(newArray, Collections.reverseOrder());
		
		// find out the numbers not in the correct position
		Map<Integer, Integer> map2 = new HashMap<>();   //<curIdx, new idx>
		for (int i = 0; i < array.length; i++) {
			Integer curIdx = map1.get(newArray[i]);
			if (curIdx != i) {
				notInorder[0]++;
				map2.put(curIdx, i);
			}
		}
		return map2;
	}

	private static int findLoops (Map<Integer, Integer> map) {
		Set<Integer> set = new HashSet<>();   // store the already checked pair
		int numLoop = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int cur = entry.getKey();
			if (!set.contains(cur)) {
				numLoop++;
				set.add(cur);
				int save = cur;
				while (map.get(cur) != save) {
					set.add(map.get(cur));
					cur = map.get(cur);
				}
			}
		}
		return numLoop;
	}
	  
