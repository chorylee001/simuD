package com.wiseweb.util;

/**
 * Created by Chory on 2017/2/10 0010.
 */
public class IMEISimple {

    /**
     * 010023   Mitsubishi      三菱       G100e
     * 01030800 NEC             NEC        A232A
     * 01034900 Siemens         西门子     A56
     * 01066000 Sharp           夏普       V100
     * 01066100 Sanyo           三洋       SIDEKICK 2
     * 01139900 Huawei          华为       T201
     * 01150100 Qualcomm        高通       12
     * 01156500 Ericsson        爱立信     F3507g
     * 01227900 SonyEricsson    索尼爱立信 SO003
     * 01228700 Dell            戴尔       Venue Pro
     * 01229500 ZTE             中兴       R225
     * 01231300 Soney Ericsson  索尼爱立信 E10a Xperia X10 mini
     * 01237100 Motorola        摩托罗拉   WX295
     * 01260400 HP              惠普       Veer 4G
     * 01279000 Sony Ericsson   索尼爱立信 MK16a Xperia pro
     * 01281100 Sony            索尼       XP5300
     * 01320500 Apple           苹果       iPhone 4S
     * 1242200	Apple	        苹果       iPhone 4
     * 1294000	Apple	        苹果       iPhone 4s
     * 1334500	Apple	        苹果       iPhone 5
     * 01311900 Nokia           诺基亚     C2-02
     * 01322000 Intel           因特尔     AZ210
     * 01325100 Alcatel         阿尔卡特   OT-903 / 903D
     * 01328400 Samsung         三星       GT-E1086i
     * 10196000	Samsung	        三星       SGH-S300
     * 01330600 LG              LG         P769 Optimus L9
     * 330031   Acer            宏基       G201
     * 35152306 HTC             HTC        One Dual Sim
     * 35167503 Philips         飞利浦     Xenium X530
     * 35191600 Lenovo          联想       G900
     * 35201505 BlackBerry      黑莓       9650
     * 86458702 OnePlus         一加       One
     * 86667402 LETV            乐视       Le 1S (ECO)
     * 86777002 Vivo            Vivo       Y15S
     * 86803301 Meizu           魅族       MX2
     * 86831300 Phillips        飞利浦     X623
     * 86963001 Xiaomi          小米       M2
     * 35513804 OPPO            OPPO       R831K
     * 35313702 GiONEE          金立       V5500
     * 01139900 Huawei          华为       T201
     * 35889100 TCL Mobile      TCL        M530
     * 86362501 CoolPAD         酷派       7728
     * 35723801 K-Touch         天语       A903
     * 35284904 Haier           海尔       H-A55W
     * 1022200	LG	            LG         C1400
     * 1030500	LG	            LG         G4011
     * 1033900	Nokia	        诺基亚     N-Gage QD
     * 1034500	LG	            LG         C1200
     * 1034600	LG	            LG         C1300
     * 1037900	LG	            LG         L1100
     * 1042100	LG	            LG         G4020
     * 1054000	LG	            LG         F9100
     * 1062800	LG	            LG         LG-2000
     * 1074200	LG	            LG         MG200
     * 1085100	LG	            LG         CU500
     * 01233600	Apple	        苹果       iPhone 4	MC608LL
     * 01233700	Apple	        苹果       iPhone 4	MC603B
     * 01233800	Apple	        苹果       iPhone 4	MC610LL
     * 01241700	Apple	        苹果       iPhone 4
     * 01242000	Apple	        苹果       iPhone 4
     * 01243000	Apple	        苹果       iPhone 4	MC603KS
     * 01253600	Apple	        苹果       iPhone 4	MC610LL/A
     * 01254200	Apple	        苹果       iPhone 4
     * 01300600	Apple	        苹果       iPhone 4S	MD260C
     * 01326300	Apple	        苹果       iPhone 4	MD198HN/A
     * 01332700	Apple	        苹果       iPhone 5	MD642C
     * 01388300	Apple	        苹果       iPhone 5S	ME297C/A
     * 35875105	Apple	        苹果       iPhone 5S	A1533
     * 35875305	Apple	        苹果       iPhone 5S	A1533
     * 35875205	Apple	        苹果       iPhone 5S	A1533
     * 35875505	Apple	        苹果       iPhone 5S	A1533
     * 35875605	Apple	        苹果       iPhone 5S	A1453
     * 35875705	Apple	        苹果       iPhone 5S	A1453
     * 35875405	Apple	        苹果       iPhone 5S	A1533
     * 35875805	Apple	        苹果       iPhone 5S	A1453
     * 35875905	Apple	        苹果       iPhone 5S	A1453
     * 35876005	Apple	        苹果       iPhone 5S	A1453
     * 35880005	Apple	        苹果       iPhone 5C	A1507
     * 35880105	Apple	        苹果       iPhone 5C	A1507
     * 35880205	Apple	        苹果       iPhone 5C	A1507
     * 35880305	Apple	        苹果       iPhone 5C	A1507
     * 35880405	Apple	        苹果       iPhone 5C	A1507
     * 35880505	Apple	        苹果       iPhone 5S	A1453
     * 35880605	Apple	        苹果       iPhone 5S	A1453
     * 35880705	Apple	        苹果       iPhone 5S	A1453
     * 35880805	Apple	        苹果       iPhone 5S	A1453
     * 35880905	Apple	        苹果       iPhone 5S	A1453
     * 35881005	Apple	        苹果       iPhone 5S	A1533
     * 35881105	Apple	        苹果       iPhone 5S	A1533
     * 35881205	Apple	        苹果       iPhone 5S	A1533
     * 35881305	Apple	        苹果       iPhone 5S	A1533
     * 35881405	Apple	        苹果       iPhone 5S	A1533
     * 35881505	Apple	        苹果       iPhone 5C	A1456
     * 35881605	Apple	        苹果       iPhone 5C	A1456
     * 35881705	Apple	        苹果       iPhone 5C	A1456
     * 35881805	Apple	        苹果       iPhone 5C	A1456
     * 35881905	Apple	        苹果       iPhone 5C	A1456
     * 35201906	Apple	        苹果       iPhone 5S	A1457
     * 35925406	Apple	        苹果       iPhone 6	A1549
     * 35438506	Apple	        苹果       iPhone 6+	A1522
     * 35325807	Apple	        苹果       iPhone 6s	A1633
     * 350151..	Nokia	        诺基亚     3330
     * 35089080	Nokia	        诺基亚     3410	NHM-2NX
     * 35099480	Nokia	        诺基亚     3410	NHM-2NX
     * 35148420	Nokia	        诺基亚     3410	NHM-2NX
     * 35148820	Nokia	        诺基亚     6310i	NPL-1
     * 35151304	Nokia	        诺基亚     E72-1	RM-530
     * 35154900	Nokia	        诺基亚     6310i	NPL-1
     * 35171005	Sony Ericsson	索尼爱立信 Xperia S
     * 35174605	Google	        谷歌       Galaxy Nexus	Samsung GT-I9250, Samsung GT-I9250TSGGEN
     * 35191405	Motorola	    摩托罗拉   Defy Mini
     * 35226005	Samsung	        三星       Galaxy SIII
     * 35238402	Sony Ericsson	索尼爱立信 K770i
     * 35274901	Nokia	        诺基亚     6233
     * 35291402	Nokia	        诺基亚     6210 Navigator
     * 35316004	ZTE	            中兴       Blade
     * 35316605	Samsung		    三星       Galaxy S3	GT-I9300
     * 35332705	Samsung		    三星       Galaxy SII	GT-I9100
     * 35328504	Samsung		    三星       Galaxy S	GT-I9000
     * 32930400	Samsung		    三星       Galaxy S7
     * 35351200	Motorola	    摩托罗拉   V300
     * 35357800	Samsung		    三星       GSGH-A800
     * 35376800	Nokia	        诺基亚     6230
     * 35391805	Google	        谷歌       Nexus 4	LG E960
     * 35421803	Nokia	        诺基亚     5310	RM-303
     * 35433004	Nokia	        诺基亚     C5-00	RM-645
     * 35511405	Sony Ericsson	索尼爱立信 Xperia U
     * 35524803	Nokia	        诺基亚     2330C-2	RM-512
     * 35566600	Nokia	        诺基亚     6230
     * 35569500	Nokia	        诺基亚     1100
     * 35679404	Samsung		    三星       Galaxy Mini	GT-S5570
     * 35685702	Nokia	        诺基亚     6300
     * 35693803	Nokia	        诺基亚     N900
     * 35694603	Nokia	        诺基亚     2700
     * 35699601	Nokia	        诺基亚     N95
     * 35700804	Nokia	        诺基亚     C1
     * 35714904	Huawei	        华为       E398U-15 LTE Stick
     * 35733104	Samsung		    三星       Galaxy Gio
     * 35739804	Nokia	        诺基亚     N8
     * 35744105	Samsung		    三星       Galaxy S4	GT-I9505
     * 35765206	Sony	        索尼       Xperia Z3 Compact	D5803
     * 35788104	Nokia	        诺基亚     N950
     * 35803106	HTC	            华为       HTC One M8s
     * 35824005	Google	        谷歌       Nexus 5	LG D820/D821
     * 35828103	Nokia	        诺基亚     6303C
     * 35836800	Nokia	        诺基亚     6230i
     * 35837800	Nokia	        诺基亚     N6030	RM-74
     * 35838706	LG	            LG         G Stylo	LG-H631
     * 35850000	Nokia	        诺基亚     Lumia 720
     * 35851004	Sony Ericsson	索尼爱立信 Xperia Active
     * 35853704	Samsung		    三星       Galaxy SII
     * 35869205	Apple	        苹果       iPhone 5S	MF353TA/A
     * 35876105	Apple	        苹果       iPhone 5S	A1457
     * 35896704	HTC	            HTC        Desire S
     * 35902803	HTC	            HTC        Wildfire
     * 35909205	Samsung		    三星       Galaxy Note III	SM-N9000, SM-N9005, SM-N900
     * 35918804	HTC	            HTC        One X
     * 35920605	Nokia	        诺基亚     Lumia 625
     * 35929005	Motorola	    摩托罗拉   Moto G	XT1039
     * 35935003	Nokia	        诺基亚     2720A-2	RM-519
     * 35979504	Samsung		    三星       Galaxy Note
     * 449337..	Nokia	        诺基亚     6210
     * 86723902	ZTE             中兴       Corporation	Rook from EE, Orange Dive 30, Blade A410
     * 01333200	Apple	        苹果       iPhone 5
     * 35808005	Sony	        索尼       SONY C6833 - XPERIA Z ULTRA
     * 35664906	Samsung		    三星       Xcover 271
     */
}
