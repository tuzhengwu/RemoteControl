package com.cetc7.remotecontrol;

import java.util.Arrays;
import java.util.List;

public interface Constants {

	//参数
	String EarthLogitude = "EarthLogitude";
	String EarthLatitude = "EarthLatitude";
	String SatelliteLogitude = "SatelliteLogitude";
	String SignalMode = "SignalMode";
	String SignalModeStr = "SignalModeStr";
	String ManageNet = "ManageNet";
	String MacAddr = "MacAddr";
	String ChannelUnit = "ChannelUnit";

	String TestModsetWorkmode = "TestModsetWorkmode";
	String TestModsetSendrate = "TestModsetSendrate";
	String TestModsetCenterfreq = "TestModsetCenterfreq";
	String TestModsetOutdbm = "TestModsetOutdbm";
	String TestModsetCheckcarrier = "TestModsetCheckcarrier";
	String TestModsetChecksinglecarrier = "TestModsetChecksinglecarrier";
	String TestModsetCheckmodtest = "TestModsetCheckmodtest";
	String TestModsetCheckspectruminversion = "TestModsetCheckspectruminversion";

	String TestDemsetWorkmode = "TestDemsetWorkmode";
	String TestDemsetRecvrate = "TestDemsetRecvrate";
	String TestDemsetCenterfreq = "TestDemsetCenterfreq";
	String TestDemsetChecktest = "TestDemsetChecktest";
	String TestDemsetCheckerrorzero = "TestDemsetCheckerrorzero";
	String TestDemsetCheckframnumzero = "TestDemsetCheckframnumzero";

	String TestAntiModSetWorkmode = "TestAntiModSetWorkmode";
	String TestAntiModSetRate = "TestAntiModSetRate";
	String TestAntiModSetCenterfreq = "TestAntiModSetCenterfreq";
	String TestAntiModSetOutdbm = "TestAntiModSetOutdbm";
	String TestAntiModSetTimeslot = "TestAntiModSetTimeslot";
	String TestAntiModSetCarrierwave = "TestAntiModSetCarrierwave";
	String TestAntiModsetCheckanticarrier = "TestAntiModsetCheckanticarrier";
	String TestAntiModsetCheckantisinglecarrier = "TestAntiModsetCheckantisinglecarrier";
	String TestAntiModsetCheckantimodtest = "TestAntiModsetCheckantimodtest";
	String TestAntiModsetCheckantispectruminversion = "TestAntiModsetCheckantispectruminversion";

	String TestAntiDemSetWorkmode = "TestAntiDemSetWorkmode";
	String TestAntiDemSetRate = "TestAntiDemSetRate";
	String TestAntiDemSetCenterfreq = "TestAntiDemSetCenterfreq";
	String TestAntiDemSetCheckTest = "TestAntiDemSetCheckTest";
	String TestAntiDemSetCheckErrorzero = "TestAntiDemSetCheckErrorzero";
	String TestAntiDemSetCheckFramnumzero = "TestAntiDemSetCheckFramnumzero";
	// 聊天
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	String isFriend = "isFriend";
	String LoginState = "LoginState";
	String UserInfo = "UserInfo";
	String URL = "URL";
	String Title = "Title";
	String ID = "id";
	String NAME = "NAME";
	String AccessToken = "AccessToken";
	String PWD = "PWD";
	String ContactMsg = "ContactMsg";
	String VersionInfo = "VersionInfo";
	String SeeMe = "SeeMe";
	String LokeMe = "LokeMe";
	String IsMsg = "IsMsg";
	String IsVideo = "IsVideo";
	String IsZhen = "IsZhen";
	String User_ID = "User_ID";
	String User_Phone = "User_Phone";
	String User_Name = "User_Name";
	String GROUP_ID = "GROUP_ID";
	String TYPE = "TYPE";
	// JSON status
	String Info = "info";
	String Value = "data";
	String Result = "status";
	String DB_NAME = "RemoteControl.db";
	String NET_ERROR = "网络错误，请稍后再试！";
	String BaiduPullKey = "Uvw5AMP15i9v1cUoS5aY7GR1";
	// 主机地址
	// public static String IP = "http://wechatjuns.sinaapp.com/";
	// String MAIN_ENGINE = "http://10.16.16.79/wechat/index.php/mobile/";
	String MAIN_ENGINE = "http://wechatjuns.sinaapp.com/index.php/";

	// 发送验证码 codeType 1注册 2修改密码
	String SendCodeURL = "";
	// 用户注册
	String RegistURL = MAIN_ENGINE + "user/regigter";
	// 用户登录
	String Login_URL = MAIN_ENGINE + "user/login";
	// 更新用户信息
	String UpdateInfoURL = MAIN_ENGINE + "user/update_userinfo";
	// 获取用户信息
	String getUserInfoURL = MAIN_ENGINE + "user/get_user_list";
	// 检查版本
	String getVersionURL = MAIN_ENGINE + "020";
	// 更新用户信息
	String updateUserInfoURL = MAIN_ENGINE + "004";
	// 我的群组
	String getGroupListURL = MAIN_ENGINE + "group/get_group_list";
	// 反馈
	String FeedbackURL = MAIN_ENGINE + "019";
	// 获取群组信息
	String getGroupInfoURL = MAIN_ENGINE + "027";

	// 查询所有好友033
	String getContactFriendURL = MAIN_ENGINE + "user/get_contact_list";
	// 举报018
	String JuBaoURL = MAIN_ENGINE + "018";
	// 加入群组
	String addGroupURL = MAIN_ENGINE + "031";
	// 退出群组
	String exitGroupURL = MAIN_ENGINE + "029";
	// 新建群组
	String newGroupURL = MAIN_ENGINE + "group/add_group";

}
