package com.nbiot.telecom.response;


import com.nbiot.telecom.enums.BaseEnum;

/**
 * rest 返回CODE定义
 * Created by wangkun23 on 2018/12/19.
 */
public enum ResponseCode implements BaseEnum {

    /***********************************************************************
     * ERROR
     ***********************************************************************/
    ERROR("1000000", "未知错误"),
    //登录错误
    ERROR_USERNAME_NOT_EXIST("1000001", "用户名不存在"),
    ERROR_USERNAME_LENGTH_OUT("1000002", "用户名过长"),
    ERROR_USERNAME_EXIST("1000003", "用户名已存在"),
    ERROR_USERNAME_PWD_INCORRECT("1000004", "用户名或者密码错误"),
    ERROR_CAPTCHA_NOT_MATCH("1000005", "验证码错误"),
    ERROR_UN_AUTHORIZE_LOGIN("1000006", "非授信登录"),
    //注册错误提示
    ERROR_EMAIL_EXIST("1001000", "该邮箱已注册"),
    ERROR_EMAIL_EMPTY("1001001", "邮箱输入为空"),
    ERROR_EMAIL_FORMAT("1001002", "邮箱地址格式错误"),
    ERROR_EMAIL_NOT_REGISTER("1001003", "邮箱未注册"),
    ERROR_EMAIL_RESET_PASSWORD_SEND("1001004", "密码重置邮件发送失败"),

    ERROR_PASSWORD_EMPTY("1002000", "请输入密码"),
    ERROR_PASSWORD_NOT_MATCH("1002001", "密码不匹配错误"),
    ERROR_PWD_SHORT("1002002", "密码太短"),
    ERROR_PWD_LONG("1002003", "密码太长"),
    ERROR_PWD_RESET_NOT_ALLOWED("1002004", "不允许重置密码"),
    ERROR_PWD_RESET("1002005", "重置密码错误"),
    ERROR_PWD_NOTAGREE("1002006", "修改密码时输入的旧密码与原始密码不符"),

    ERROR_CFMPWD_EMPTY("1003000", "请输入确认密码"),
    ERROR_CFMPWD_NOTAGREE("1003001", "密码输入不一致"),
    ERROR_CFMPWD_SAME("1003002", "新旧密码相同"),

    ERROR_ACCOUNT_ACTIVATION("1004000", "账户激活错误"),
    ERROR_ACCOUNT_ACTIVATION_EXPIRED("1004001", "激活链接过期"),
    ERROR_ACCOUNT_ACTIVATION_NOT_EXIST("1004002", "激活账户不存在"),
    ERROR_ACCOUNT_INACTIVE("1004003", "账户待激活"),
    ERROR_ACCOUNT_LOCK("1004004", "账户已锁定"),
    ERROR_ACCOUNT_CANCELLED("1004005", "账户已注销"),
    ERROR_ACCOUNT_EXIST("1004006", "账户已存在(已激活)"),
    ERROR_ACCOUNT_NOT_LOGIN("1004007", "账户没有登录"),
    ERROR_ACCOUNT_NOT_EXIST("1004008", "账户不存在"),
    ERROR_ACCOUNT_AUTH("1004009", "不合法的凭证类型"),

    ERROR_VALID("1005000", "参数验证错误"),
    ERROR_KEY_SHARE_UN_REVOKE("1005001", "需要先收回钥匙,才能删除"),
    //设备相关
    ERROR_DEVICE_ADMIN("1006001", "设备不属于该管理员"),
    ERROR_DEVICE_NO_GW("1006002", "设备没有绑定网关盒子"),
    ERROR_DEVICE_NOT_EXIST("1006003", "设备不存在"),
    ERROR_DEVICE_MODEL_NOT_EXIST("1006004", "设备型号不存在"),

    ERROR_REQUEST_LIMIT("1009001", "请求次数太频繁"),
    ERROR_NOT_FOUNT("1009002", "记录不存在"),
    ERROR_EXIST("1009003", "该名称已经存在"),


    /***********************************************************************
     * SUCCESS
     ***********************************************************************/
    SUCCESS("200", "操作成功"),

    SUCCESS_EMAIL_RESETPWD_SEND("2001000", "密码重置邮件发送成功"),
    SUCCESS_PWD_FORMAT("2002000", "密码格式正确"),
    SUCCESS_PWD_RESET_ALLOWED("2002001", "密码允许重置"),
    SUCCESS_PWD_RESET("2002002", "密码重置成功"),
    SUCCESS_PWD_CHANGE("2002003", "密码修改成功"),

    SUCCESS_ACCOUNT_REGISTER("2004000", "注册成功"),
    SUCCESS_ACCOUNT_LOGIN("2004001", "登陆成功"),
    SUCCESS_ACCOUNT_ACTIVATION("2004002", "账户激活成功"),
    SUCCESS_ACCOUNT_ACTIVATION_EMAIL_RESEND("2004003", "账户激活邮件重新发送成功"),
    SUCCESS_ACCOUNT_ACTIVATION_KEY_UPD("2004004", "账户激活key更新成功"),
    SUCCESS_ACCOUNT_LOGOUT("2004005", "账户退出系统成功"),


    OTHER("0000000", "默认"),

    /***********************************************************************
     * NB IOT
     ***********************************************************************/
    ERROR_NBIOT_INTERNAL_SERVER_ERROR("100001", "Internal server error."),
    ERROR_NBIOT_INVALID_TOKEN("100002", "Invalid access token."),
    ERROR_NBIOT_INVALID_VERIFY_CODE("100003", "Invalid verify code."),
    ERROR_NBIOT_BAD_REQUEST_MESSAGE("100007", "Bad request message."),
    ERROR_NBIOT_INPUT_INVALID("100022", "The input is invalid."),
    ERROR_NBIOT_APP_ID_NOT_EXISTED("100025", "AppId for auth not exist."),
    ERROR_NBIOT_GET_APP_KEY_FAIL("100220", "Get AppKey from header failed."),
    ERROR_NBIOT_APPLICATION_NOT_EXISTED("100203", "The application is not existed."),
    ERROR_NBIOT_APPLICATION_NOT_AUTHORIZED("100217", "The application hasn't been authorized."),
    ERROR_NBIOT_DEVICE_COUNT_REACHED_LIMIT("100412", "The amount of device has reached the limit."),
    ERROR_NBIOT_DEVICE_HAS_BEAN_BINDED("100416", "The device has already been binded."),
    ERROR_NBIOT_DEVICE_DATA_NOT_EXISTED("100418", "The deviceData is not existed."),
    ERROR_NBIOT_SERVICE_TYPE_NOT_EXISTED("100431", "The serviceType is not exist."),
    ERROR_NBIOT_LICENSE_NOT_EXISTED("103026", "The license is not exist."),
    ERROR_NBIOT_LICENSE_SALES_NOT_EXISTED("103027", "The license sales is not exist."),
    ERROR_NBIOT_LICENSE_POOL_RESOURCES("103028", "The license pool resources.");

    private String key;
    /**
     * 备注
     */
    private String remark;

    ResponseCode(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    /**
     * key along with enum
     *
     * @return
     */
    @Override
    public String getKey() {
        return this.key;
    }

    public String getRemark() {
        return this.remark;
    }
}
