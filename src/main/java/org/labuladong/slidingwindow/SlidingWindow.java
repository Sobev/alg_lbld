package org.labuladong.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luojx
 * @date 2022/7/2 14:24
 */
public class SlidingWindow {
    /*
    public void slidingWindow(String s){
        int left=0,right=0;
        while (right < s.length()){
            char c = s.charAt(right);
            right++;
            System.out.println(left + " " + right);
            while (window need shrink){
                char r = s.charAt(left);
                left++;
            }
        }
    }
    */

    public String minWindow(String source, String target) {
        Map<Character, Integer> needs = new HashMap<>(),
                window = new HashMap<>();
        for (char c : target.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
//        [)
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == needs.get(c)) {
                    valid++;
                }
            }
            while (valid == window.size()) {
                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                char r = source.charAt(left);
                left++;
                if (needs.containsKey(r)) {
                    window.put(r, window.get(r) - 1);
                    if (window.get(r) < needs.get(r)) {
                        valid--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : target.substring(start, start + len);
    }

}
