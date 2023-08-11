package org.labuladong.bfs;

import java.util.*;

/**
 * @author luojx
 * @date 2022/6/24 11:02
 */
public class PuzzlePlugin {

    public static void main(String[] args) {
        PuzzlePlugin puzzlePlugin = new PuzzlePlugin();
        int[][] board = new int[][]{
                new int[]{4,1,2},
                new int[]{5,0,3}
        };
        List<Track> trackList = puzzlePlugin.slidingPuzzle(board);
        Collections.sort(trackList, (o1, o2) -> {
            return o1.getId() - o2.getId();
        });
        trackList.forEach(System.out::println);
    }

    public List<Track> slidingPuzzle(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        //plugin
        int track = 0;
        List<Track> trackList = new ArrayList<>();
        List<List<Integer>> neighbors = getNeighbors(row, col);
        //generate string puzzle
        StringBuilder builder = new StringBuilder();
        for (int[] r : board) {
            for (int c : r) {
                builder.append(c);
            }
        }
        String puzzleRes = getPuzzleRes(row, col);
        String puzzle = builder.toString();
        Queue<Track> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Track(track++, puzzle, 0));
        visited.add(puzzle);
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Track puz = queue.poll();
                trackList.add(puz);
                if (puz.getPuz().equals(puzzleRes)) {
                    return getRealPath(trackList);
                }
                //find '0' idx
                int idx = 0;
                for (; puz.getPuz().charAt(idx) != '0'; idx++) ;
                List<Integer> moveAbleNeighbors = neighbors.get(idx);
                for (Integer neighborIdx : moveAbleNeighbors) {
                    char[] array = puz.getPuz().toCharArray();
                    array[idx] = array[neighborIdx];
                    array[neighborIdx] = '0';
                    String p = new String(array);
                    if (!visited.contains(p)) {
                        queue.offer(new Track(track++, p, puz.getId()));
                        visited.add(p);
                    }
                }
            }
            time++;
        }
        return null;
    }

    /*
        从m行n列获取各个下标的邻居
     */
    private List<List<Integer>> getNeighbors(int m, int n) {
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>();
                //left
                if ((j - 1) >= 0) {
                    list.add(i * n + j - 1);
                }
                //right
                if ((j + 1) < n) {
                    list.add(i * n + j + 1);
                }
                //up
                if ((i - 1) >= 0) {
                    list.add(i * n + j - n);
                }
                //down
                if ((i + 1) < m) {
                    list.add(i * n + j + n);
                }
                neighbors.add(list);
            }
        }
        return neighbors;
    }
    /**
     * 获取m行n列的解
     */
    private String getPuzzleRes(int m, int n){
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < m*n; i++) {
            builder.append(i);
        }
        builder.append(0);
        return builder.toString();
    }

    public List<Track> getRealPath(List<Track> trackList){
        List<Track> real = new ArrayList<>();
        Track lastStep = trackList.get(trackList.size() - 1);
        Map<Integer, Track> map = new HashMap<>();
        trackList.forEach(item -> {
            map.put(item.getId(), item);
        });
        recursion(map, lastStep.getId(), real);
        return real;
    }
    private void recursion(Map<Integer, Track> map, Integer id, List<Track> real){
        real.add(map.get(id));
        if(id == 0){
            return;
        }
        recursion(map, map.get(id).getParentId(), real);
    }

    public static class Track{
        private Integer id;
        private String puz;
        private Integer parentId;

        public Track(){}

        public Track(Integer id, String puz, Integer parentId) {
            this.id = id;
            this.puz = puz;
            this.parentId = parentId;
        }

        public String getPuz() {
            return puz;
        }

        public void setPuz(String puz) {
            this.puz = puz;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Track{" +
                    "id=" + id +
                    ", puz='" + puz + '\'' +
                    ", parentId=" + parentId +
                    '}';
        }
    }
}
