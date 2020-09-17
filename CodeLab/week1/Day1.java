package week1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day1 {
    public String sumOfTwoStrings(String num1, String num2) {
        if (num1.length() == 0 && num2.length() == 0) {
            return "";
        }
        String result = "";
        int add = 0;
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        while (l1 >= 0 || l2 >= 0) {
            int val1 = l1 >= 0 ? num1.charAt(l1) - '0' : 0;
            int val2 = l2 >= 0 ? num2.charAt(l2) - '0' : 0;
            result = ((val1 + val2 + add) % 10) + result;
            if (val1 + val2 + add >= 10) {
                add = 1;
            } else {
                add = 0;
            }
            l1--;
            l2--;
        }
        if (add == 1) {
            result = 1 + result;
        }
        return result;
    }


    public int[] twoSumProblem(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public void rotateArray(int[] arr, int k) {
        int length = arr.length;
        if (length == 0 || k % length == 0) {
            return;
        }

        k = k % length;
        int count = 0;
        for (int startPosition = 0; count < length; startPosition++) {
            int currentPosition = startPosition;
            int currentValue = arr[currentPosition];
            do {
                currentPosition = (currentPosition + k) % length;
                int temp = arr[currentPosition];
                arr[currentPosition] = currentValue;
                currentValue = temp;
                count++;
            } while (startPosition != currentPosition);

        }
    }

    public boolean isUnique(String s) {
        if (s.length() <= 1) {
            return true;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    public boolean checkPermutation(String a, String b) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        if (b.length() < a.length()) {return false; }
        for (int i = 0; i < a.length(); i++) {
            arr1[a.charAt(i) - 'a']++;
            arr2[b.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (arr1[i] == arr2[i]) {
                count++;
            }
        }
        for (int i = 0; i < b.length() - a.length(); i++) {
            if (count == 26) {
                return true;
            }
            int left = b.charAt(i) - 'a';
            int right = b.charAt(i + a.length()) - 'a';
            arr2[right]++;
            if (arr2[right] == arr1[right]) {
                count++;
            } else if (arr2[right] == arr1[right] + 1) {
                count--;
            }
            arr2[left]--;
            if (arr2[left] == arr1[left]) {
                count++;
            } else if (arr2[left] == arr1[left] - 1) {
                count--;
            }

            System.out.println(count);
        }
        return count == 26;
    }
}
