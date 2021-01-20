package org.bl.algo;

/**
 * @author beckl
 */
public class UnionFindTest {
    public static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        private int count = 0;

        public UnionFind(char[][] grid) {
            int rowNum = grid.length;
            int colNum = grid[0].length;
            parent = new int[rowNum * colNum];
            rank = new int[rowNum * colNum];
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * colNum + j] = i * colNum + j;
                        ++count;
                    }
                    // rank[i * colNum + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int current, int neighbor) {
            int parentOfCurrent = find(current);
            int parentOfNeighbor = find(neighbor);
            if (parentOfCurrent != parentOfNeighbor) {
                if (rank[parentOfCurrent] > rank[parentOfNeighbor]) {
                    parent[parentOfNeighbor] = parentOfCurrent;
                } else if (rank[parentOfCurrent] < rank[parentOfNeighbor]) {
                    parent[parentOfCurrent] = parentOfNeighbor;
                } else {
                    parent[parentOfNeighbor] = parentOfCurrent;
                    rank[parentOfCurrent] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1'}, {'0', '0', '1'}, {'0', '1', '1'}};
        System.out.println(numIslands(grid));
    }
}
