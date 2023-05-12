package com.cetc7.remotecontrol;

import com.cetc7.remotecontrol.bean.GroupInfo;
import com.cetc7.remotecontrol.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GloableParams {

	// 屏幕高度 宽度
	public static int WIN_WIDTH;
	public static int WIN_HEIGHT;
	public static Map<String, User> Users = new HashMap<String, User>();
	public static List<User> UserInfos = new ArrayList<User>();// 好友信息
	public static List<GroupInfo> ListGroupInfos = new ArrayList<GroupInfo>();// 群聊信息
	public static Map<String, GroupInfo> GroupInfos = new HashMap<String, GroupInfo>();
	public static Boolean isHasPulicMsg = false;

	public static final byte ProtocolFrame_Head = (byte) 0x7e;
	public static final byte ProtocolFrame_Tail = (byte) 0x7e;
	//设备类型
	public static final short DeviceType_MainContol = 0x01A1;
	public static final short DeviceType_ProtocolHandle = 0x01A2;
	public static final short DeviceType_Modem = 0x01A3;
	public static final short DeviceType_IpRoute = 0x01A4;
	public static final short DeviceType_SofteUpgrade = 0x01A8;
	//模式识别
	public static final short BroadbandMode_Signaling = 0x0001;
	public static final short BroadbandMode_TDMA = 0x0002;
	public static final short BroadbandMode_FDMA = 0x0003;
	public static final short BroadbandMode_CDMA = 0x0004;
	public static final short BroadbandMode_Antirotor = 0x0005;
	public static final short BroadbandMode_Feed = 0x0006;
	public static final short BroadbandMode_TDMA_Antijam = 0x0007;
	public static final short BroadbandMode_FDMA_Antijam = 0x0008;
	public static final short AntijamMode_Low = 0x0100;
	public static final short AntijamMode_Medium = 0x0101;
	public static final short AntijamMode_High = 0x0102;
	//命令字
	public static final byte Command_Set = (byte) 0x80;
	public static final byte Command_SetResponse = (byte) 0x81;
	public static final byte Command_Query = (byte) 0x82;
	public static final byte Command_QueryResponse = (byte) 0x83;
	public static final byte Command_Report = (byte) 0x84;
	//关键字
	public static final byte Key_Protocol_Location_Set = (byte) 0x01;
	public static final byte Key_Protocol_WorkMode_Set = (byte) 0x02;
	public static final byte Key_Protocol_IpAddr_Set = (byte) 0x03;
	public static final byte Key_Protocol_MacAddr_Set = (byte) 0x04;
	public static final byte Key_Protocol_ChannelUnit_Set = (byte) 0x05;
	public static final byte Key_Protocol_TestSwitch_Set = (byte) 0x06;
	public static final byte Key_Protocol_AccessControl_Query = (byte) 0x01;
	public static final byte Key_Protocol_Location_Query = (byte) 0x02;

	public static final byte Key_Protocol_BroadMod_Report = (byte) 0x01;
	public static final byte Key_Protocol_BroadDem_Report = (byte) 0x02;
	public static final byte Key_Protocol_AntiMod_Report = (byte) 0x03;
	public static final byte Key_Protocol_AntidDem_Report = (byte) 0x04;
	public static final byte Key_Protocol_NetControl_Report = (byte) 0x05;

	public static final byte Key_Test_BroadModSet = (byte) 0x01;
	public static final byte Key_Test_BroadDemSet = (byte) 0x02;
	public static final byte Key_Test_AntiModSet = (byte) 0x03;
	public static final byte Key_Test_AntiDemSet = (byte) 0x04;

	//参数体
	public static final byte Param_Protocol_TestSwitch_Open = (byte) 0x00;
	public static final byte Param_Protocol_TestSwitch_Close = (byte) 0x01;
	public static final byte Param_SetResponse_Failed = (byte) 0x00;
	public static final byte Param_SetResponse_Success = (byte) 0x01;

	public static final ArrayList<String> ListString_Signal_Mode = new ArrayList<String>(Arrays.asList(
			"信令模式","TDMA模式", "FDMA模式","CDMA模式","抗旋翼模式","馈电模式","TDMA抗干扰模式","FDMA抗干扰模式",
			"低速抗干扰", "中速抗干扰","高速抗干扰"));
	public static final ArrayList<String> ListString_Test_modset_workmode = new ArrayList<String>(Arrays.asList(
			"信令模式","TDMA模式", "FDMA模式","CDMA模式","抗旋翼模式","馈电模式"));
	public static final ArrayList<String> ListString_Test_demset_workmode = new ArrayList<String>(Arrays.asList(
			"LDR模式","MDR模式", "HDR模式"));
	public static final ArrayList<String> ListString_Test_antimodset_workmode = new ArrayList<String>(Arrays.asList(
			"信令模式","VTDM模式", "抗旋翼模式","抗干扰模式","馈电模式"));
	public static final ArrayList<String> ListString_Test_antidemset_workmode = new ArrayList<String>(Arrays.asList(
			"SDR模式"));
	public static final ArrayList<String> ListString_Test_demset_recvrate = new ArrayList<String>(Arrays.asList(
			"2.4kbps(24Mcps,UQPSK,Turbo-1/4-2208,2048,信令)","9.6kbps(24Mcps,UQPSK,Turbo-1/4-2208,512,信令)",
			"38.4kbps(24Mcps,UQPSK,Turbo-1/4-2208,128,信令)","153.6kbps(24Mcps,UQPSK,Turbo-1/4-2208,32,信令)",
			"304Mbps(96Mcps,16APSK,LDPC-7/8-8064,1,VTDM)","261Mbps(96Mcps,16APSK,LDPC-3/4-8064,1,VTDM)",
			"228Mbps(96Mcps,8PSK,LDPC-7/8-8064,1,VTDM)","195Mbps(96Msps,8PSK,LDPC-3/4-8064,1,VTDM)",
			"152Mbps(96Msps,QPSK,LDPC-7/8-8064,1,VTDM)","130Mbps(96Msps,QPSK,LDPC-3/4-8064,1,VTDM)",
			"87Mbps(96Msps,QPSK,LDPC-1/2-8064,1,VTDM)","76Mbps(48Msps,QPSK,LDPC-7/8-8064,1,VTDM)",
			"65Mbps(48Msps,QPSK,LDPC-3/4-8064,1,VTDM)","43Mbps(48Msps,QPSK,LDPC-1/2-8064,1,VTDM)",
			"38Mbps(24Msps,QPSK,LDPC-7/8-8064,1,VTDM)","32Mbps(24Msps,QPSK,LDPC-3/4-8064,1,VTDM)",
			"21Mbps(24Msps,QPSK,LDPC-1/2-8064,1,VTDM)","19Mbps(12Msps,QPSK,LDPC-7/8-8064,1,VTDM)",
			"16Mbps(12Msps,QPSK,LDPC-3/4-8064,1,VTDM)","10Mbps(12Msps,QPSK,LDPC-1/2-8064,1,VTDM)",
			"5Mbps(12Msps,QPSK,LDPC-7/8-8064,1,VTDM)", "2Mbps(12Mcps,QPSK,LDPC-3/4-8064,1,VTDM)",
			"1Mbps(12Mcps,QPSK,LDPC-1/2-8064,1,VTDM)","9Mbps(96Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)",
			"4Mbps(48Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)","2Mbps(24Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)",
			"1Mbps(12Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)","500kbps(12Mcps,QPSK,LDPC-1/4-4032,2,抗旋翼)",
			"250kbps(12Mcps,QPSK,LDPC-1/4-4032,4,抗旋翼)","125kbps(12Mcps,QPSK,LDPC-1/4-4032,8,抗旋翼)",
			"256kbps(96Mcps,BPSK,LDPC-1/2-2016,2560,抗干扰)","128kbps(96Mcps,BPSK,LDPC-1/2-2016,1280,抗干扰)",
			"64kbps(96Mcps,BPSK,LDPC-1/2-2016,640,抗干扰)","32kbps(96Mcps,BPSK,LDPC-1/2-2016,320,抗干扰)",
			"16kbps(96Mcps,BPSK,LDPC-1/2-2016,160,抗干扰)","256kbps(48Mcps,BPSK,LDPC-1/2-2016,1280,抗干扰)",
			"128kbps(48Mcps,BPSK,LDPC-1/2-2016,640,抗干扰)","64kbps(48Mcps,BPSK,LDPC-1/2-2016,320,抗干扰)",
			"32kbps(48Mcps,BPSK,LDPC-1/2-2016,160,抗干扰)","16kbps(48Mcps,BPSK,LDPC-1/2-2016,80,抗干扰)",
			"659Mbps(192Mcps,16APSK,LDPC-7/8-8064,1,馈电)","565Mbps(192Mcps,16APSK,LDPC-3/4-8064,1,馈电)",
			"494Mbps(192Mcps,8PSK,LDPC-7/8-8064,1,馈电)","424Mbps(192Mcps,8PSK,LDPC-3/4-8064,1,馈电)",
			"329Mbps(192Mcps,QPSK,LDPC-7/8-8064,1,馈电)","282Mbps(192Mcps,QPSK,LDPC-3/4-8064,1,馈电)",
			"188Mbps(192Mcps,QPSK,LDPC-1/2-8064,1,馈电)","94Mbps(192Mcps,QPSK,LDPC-1/2-8064,2,馈电)",
			"47Mbps(192Mcps,QPSK,LDPC-1/2-8064,4,馈电)","23Mbps(192Mcps,QPSK,LDPC-1/2-8064,8,馈电)",
			"300Mbps(96Mcps,16APSK,LDPC-7/8-8064,1,馈电)","282Mbps(96Mcps,16APSK,LDPC-3/4-8064,1,馈电)",
			"247Mbps(96Mcps,8PSK,LDPC-7/8-8064,1,馈电)","212Mbps(96Mcps,8PSK,LDPC-3/4-8064,1,馈电)",
			"164Mbps(96Mcps,QPSK,LDPC-7/8-8064,1,馈电)","141Mbps(96Mcps,QPSK,LDPC-3/4-8064,1,馈电)",
			"94Mbps(96Mcps,QPSK,LDPC-1/2-8064,1,馈电)","47Mbps(96Mcps,QPSK,LDPC-1/2-8064,2,馈电)",
			"23Mbps(96Mcps,QPSK,LDPC-1/2-8064,4,馈电)","11Mbps(96Mcps,QPSK,LDPC-1/2-8064,8,馈电)",
			"141Mbps(48Mcps,16APSK,LDPC-3/4-8064,1,馈电)","123Mbps(48Mcps,8PSK,LDPC-7/8-8064,1,馈电)",
			"106Mbps(48Mcps,8PSK,LDPC-3/4-8064,1,馈电)","82Mbps(48Mcps,QPSK,LDPC-7/8-8064,1,馈电)",
			"70Mbps(48Mcps,QPSK,LDPC-3/4-8064,1,馈电)","47Mbps(48Mcps,QPSK,LDPC-1/2-8064,1,馈电)",
			"23Mbps(48Mcps,QPSK,LDPC-1/2-8064,2,馈电)","11Mbps(48Mcps,QPSK,LDPC-1/2-8064,4,馈电)",
			"5Mbps(48Mcps,QPSK,LDPC-1/2-8064,8,馈电)"));

	public static final ArrayList<String> ListString_Test_modset_sendrate =  new ArrayList<String>(Arrays.asList(
			"2.4kbps(16Mcps,BPSK,LDPC-1/4-4032,1280,信令)","2.4kbps(8Mcps,BPSK,LDPC-1/4-4032,640-1,信令)",
			"2.4kbps(8Mcps,BPSK,LDPC-1/4-4032,640-2,信令)","9.6kbps(8Mcps,BPSK,LDPC-1/4-4032,160-1,信令)",
			"9.6kbps(8Mcps,BPSK,LDPC-1/4-4032,160-2,信令)","38.4kbps(8Mcps,BPSK,LDPC-1/4-4032,40-1,信令)",
			"38.4kbps(8Mcps,BPSK,LDPC-1/4-4032,40-2,信令)","79281kbps(32Msps,8PSK,LDPC-7/8-8064,1,TDMA)",
			"67955kbps(32Msps,8PSK,LDPC-3/4-8064,1,TDMA)","52854kbps(32Msps,QPSK,LDPC-7/8-8064,1,TDMA)",
			"45303kbps(32Msps,QPSK,LDPC-3/4-8064,1,TDMA)","30202kbps(32Msps,QPSK,LDPC-1/2-8064,1,TDMA)",
			"15101kbps(32Msps,QPSK,LDPC-1/2-8064,2,TDMA)","6040kbps(32Msps,QPSK,LDPC-1/2-8064,4,TDMA)",
			"15856kbps(8Msps,8PSK,LDPC-7/8-8064,1,TDMA)","13591kbps(8Msps,8PSK,LDPC-3/4-8064,1,TDMA)",
			"13213kbps(8Msps,QPSK,LDPC-7/8-8064,1,TDMA)","11326kbps(8Msps,QPSK,LDPC-3/4-8064,1,TDMA)",
			"7551kbps(8Msps,QPSK,LDPC-1/2-8064,1,TDMA)","3020kbps(8Msps,QPSK,LDPC-1/2-8064,2,TDMA)",
			"1510kbps(8Msps,QPSK,LDPC-1/2-8064,4,TDMA)","4955kbps(2Msps,8PSK,LDPC-7/8-8064,1,TDMA)",
			"4247kbps(2Msps,8PSK,LDPC-3/4-8064,1,TDMA)","3303kbps(2Msps,QPSK,LDPC-7/8-8064,1,TDMA)",
			"2831kbps(2Msps,QPSK,LDPC-3/4-8064,1,TDMA)","1888kbps(2Msps,QPSK,LDPC-1/2-8064,1,TDMA)",
			"755kbps(2Msps,QPSK,LDPC-1/2-8064,2,TDMA)","378kbps(2Msps,QPSK,LDPC-1/2-8064,4,TDMA)",
			"991kbps(500ksps,8PSK,LDPC-7/8-8064,1,TDMA)","849kbps(500ksps,8PSK,LDPC-3/4-8064,1,TDMA)",
			"661kbps(500ksps,QPSK,LDPC-7/8-8064,1,TDMA)","566kbps(500ksps,QPSK,LDPC-3/4-8064,1,TDMA)",
			"378kbps(500ksps,QPSK,LDPC-1/2-8064,1,TDMA)","189kbps(500ksps,QPSK,LDPC-1/2-8064,2,TDMA)",
			"94kbps(500ksps,QPSK,LDPC-1/2-8064,4,TDMA)","300Mbps(96Msps,16APSK,LDPC-7/8-8064,1,FDMA)",
			"282Mbps(96Msps,16APSK,LDPC-3/4-8064,1,FDMA)","247Mbps(96Msps,8PSK,LDPC-7/8-8064,1,FDMA)",
			"212Mbps(96Msps,8PSK,LDPC-3/4-8064,1,FDMA)","164Mbps(96Msps,QPSK,LDPC-7/8-8064,1,FDMA)",
			"141Mbps(96Msps,QPSK,LDPC-3/4-8064,1,FDMA)","94Mbps(96Msps,QPSK,LDPC-1/2-8064,1,FDMA)",
			"47Mbps(96Msps,QPSK,LDPC-1/2-8064,2,FDMA)","23Mbps(96Msps,QPSK,LDPC-1/2-8064,4,FDMA)",
			"11Mbps(96Msps,QPSK,LDPC-1/2-8064,8,FDMA)","141Mbps(48Msps,16APSK,LDPC-3/4-8064,4,FDMA)",
			"123Mbps(48Msps,8PSK,LDPC-7/8-8064,1,FDMA)","106Mbps(48Msps,8PSK,LDPC-3/4-8064,1,FDMA)",
			"82Mbps(48Msps,QPSK,LDPC-7/8-8064,1,FDMA)","70Mbps(48Msps,QPSK,LDPC-3/4-8064,1,FDMA)",
			"47Mbps(48Msps,QPSK,LDPC-1/2-8064,1,FDMA)","23Mbps(48Msps,QPSK,LDPC-1/2-8064,2,FDMA)",
			"11Mbps(48Msps,QPSK,LDPC-1/2-8064,4,FDMA)","5Mbps(48Msps,QPSK,LDPC-1/2-8064,8,FDMA)",
			"1500kbps(500ksps,16APSK,LDPC-3/4-8064,1,FDMA)","1312kbps(500ksps,8PSK,LDPC-7/8-8064,1,FDMA)",
			"1125kbps(500ksps,8PSK,LDPC-3/4-8064,1,FDMA)","875kbps(500ksps,QPSK,LDPC-7/8-8064,1,FDMA)",
			"750kbps(500ksps,QPSK,LDPC-3/4-8064,1,FDMA)","500kbps(500ksps,QPSK,LDPC-1/2-8064,1,FDMA)",
			"250kbps(500ksps,QPSK,LDPC-1/2-8064,2,FDMA)","125kbps(500ksps,QPSK,LDPC-1/2-8064,4,FDMA)",
			"62kbps(500ksps,QPSK,LDPC-1/2-8064,8,FDMA)","31kbps(500ksps,BPSK,LDPC-1/2-8064,8,FDMA)",
			"256kbps(24Mcps,QPSK,LDPC-1/4-4032,640,CDMA)","128kbps(24Mcps,QPSK,LDPC-1/4-4032,320,CDMA)",
			"64kbps(24Mcps,QPSK,LDPC-1/4-4032,160,CDMA)","32kbps(24Mcps,QPSK,LDPC-1/4-4032,80,CDMA)",
			"16kbps(24Mcps,QPSK,LDPC-1/4-4032,40,CDMA)","8Mbps(16Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)",
			"4Mbps(8Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)","2Mbps(4Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)",
			"1Mbps(2Mcps,QPSK,LDPC-1/4-4032,1,抗旋翼)","500kbps(2Mcps,QPSK,LDPC-1/4-4032,2,抗旋翼)",
			"250kbps(2Mcps,QPSK,LDPC-1/4-4032,4,抗旋翼)","125kbps(2Mcps,QPSK,LDPC-1/4-4032,8,抗旋翼)",
			"62kbps(2Mcps,QPSK,LDPC-1/4-4032,16,抗旋翼)","125kbps(4Mcps,QPSK,LDPC-1/4-4032,16,抗旋翼)",
			"250kbps(8Mcps,QPSK,LDPC-1/4-4032,16,抗旋翼)","500kbps(16Mcps,QPSK,LDPC-1/4-4032,16,抗旋翼)",
			"659Mbps(192Mcps,16APSK,LDPC-7/8-8064,1,馈电)","565Mbps(192Mcps,16APSK,LDPC-3/4-8064,1,馈电)",
			"494Mbps(192Mcps,8PSK,LDPC-7/8-8064,1,馈电)","424Mbps(192Mcps,8PSK,LDPC-3/4-8064,1,馈电)",
			"329Mbps(192Mcps,QPSK,LDPC-7/8-8064,1,馈电)","282Mbps(192Mcps,QPSK,LDPC-3/4-8064,1,馈电)",
			"188Mbps(192Mcps,QPSK,LDPC-1/2-8064,1,馈电)","94Mbps(192Mcps,QPSK,LDPC-1/2-8064,2,馈电)",
			"47Mbps(192Mcps,QPSK,LDPC-1/2-8064,4,馈电)","23Mbps(192Mcps,QPSK,LDPC-1/2-8064,8,馈电)",
			"300Mbps(96Mcps,16APSK,LDPC-7/8-8064,1,馈电)","282Mbps(96Mcps,16APSK,LDPC-3/4-8064,1,馈电)",
			"247Mbps(96Mcps,8PSK,LDPC-7/8-8064,1,馈电)","212Mbps(96Mcps,8PSK,LDPC-3/4-8064,1,馈电)",
			"164Mbps(96Mcps,QPSK,LDPC-7/8-8064,1,馈电)","141Mbps(96Mcps,QPSK,LDPC-3/4-8064,1,馈电)",
			"94Mbps(96Mcps,QPSK,LDPC-1/2-8064,1,馈电)","47Mbps(96Mcps,QPSK,LDPC-1/2-8064,2,馈电)",
			"23Mbps(96Mcps,QPSK,LDPC-1/2-8064,4,馈电)","11Mbps(96Mcps,QPSK,LDPC-1/2-8064,8,馈电)",
			"141Mbps(48Mcps,16APSK,LDPC-3/4-8064,1,馈电)","123Mbps(48Mcps,8PSK,LDPC-7/8-8064,1,馈电)",
			"106Mbps(48Mcps,8PSK,LDPC-3/4-8064,1,馈电)","82Mbps(48Mcps,QPSK,LDPC-7/8-8064,1,馈电)",
			"70Mbps(48Mcps,QPSK,LDPC-3/4-8064,1,馈电)","47Mbps(48Mcps,QPSK,LDPC-1/2-8064,1,馈电)",
			"23Mbps(48Mcps,QPSK,LDPC-1/2-8064,2,馈电)","11Mbps(48Mcps,QPSK,LDPC-1/2-8064,4,馈电)",
			"5Mbps(48Mcps,QPSK,LDPC-1/2-8064,8,馈电)"));
	public static final ArrayList<String> ListString_Test_antimodset_rate =  new ArrayList<String>(Arrays.asList(
			"9.6k(4FSK,LDPC-1/2-768,LDR)","9.6k(4FSK,LDPC-1/4-768,LDR)",
			"4.8k(4FSK,LDPC-1/2-768,LDR)","4.8k(4FSK,LDPC-1/4-768,LDR)",
			"2.4k(4FSK,LDPC-1/2-768,LDR)","2.4k(4FSK,LDPC-1/4-768,LDR)",
			"1.2k(2FSK,LDPC-1/2-768,LDR)","1.2k(2FSK,LDPC-1/4-768,LDR)",
			"600(2FSK,LDPC-1/2-768,LDR)","600(2FSK,LDPC-1/4-768,LDR)",
			"300(2FSK,LDPC-1/2-768,LDR)","300(2FSK,LDPC-1/4-768,LDR)",
			"150(2FSK,LDPC-1/2-768,LDR)","150(2FSK,LDPC-1/4-768,LDR)",
			"75(2FSK,LDPC-1/2-768,LDR)","75(2FSK,LDPC-1/4-768,LDR)",
			"9.6k(4FSK,卷积-1/2-768,LDR)","4.8k(4FSK,卷积-1/2-768,LDR)",
			"2.4k(4FSK,卷积-1/2-768,LDR)","1.2k(2FSK,卷积-1/2-768,LDR)",
			"600(2FSK,卷积-1/2-768,LDR)","2048k(pi/4-DQPSK,LDPC-1/2-5120,MDR)",
			"1024k(pi/4-DQPSK,LDPC-1/4-5120,MDR)","1024k(pi/4-DQPSK,LDPC-1/2-2560,MDR)",
			"512k(pi/4-DQPSK,LDPC-1/4-2560,MDR)","512k(pi/4-DQPSK,LDPC-1/2-1280,MDR)",
			"256k(pi/4-DQPSK,LDPC-1/4-1280,MDR)","256k(pi/4-DQPSK,LDPC-1/2-640,MDR)",
			"128k(pi/4-DQPSK,LDPC-1/4-640,MDR)","64k(pi/4-DQPSK,LDPC-1/4-320,MDR)",
			"8192k(pi/4-DQPSK,LDPC-1/2-5120,HDR)","4096k(pi/4-DQPSK,LDPC-1/4-5120,HDR)"));
	public static final ArrayList<String> ListString_Test_antidemset_rate = new ArrayList<String>(Arrays.asList(
			"73.728M(8PSK,3/4-5120)","49.125M(QPSK,3/4-5120)","32.768M(QPSK,1/2-5120)",
			"16.384M(QPSK,1/4-5120)","8.192M(BPSK,1/4-5120)","4.092M(BPSK,1/4-5120)",
			"2048K(BPSK,1/4-5120)","1024K(BPSK,1/4-5120)","2048K(pi/4-DQPSK,1/2-5120)",
			"1024K(pi/4-DQPSK,1/2-2560)","512K(pi/4-DQPSK,1/4-2560)","512K(pi/4-DQPSK,1/2-1280)",
			"256K(pi/4-DQPSK,1/4-1280)","256K(pi/4-DQPSK,1/2-640)","128K(pi/4-DQPSK,1/4-640)"
			));
	public static int selectPosition = -1;

}
