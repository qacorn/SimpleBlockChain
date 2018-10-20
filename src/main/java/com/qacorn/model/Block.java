package com.qacorn.model;

import com.qacorn.utility.StringUtility;

import java.util.Date;

public class Block {
    private int nonce;
    private String data;
    private String preHash;
    private String hash;
    private long timestamp;

    public Block(String data, String preHash) {
        this.data = data;
        this.preHash = preHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtility.applySha256(preHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = StringUtility.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("创建区块:" + hash);
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreHash() {
        return preHash;
    }

    public void setPreHash(String preHash) {
        this.preHash = preHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Block{" +
                "nonce=" + nonce +
                ", data='" + data + '\'' +
                ", preHash='" + preHash + '\'' +
                ", hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
