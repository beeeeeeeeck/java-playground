package org.bl.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


public class ConsistentHashLoadBalance {

    private final TreeMap<Long, String> virtualNodes = new TreeMap<>();
    private final LinkedList<String> nodes;
    private final int replicaCount; // the virtual nodes count for each real node

    public ConsistentHashLoadBalance(LinkedList<String> nodes, int replicaCount) {
        this.nodes = nodes;
        this.replicaCount = replicaCount;
        initialization();
    }

    /**
     * Initialize the hash ring and compute the hash value for each node
     */
    private void initialization() {
        for (String nodeName : nodes) {
            for (int i = 0; i < replicaCount / 4; i++) {
                String virtualNodeName = getNodeNameByIndex(nodeName, i);
                for (int j = 0; j < 4; j++) {
                    virtualNodes.put(hash(virtualNodeName, j), nodeName);
                }
            }
        }
    }

    private String getNodeNameByIndex(String nodeName, int index) {
        return nodeName + "&&" + index;
    }

    /**
     * Select node by key
     */
    public String selectNode(String key) {
        Long hashOfKey = hash(key, 0);
        if (!virtualNodes.containsKey(hashOfKey)) {
            Map.Entry<Long, String> entry = virtualNodes.ceilingEntry(hashOfKey);
            if (entry != null)
                return entry.getValue();
            else
                return nodes.getFirst();
        } else
            return virtualNodes.get(hashOfKey);
    }

    private Long hash(String nodeName, int number) {
        byte[] digest = md5(nodeName);
        assert digest != null;
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
            | ((long) (digest[2 + number * 4] & 0xFF) << 16)
            | ((long) (digest[1 + number * 4] & 0xFF) << 8)
            | (digest[number * 4] & 0xFF))
            & 0xFFFFFFFFL;
    }

    /**
     * md5 encryption
     */
    private byte[] md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes(StandardCharsets.UTF_8));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addNode(String node) {
        nodes.add(node);
        String virtualNodeName = getNodeNameByIndex(node, 0);
        for (int i = 0; i < replicaCount / 4; i++) {
            for (int j = 0; j < 4; j++) {
                virtualNodes.put(hash(virtualNodeName, j), node);
            }
        }
    }

    public void removeNode(String node) {
        nodes.remove(node);
        String virtualNodeName = getNodeNameByIndex(node, 0);
        for (int i = 0; i < replicaCount / 4; i++) {
            for (int j = 0; j < 4; j++) {
                virtualNodes.remove(hash(virtualNodeName, j), node);
            }
        }
    }

    private void printTreeNode() {
        if (!virtualNodes.isEmpty()) {
            virtualNodes.forEach((hashKey, node) ->
                System.out.println(node + " ==> " + hashKey)
            );
            return;
        }

        System.out.println("Cycle is Empty");
    }

    public static void main(String[] args) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.add("192.168.13.1:8080");
        nodes.add("192.168.13.2:8080");
        nodes.add("192.168.13.3:8080");
        nodes.add("192.168.13.4:8080");
        ConsistentHashLoadBalance consistentHash = new ConsistentHashLoadBalance(nodes, 160);
        consistentHash.printTreeNode();
    }
}