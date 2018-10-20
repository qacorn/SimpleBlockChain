package com.qacorn.service;

import com.qacorn.model.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockChainService {
    private List<Block> blockList = new ArrayList<>(32);
    private int difficulty = 5;

    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockList.add(newBlock);
    }

    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockList.size(); i++) {
            currentBlock = blockList.get(i);
            previousBlock = blockList.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.getHash().equals(currentBlock.getPreHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("这个区块还没有被开采。。。");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BlockChainService blockChainService = new BlockChainService();
        blockChainService.addBlock(new Block("我是第一个区块","0"));
        blockChainService.addBlock(new Block("我是第二个区块",blockChainService.blockList.get(0).getHash()));
        blockChainService.addBlock(new Block("我是第三个区块",blockChainService.blockList.get(1).getHash()));

        System.out.println(blockChainService.isChainValid());

        for (int i = 0; i < blockChainService.blockList.size(); i++) {

            System.out.println(blockChainService.blockList.get(i));

        }
    }
}
