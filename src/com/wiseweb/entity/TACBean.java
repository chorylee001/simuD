package com.wiseweb.entity;

/**
 * Created by Chory on 2017/2/10 0010.
 * TAC码实体类
 */
public class TACBean {

    private Integer id;
    private String tacCode;             //TAC码
    private String brandEn;             //手机品牌 en
    private String brandZh;             //手机品牌 zh
    private String model;               //型号
    private float weight;               //权重值

    public TACBean() {
    }

    public TACBean(Integer id, String tacCode, String brandEn, String model) {
        this.id = id;
        this.tacCode = tacCode;
        this.brandEn = brandEn;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTacCode() {
        return tacCode;
    }

    public void setTacCode(String tacCode) {
        this.tacCode = tacCode;
    }

    public String getBrandEn() {
        return brandEn;
    }

    public void setBrandEn(String brandEn) {
        this.brandEn = brandEn;
    }

    public String getBrandZh() {
        return brandZh;
    }

    public void setBrandZh(String brandZh) {
        this.brandZh = brandZh;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public static final String[] tacCodes = {
            "010023",
            "01030800",
            "01034900",
            "01066000",
            "01066100",
            "01139900",
            "01150100",
            "01156500",
            "01227900",
            "01228700",
            "01229500",
            "01231300",
            "01237100",
            "01260400",
            "01279000",
            "01281100",
            "01320500",
            "1242200",
            "1294000",
            "1334500",
            "01311900",
            "01322000",
            "01325100",
            "01328400",
            "10196000",
            "01330600",
            "330031",
            "35152306",
            "35167503",
            "35191600",
            "35201505",
            "86458702",
            "86667402",
            "86777002",
            "86803301",
            "86831300",
            "86963001",
            "35513804",
            "35313702",
            "01139900",
            "35889100",
            "86362501",
            "35723801",
            "35284904",
            "1022200",
            "1030500",
            "1033900",
            "1034500",
            "1034600",
            "1037900",
            "1042100",
            "1054000",
            "1062800",
            "1074200",
            "1085100",
            "01233600",
            "01233700",
            "01233800",
            "01241700",
            "01242000",
            "01243000",
            "01253600",
            "01254200",
            "01300600",
            "01326300",
            "01332700",
            "01388300",
            "35875105",
            "35875305",
            "35875205",
            "35875505",
            "35875605",
            "35875705",
            "35875405",
            "35875805",
            "35875905",
            "35876005",
            "35880005",
            "35880105",
            "35880205",
            "35880305",
            "35880405",
            "35880505",
            "35880605",
            "35880705",
            "35880805",
            "35880905",
            "35881005",
            "35881105",
            "35881205",
            "35881305",
            "35881405",
            "35881505",
            "35881605",
            "35881705",
            "35881805",
            "35881905",
            "35201906",
            "35925406",
            "35438506",
            "35325807",
            "350151",
            "35089080",
            "35099480",
            "35148420",
            "35148820",
            "35151304",
            "35154900",
            "35171005",
            "35174605",
            "35191405",
            "35226005",
            "35238402",
            "35274901",
            "35291402",
            "35316004",
            "35316605",
            "35332705",
            "35328504",
            "32930400",
            "35351200",
            "35357800",
            "35376800",
            "35391805",
            "35421803",
            "35433004",
            "35511405",
            "35524803",
            "35566600",
            "35569500",
            "35679404",
            "35685702",
            "35693803",
            "35694603",
            "35699601",
            "35700804",
            "35714904",
            "35733104",
            "35739804",
            "35744105",
            "35765206",
            "35788104",
            "35803106",
            "35824005",
            "35828103",
            "35836800",
            "35837800",
            "35838706",
            "35850000",
            "35851004",
            "35853704",
            "35869205",
            "35876105",
            "35896704",
            "35902803",
            "35909205",
            "35918804",
            "35920605",
            "35929005",
            "35935003",
            "35979504",
            "449337",
            "86723902",
            "01333200",
            "35808005",
            "35664906"};

    public static final String[] brandsEn = {
            "Mitsubishi",
            "NEC",
            "Siemens",
            "Sharp",
            "Sanyo",
            "Huawei",
            "Qualcomm",
            "Ericsson",
            "Sony Ericsson",
            "Dell",
            "ZTE",
            "Sony Ericsson",
            "Motorola",
            "HP",
            "Sony Ericsson",
            "Sony",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Nokia",
            "Intel",
            "Alcatel",
            "Samsung",
            "Samsung",
            "LG",
            "Acer",
            "HTC",
            "Philips",
            "Lenovo",
            "BlackBerry",
            "OnePlus",
            "LETV",
            "Vivo",
            "Meizu",
            "Phillips",
            "Xiaomi",
            "OPPO",
            "GiONEE",
            "Huawei",
            "TCL Mobile",
            "CoolPAD",
            "K-Touch",
            "Haier",
            "LG",
            "LG",
            "Nokia",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Sony Ericsson",
            "Google",
            "Motorola",
            "Samsung",
            "Sony Ericsson",
            "Nokia",
            "Nokia",
            "ZTE",
            "Samsung",
            "Samsung",
            "Samsung",
            "Samsung",
            "Motorola",
            "Samsung",
            "Nokia",
            "Google",
            "Nokia",
            "Nokia",
            "Sony Ericsson",
            "Nokia",
            "Nokia",
            "Nokia",
            "Samsung",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Nokia",
            "Huawei",
            "Samsung",
            "Nokia",
            "Samsung",
            "Sony",
            "Nokia",
            "HTC",
            "Google",
            "Nokia",
            "Nokia",
            "Nokia",
            "LG",
            "Nokia",
            "Sony Ericsson",
            "Samsung",
            "Apple",
            "Apple",
            "HTC",
            "HTC",
            "Samsung",
            "HTC",
            "Nokia",
            "Motorola",
            "Nokia",
            "Samsung",
            "Nokia",
            "ZTE",
            "Apple",
            "Sony",
            "Samsung"};
    public static final String[] brandsZh = {
            "三菱",
            "NEC",
            "西门子",
            "夏普",
            "三洋",
            "华为",
            "高通",
            "爱立信",
            "索尼爱立信 ",
            "戴尔",
            "中兴",
            "索尼爱立信",
            "摩托罗拉",
            "惠普",
            "索尼爱立信",
            "索尼",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "诺基亚",
            "因特尔",
            "阿尔卡特",
            "三星",
            "三星",
            "LG",
            "宏基",
            "HTC",
            "飞利浦",
            "联想",
            "黑莓",
            "一加",
            "乐视",
            "Vivo",
            "魅族",
            "飞利浦",
            "小米",
            "OPPO",
            "金立",
            "华为",
            "TCL",
            "酷派",
            "天语",
            "海尔",
            "LG",
            "LG",
            "诺基亚",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "LG",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "苹果",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "索尼爱立信",
            " 谷歌",
            "摩托罗拉",
            "三星",
            "索尼爱立信",
            "诺基亚",
            "诺基亚",
            "中兴",
            "三星",
            "三星",
            "三星",
            "三星",
            "摩托罗拉",
            "三星",
            " 诺基亚",
            "谷歌",
            "诺基亚",
            "诺基亚",
            "索尼爱立信",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "三星",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "华为",
            "三星",
            " 诺基亚",
            "三星",
            "索尼",
            "诺基亚",
            "华为",
            "谷歌",
            "诺基亚",
            "诺基亚",
            "诺基亚",
            "LG",
            "诺基亚",
            "索尼爱立信",
            "三星",
            "苹果",
            "苹果",
            "HTC",
            "HTC",
            "三星",
            "HTC",
            "诺基亚",
            "摩托罗拉",
            "诺基亚",
            "三星",
            "诺基亚",
            "中兴",
            "苹果",
            "索尼",
            "三星"};
    public static final String[] models = {
            "G100e",
            "A232A",
            "A56",
            "V100",
            "SIDEKICK 2",
            "T201",
            "12",
            "F3507g",
            "SO003",
            "Venue Pro",
            "R225",
            "E10a Xperia X10 mini",
            "WX295",
            "Veer 4G",
            "MK16a Xperia pro",
            "XP5300",
            "iPhone 4S",
            "iPhone 4",
            "iPhone 4s",
            "iPhone 5",
            "C2-02",
            "AZ210",
            "OT-903 / 903D",
            "GT-E1086i",
            "SGH-S300",
            "P769 Optimus L9",
            "G201",
            "One Dual Sim",
            "Xenium X530",
            "G900",
            "9650",
            "One",
            "Le 1S (ECO)",
            "Y15S",
            "MX2",
            "X623",
            "M2",
            "R831K",
            "V5500",
            "T201",
            "M530",
            "7728",
            "A903",
            "H-A55W",
            "C1400",
            "G4011",
            "N-Gage QD",
            "C1200",
            "C1300",
            "L1100",
            "G4020",
            "F9100",
            "LG-2000",
            "MG200",
            "CU500",
            "iPhone 4	MC608LL",
            "iPhone 4	MC603B",
            "iPhone 4	MC610LL",
            "iPhone 4",
            "iPhone 4",
            "iPhone 4	MC603KS",
            "iPhone 4	MC610LL/A",
            "iPhone 4",
            "iPhone 4S	MD260C",
            "iPhone 4	MD198HN/A",
            "iPhone 5	MD642C",
            "iPhone 5S	ME297C/A",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1533",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5C	A1507",
            "iPhone 5C	A1507",
            "iPhone 5C	A1507",
            "iPhone 5C	A1507",
            "iPhone 5C	A1507",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1453",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5S	A1533",
            "iPhone 5C	A1456",
            "iPhone 5C	A1456",
            "iPhone 5C	A1456",
            "iPhone 5C	A1456",
            "iPhone 5C	A1456",
            "iPhone 5S	A1457",
            "iPhone 6	A1549",
            "iPhone 6+	A1522",
            "iPhone 6s	A1633",
            "3330",
            "3410	NHM-2NX",
            "3410	NHM-2NX",
            "3410	NHM-2NX",
            "6310i	NPL-1",
            "E72-1	RM-530",
            "6310i	NPL-1",
            "Xperia S",
            "Galaxy Nexus	Samsung GT-I9250, Samsung GT-I9250TSGGEN",
            "Defy Mini",
            "Galaxy SIII",
            "K770i",
            "6233",
            "6210 Navigator",
            "Blade",
            "Galaxy S3	GT-I9300",
            "Galaxy SII	GT-I9100",
            "Galaxy S	GT-I9000",
            "Galaxy S7",
            "V300",
            "GSGH-A800",
            "6230",
            "Nexus 4	LG E960",
            "5310	RM-303",
            "C5-00	RM-645",
            "Xperia U",
            "2330C-2	RM-512",
            "6230",
            "1100",
            "Galaxy Mini	GT-S5570",
            "6300",
            "N900",
            "2700",
            "N95",
            "C1",
            "E398U-15 LTE Stick",
            "Galaxy Gio",
            "N8",
            "Galaxy S4	GT-I9505",
            "Xperia Z3 Compact	D5803",
            "N950",
            "HTC One M8s",
            "Nexus 5	LG D820/D821",
            "6303C",
            "6230i",
            "N6030	RM-74",
            "G Stylo	LG-H631",
            "Lumia 720",
            "Xperia Active",
            "Galaxy SII",
            "iPhone 5S	MF353TA/A",
            "iPhone 5S	A1457",
            "Desire S",
            "Wildfire",
            "Galaxy Note III	SM-N9000, SM-N9005, SM-N900",
            "One X",
            "Lumia 625",
            "Moto G	XT1039",
            "2720A-2	RM-519",
            "Galaxy Note",
            "6210",
            "Corporation	Rook from EE, Orange Dive 30, Blade A410",
            "iPhone 5",
            "SONY C6833 - XPERIA Z ULTRA",
            "Xcover 271"};
}
