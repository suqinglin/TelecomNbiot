package com.nbiot.telecom.bean;

public class ReportCmdExecResultBean {

    /**
     * deviceId : deviceId
     * commandId : commandId
     */

    private String deviceId;
    private String commandId;
    private ResultBeanX result;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ReportCmdExecResultBean{" +
                "deviceId='" + deviceId + '\'' +
                ", commandId='" + commandId + '\'' +
                ", result=" + result.toString() +
                '}';
    }

    public class ResultBeanX {

        /**
         * resultCode : DELIVERED
         * resultDetail : null
         */

        private String resultCode;
        private Object resultDetail;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public Object getResultDetail() {
            return resultDetail;
        }

        public void setResultDetail(Object resultDetail) {
            this.resultDetail = resultDetail;
        }

        @Override
        public String toString() {
            return "ResultBeanX{" +
                    "resultCode='" + resultCode + '\'' +
                    ", resultDetail=" + resultDetail +
                    '}';
        }
    }
}
