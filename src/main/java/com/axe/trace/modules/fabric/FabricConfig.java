package com.axe.trace.modules.fabric;

import java.io.File;

// fabric证书路径配置
public class FabricConfig {

    public static final String CHANNEL_NAME = "mychannel";

    public static final String ORG1 = "Org1";

    public static final String ORG1_MSP = "Org1MSP";

    public static final String ORG2 = "Org2";

    public static final String ORG2_MSP = "Org2MSP";

    public static final String ORDERER_NAME = "orderer.example.com";

    public static final String ORDERER_URL = "grpcs://orderer.example.com:7050";

    public static final String ORG1_PEER0 = "peer0.org1.example.com";

    public static final String ORG1_PEER0_URL = "grpcs://peer0.org1.example.com:7051";

    public static final String ORG1_PEER1 = "peer1.org1.example.com";

    public static final String ORG1_PEER1_URL = "grpcs://peer1.org1.example.com:8051";

    public static final String ORG2_PEER0 = "peer0.org2.example.com";

    public static final String ORG2_PEER0_URL = "grpcs://peer0.org2.example.com:9051";

    public static final String ORG2_PEER1 = "peer1.org2.example.com";

    public static final String ORG2_PEER1_URL = "grpcs://peer1.org2.example.com:10051";

    public static final String BASE_PATH = "/root/develop/first-network";
    // /home/liyang/develop/gopath/src/github.com/hyperledger/fabric-samples/first-network

    public static final String KEY_FOLDER_PATH1 = BASE_PATH + "/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore";

    public static final String KEY_FILE_NAME1; // 4dc60cf9fd94cce1bc629aacd6f274e39545da9c24060929c3ed0295a25a3239_sk

    public static final String CERT_FOLDER_PATH1 = BASE_PATH + "/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/admincerts";

    public static final String CERT_FILE_NAME1 = "Admin@org1.example.com-cert.pem";

    public static final String KEY_FOLDER_PATH2 = BASE_PATH + "/crypto-config/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp/keystore";

    public static final String KEY_FILE_NAME2; // 464ed95a81e6c4ab45c36f9c7eff939f5babbc8625fd06cd9bdddb4b51d3bf93_sk

    public static final String CERT_FOLDER_PATH2 = BASE_PATH + "/crypto-config/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp/admincerts";

    public static final String CERT_FILE_NAME2 = "Admin@org2.example.com-cert.pem";

    public static final String ORDER_FILE_PATH = BASE_PATH+ "/crypto-config/ordererOrganizations/example.com/tlsca/tlsca.example.com-cert.pem";

    public static final String PEER_FILE_PATH1 = BASE_PATH + "/crypto-config/peerOrganizations/org1.example.com/tlsca/tlsca.org1.example.com-cert.pem";

    public static final String PEER_FILE_PATH2 = BASE_PATH + "/crypto-config/peerOrganizations/org2.example.com/tlsca/tlsca.org2.example.com-cert.pem";

    // 获取指定目录下的证书文件名
    static {
        File file = new File(KEY_FOLDER_PATH1);
        File[] files = file.listFiles();
        KEY_FILE_NAME1 = files[0].getName();

        file = new File(KEY_FOLDER_PATH2);
        files = file.listFiles();
        KEY_FILE_NAME2 = files[0].getName();
    }
}
