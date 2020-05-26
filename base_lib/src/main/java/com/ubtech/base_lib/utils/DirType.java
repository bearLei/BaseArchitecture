package com.ubtech.base_lib.utils;

public enum DirType {

    AI("ai", 1),
    AUDIO("audio", 2),
    B2PVM("b2pvm", 3),
    BLOCKLY("blockly", 4),
    CAMERA("camera", 5),
    LOG("log", 6),
    OTA("ota", 7),
    ROBOT("robot", 8),
    U3D("u3d", 9),
    THUMBNAIL("thumbnail", 10);//缩略图路径


    private String name;
    private int type;


    DirType(String name, int type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public int getType(){
        return this.type;
    }

}
