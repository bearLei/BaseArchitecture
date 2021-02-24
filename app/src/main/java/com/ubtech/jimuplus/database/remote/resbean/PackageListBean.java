package com.ubtech.jimuplus.database.remote.resbean;

import com.ubtech.jimuplus.base.data.Package;

import java.io.Serializable;
import java.util.List;

public class PackageListBean implements Serializable {

    /**
     * info : 0000
     * models : [{"EAN":"6931705004588,6931705069334","UPC":"869608000351","createTime":1534487303000,"displayOrder":40,"endNum":0,"id":28,"isDefault":0,"isDeleted":0,"isLatest":"1","packageDiscription":"Astrobot-中国","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/Astrobot_CN.png","packageName":"Astrobot_CN","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004571","UPC":"869608000320","createTime":1534487303000,"displayOrder":40,"endNum":0,"id":29,"isDefault":0,"isDeleted":0,"isLatest":"1","packageDiscription":"Astrobot-北美","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/Astrobot_NA.png","packageName":"Astrobot_NA","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705006971,6931705069440","UPC":"","createTime":1534558113000,"displayOrder":60,"endNum":0,"id":34,"isDefault":0,"isDeleted":0,"isLatest":"1","packageDiscription":"晨星-中国","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/chenXing_CN.png","packageName":"chenXing_CN","packageType":"model","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004595,6931705004915,6931705005059,6931705069310","UPC":"869608000337,858119007157,858119007164","createTime":1534475761000,"displayOrder":70,"endNum":0,"id":30,"isDefault":0,"isDeleted":0,"isLatest":"1","packageDiscription":"BuilderBot","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/BuilderBot.png","packageName":"BuilderBot","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004144,6931705004182,6931705004199,6931705004205","UPC":"868053000206,860511000278,868053000213,868053000220,858119007058","createTime":1534490972000,"displayOrder":90,"endNum":0,"id":1,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"苹果小黄人","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/1.png","packageName":"1","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004151","UPC":"860511000285","createTime":1534490972000,"displayOrder":100,"endNum":0,"id":2,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"苹果版升级包","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/2.png","packageName":"2","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"","UPC":"","createTime":1534497784000,"displayOrder":105,"endNum":0,"id":20,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"二舵机系列","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/twoservos.png","packageName":"two servos","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705005240","UPC":"","createTime":1545204437000,"displayOrder":110,"endNum":0,"id":1008,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"探索者升级版-中国","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/1545204499476.png","packageName":"tansuozhe_pro_CN","packageType":"model","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705000412","UPC":"","createTime":1534501801000,"displayOrder":120,"endNum":0,"id":12,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"发明家-中国","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/12.png","packageName":"12","packageType":"model","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004861,6931705004229","UPC":"868053000268","createTime":1534496815000,"displayOrder":130,"endNum":0,"id":16,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"牧羊人","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/muyangren.png","packageName":"muyangren","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004908","UPC":"","createTime":1534444016000,"displayOrder":130,"endNum":0,"id":31,"isDefault":0,"isDeleted":0,"isLatest":"1","packageDiscription":"牧羊人中国版","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/Shepherd-CN.png","packageName":"Shepherd-CN","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004267","UPC":"","createTime":1534492595000,"displayOrder":140,"endNum":0,"id":18,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"草原漫步者-中国","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/caoyuanmanbuzhe_zh.png","packageName":"caoyuanmanbuzhe_zh","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004212","UPC":"865507000317","createTime":1534509255000,"displayOrder":150,"endNum":0,"id":22,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"丛林飞车","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/carbot.png","packageName":"carbot","packageType":"model","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004281,6931705004298,6931705004311,6931705004304","UPC":"863211000333,863211000319,863211000302,863211000326,858119007065","createTime":1534487567000,"displayOrder":160,"endNum":0,"id":23,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"Tankbot 包装","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/tankbot.png","packageName":"Tankbot","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004403,6931705004410,6931705004427,6931705004434","UPC":"863211000364,863211000371,863211000357,863211000388","createTime":1534474451000,"displayOrder":170,"endNum":0,"id":24,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"Lionbot 包装","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/lionbot.png","packageName":"Lionbot","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004106","UPC":"","createTime":1534491656000,"displayOrder":180,"endNum":0,"id":3,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"莱昂纳多","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/3.png","packageName":"3","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004120","UPC":"","createTime":1534491621000,"displayOrder":180,"endNum":0,"id":4,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"拉斐尔","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/4.png","packageName":"4","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004113","UPC":"","createTime":1534491580000,"displayOrder":180,"endNum":0,"id":5,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"米开朗基罗","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/5.png","packageName":"5","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""},{"EAN":"6931705004137","UPC":"","createTime":1534501814000,"displayOrder":180,"endNum":0,"id":6,"isDefault":0,"isDeleted":0,"isLatest":"0","packageDiscription":"多纳泰罗","packageImage":"https://ubtech.oss-cn-shenzhen.aliyuncs.com/jimu/test/packages/cover/6.png","packageName":"6","pageNum":0,"pageSize":0,"serviceVersionCode":0,"startNum":0,"totalNum":0,"videoThumbnail":"","videoUrl":""}]
     * status : true
     */

    private String info;
    private boolean status;
    private List<Package> models;

    public PackageListBean() {
    }

    public PackageListBean(String info, boolean status, List<Package> models) {
        this.info = info;
        this.status = status;
        this.models = models;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Package> getModels() {
        return models;
    }

    public void setModels(List<Package> models) {
        this.models = models;
    }


}
